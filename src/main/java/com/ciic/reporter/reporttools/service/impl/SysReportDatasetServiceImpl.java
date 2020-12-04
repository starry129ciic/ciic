package com.ciic.reporter.reporttools.service.impl;

import com.ciic.reporter.reporttools.entity.SysDataset;
import com.ciic.reporter.reporttools.mapper.SysReportDatasetMapper;
import com.ciic.reporter.reporttools.service.ISysDatasetService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wuhang
 * @since 2020-11-17
 */
@Service
public class SysReportDatasetServiceImpl extends ServiceImpl<SysReportDatasetMapper, SysDataset> implements ISysDatasetService {

    @Autowired
    public SysReportDatasetMapper sysDatasetMapper;

    @Override
    public List queryDataSetList() {
        List dataSetList = sysDatasetMapper.queryDataSetList();
        return dataSetList;
    }

    @Override
    public List queryTree1List(String cid) {
        List tree1List = sysDatasetMapper.queryTree1List(cid);
        return tree1List;
    }

    @Override
    public List queryTree2List() {
        List tree2List = sysDatasetMapper.queryTree2List();
        return tree2List;
    }




}
