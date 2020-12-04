package com.ciic.reporter.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ciic.reporter.common.DynamicDataSourceContextHolder;
import com.ciic.reporter.dao.IDataDao;
import com.ciic.reporter.reporttools.entity.SysReportData;
import com.ciic.reporter.service.IDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class DataServiceImpl implements IDataService {
    @Autowired
    private IDataDao dataDao;

    @Override
    public List<Map<String, Object>> getData(String dataSourceId,String sql) {
        DynamicDataSourceContextHolder.setDataSourceType(dataSourceId);
        List<Map<String, Object>> result= dataDao.getData(sql);
        DynamicDataSourceContextHolder.clearDataSourceType();
        return result;
    }

    @Override
    public void addData(String dataSourceId, String sql) {
        DynamicDataSourceContextHolder.setDataSourceType(dataSourceId);
        dataDao.addData(sql);
        DynamicDataSourceContextHolder.clearDataSourceType();
    }


    @Override
    public List<Map<String, Object>> getDataPage(String dataSourceId,String sql, Long current, Long size) {
        DynamicDataSourceContextHolder.setDataSourceType(dataSourceId);
        List<Map<String, Object>> result= dataDao.getDataPage(sql,current,size);
        DynamicDataSourceContextHolder.clearDataSourceType();

        return result;
    }
}
