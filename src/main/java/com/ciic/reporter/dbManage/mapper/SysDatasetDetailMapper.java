package com.ciic.reporter.dbManage.mapper;

import com.ciic.reporter.dbManage.entity.SysDatasetDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

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
     * 修改字段展示名称保存
     * @param id
     */
    void updateDatasetDetail(@Param("id") String id, @Param("field_id") String fieldId, @Param("field_name") String fieldName, @Param("field_en_name") String fieldEnName, @Param("field_ch_en_name") String fieldChEnName);

}
