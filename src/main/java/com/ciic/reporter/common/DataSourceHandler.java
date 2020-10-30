package com.ciic.reporter.common;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Properties;


@EnableTransactionManagement
@Configuration
@MapperScan("com.baomidou.mybatisplus.core.mapper")
@MapperScan("com.ciic.reporter.*.mapper")
public class DataSourceHandler {
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
        // paginationInterceptor.setOverflow(false);
        // 设置最大单页限制数量，默认 500 条，-1 不受限制
        // paginationInterceptor.setLimit(500);
        // 开启 count 的 join 优化,只针对部分 left join
        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        return paginationInterceptor;
    }
    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.druid.db1")
    public DataSource firstDataSource()
    {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.db2")
    public DataSource secondDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "dynamicDataSource")
    @Primary
    public DataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.myMap = new HashMap<>();//保存我们有的数据源，方便后面动态增加
        dynamicDataSource.myMap.put("first", firstDataSource());
        dynamicDataSource.myMap.put("second", secondDataSource());
        DataSource ds = firstDataSource();
        Connection con=null;
        try {
            //获取数据库中的数据源配置
            con=ds.getConnection();
            Statement st=con.createStatement();
            ResultSet rs = st.executeQuery("select * from datasource where status=0");
            while (rs.next()) {
                Properties properties = new Properties();
                properties.setProperty("url",rs.getString("url"));
                properties.setProperty("username",rs.getString("username"));
                properties.setProperty("password",rs.getString("password"));
                properties.setProperty("driverClassName",rs.getString("driver_class_name"));
                properties.setProperty("initialSize",rs.getString("initialSize"));
                properties.setProperty("minIdle",rs.getString("minIdle"));
                properties.setProperty("maxActive",rs.getString("maxActive"));
                DataSource dataSource = null;
                try {
                    dataSource=DruidDataSourceFactory.createDataSource(properties);
                }catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                dynamicDataSource.myMap.put(rs.getString("data_source_code"), DruidDataSourceFactory.createDataSource(properties));
            }
            rs.close();
            st.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(con!=null)
            {
                try {
                    con.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

        dynamicDataSource.setTargetDataSources(dynamicDataSource.myMap);//父类的方法
        DynamicDataSourceContextHolder.dataSourceIds.addAll(dynamicDataSource.myMap.keySet());
        dynamicDataSource.setDefaultTargetDataSource(firstDataSource());//父类的方法
        return dynamicDataSource;
    }
    @Bean("sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dynamicDataSource());

        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setCacheEnabled(false);
        sqlSessionFactory.setConfiguration(configuration);
        //PerformanceInterceptor(),OptimisticLockerInterceptor()
        //添加分页功能
        sqlSessionFactory.setPlugins(new org.apache.ibatis.plugin.Interceptor[]{
                paginationInterceptor()
        });
//        sqlSessionFactory.setGlobalConfig(globalConfiguration()); //注释掉全局配置，因为在xml中读取就是全局配置
        return sqlSessionFactory.getObject();
    }


}
