package com.ciic.reporter.dbManage.service.impl;

import com.ciic.reporter.dbManage.entity.SysDatasetDetail;
import com.ciic.reporter.dbManage.mapper.SysDatasetDetailMapper;
import com.ciic.reporter.dbManage.service.ISysDatasetDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class SysDatasetDetailServiceImpl extends ServiceImpl<SysDatasetDetailMapper, SysDatasetDetail> implements ISysDatasetDetailService {

    @Autowired
    private SysDatasetDetailMapper sysDatasetDetailMapper;

    @Override
    public List queryColList(String sqlContent) {
        List colList = sysDatasetDetailMapper.queryColList(sqlContent);
        return colList;
    }

    @Override
    public List queryDataTableList(String dsId) {
        List list = sysDatasetDetailMapper.queryDataTableList(dsId);
        return list;
    }

    @Override
    public List querydsIdByid(String id) {
        List list = sysDatasetDetailMapper.querydsIdByid(id);
        return list;
    }

}
