package com.ciic.reporter.reporttools.mapper;

import com.ciic.reporter.reporttools.entity.SysDataset;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wuhang
 * @since 2020-11-17
 */
public interface SysReportDatasetMapper extends BaseMapper<SysDataset> {

//    @Select("SELECT s.ds_id,d.table_id,d.field_id,d.field_name FROM sys_dataset s,sys_dataset_detail d WHERE s.id = d.ds_id")
//    List<SysDatasetAndDetailVo> SelectDatasetMapper();

    List queryDataSetList();

    List queryTree1List(String cid);

    List queryTree2List();
}
