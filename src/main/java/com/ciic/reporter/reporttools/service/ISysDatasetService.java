package com.ciic.reporter.reporttools.service;

import com.ciic.reporter.reporttools.entity.SysDataset;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wuhang
 * @since 2020-11-17
 */
public interface ISysDatasetService extends IService<SysDataset> {

    List queryDataSetList();

    List queryTree1List(String cid);

    List queryTree2List();

}
