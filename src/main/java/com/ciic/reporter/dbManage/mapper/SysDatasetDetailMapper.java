package com.ciic.reporter.dbManage.mapper;

import com.ciic.reporter.dbManage.entity.SysDatasetDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Property;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author stt
 * @since 2020-11-06
 */
public interface SysDatasetDetailMapper extends BaseMapper<SysDatasetDetail> {

    /**
     * 查询字段列
     * @param sqlContent
     * @return
     */
    List queryColList(String sqlContent);

    /**
     * 查询“客户查询”页面数据
     * @param dsId
     * @return
     */
    List queryDataTableList(@Param("dsId") String dsId);

    /**
     * 查询数据集id by id
     * @param id
     * @return
     */
    List querydsIdByid(@Param("id") String id);
}
