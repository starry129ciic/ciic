package com.ciic.reporter.dbManage.mapper;

import com.ciic.reporter.dbManage.entity.SysDataset;
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

public interface SysDatasetMapper extends BaseMapper<SysDataset> {
    /**
     * 查询数据集
     * @return
     */
    List queryDataSetList();

    /**
     * 查询数据集展示数据
     * @return
     */
    List queryTreeList();

    /**
     * 查询数据库编码
     * @return
     */
    List queryCodeList();

    /**
     * 根据数据集树查询数据集列表
     * @param dataSetId
     * @return
     */
    List queryDataByTreeKey(@Param("id") String dataSetId);
}
