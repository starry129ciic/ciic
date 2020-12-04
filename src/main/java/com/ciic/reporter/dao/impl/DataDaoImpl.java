package com.ciic.reporter.dao.impl;

import com.ciic.reporter.dao.IDataDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

@Repository
public class DataDaoImpl implements IDataDao {
    @Qualifier("dynamicDataSource")
    @Autowired
    private DataSource dataSource;

    /**
     * 添加数据
     * @param sql
     */
    public void addData(String sql) {
        Connection con=null;
        try {
            //获取数据库中的数据源配置
            con=dataSource.getConnection();
            Statement st=con.createStatement();
            st.execute(sql);
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
    }


    /**
     * 获取数据
     * @param sql
     * @return
     */
    @Override
    public List<Map<String, Object>> getData(String sql) {
        Connection con=null;
        List<Map<String, Object>> result=new ArrayList<Map<String, Object>>();
        try {
            //获取数据库中的数据源配置
            con=dataSource.getConnection();
            Statement st=con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            result=convertList(rs);
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
        return result;
    }

    @Override
    public List<Map<String, Object>> getDataPage(String sql, Long current, Long size) {
        Connection con=null;
        List<Map<String, Object>> result=new ArrayList<Map<String, Object>>();
        try {
            //获取数据库中的数据源配置
            con=dataSource.getConnection();
            Statement st=con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            result=convertList(rs);
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
        return result;
    }


    /**
     * 将ResultSet转换成MAP
     * @param rs
     * @return
     */
    private static List<Map<String, Object>> convertList(ResultSet rs) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            while (rs.next()) {
                Map<String, Object> rowData = new HashMap<String, Object>();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(md.getColumnName(i), rs.getObject(i));
                }
                list.add(rowData);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                rs = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }


}
