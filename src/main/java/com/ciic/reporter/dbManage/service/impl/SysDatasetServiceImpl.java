package com.ciic.reporter.dbManage.service.impl;

import com.ciic.reporter.dbManage.entity.SysDataset;
import com.ciic.reporter.dbManage.mapper.SysDatasetMapper;
import com.ciic.reporter.dbManage.service.ISysDatasetService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author caoxh
 * @since 2020-11-06
 */
@Service
public class SysDatasetServiceImpl extends ServiceImpl<SysDatasetMapper, SysDataset> implements ISysDatasetService {

    @Autowired
    private SysDatasetMapper sysDatasetMapper;

    @Override
    public List queryDataSetList() {
        List datasetList = sysDatasetMapper.queryDataSetList();
        return datasetList;
    }

    @Override
    public List queryTreeList() {
        List treeList = sysDatasetMapper.queryTreeList();
        return treeList;
    }

    @Override
    public List queryDataByTreeKey(String dataSetId) {
        List dsList = sysDatasetMapper.queryDataByTreeKey(dataSetId);
        return dsList;
    }

}
