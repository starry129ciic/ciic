package com.ciic.reporter.dbManage.service;

import com.ciic.reporter.dbManage.entity.SysDataset;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author stt
 * @since 2020-11-06
 */
public interface ISysDatasetService extends IService<SysDataset> {
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
     * 通过数据集树查看数据集列表
     * @param dataSetId
     * @return
     */
    List queryDataByTreeKey(String dataSetId);
}
