package com.ciic.reporter.dbManage.mapper;

import com.ciic.reporter.dbManage.entity.SysDatasource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author stt
 * @since 2020-11-10
 */
public interface SysDatasourceMapper extends BaseMapper<SysDatasource> {
    /**
     * 根据数据集id,查询数据源
     * @return
     */
    List queryDatasourceList(@Param("id") String id);

    /**
     * 查询数据库编码
     * @return
     */
    List queryCodeList();

    /**
     * 通过数据源编码加载页面数据
     * @param dbCode
     * @return
     */
    List queryDataSourceByCode(@Param("db_code") String dbCode);
}
