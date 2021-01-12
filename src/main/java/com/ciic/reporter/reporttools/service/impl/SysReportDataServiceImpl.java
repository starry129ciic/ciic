package com.ciic.reporter.reporttools.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ciic.reporter.reporttools.entity.SysReportData;
import com.ciic.reporter.reporttools.mapper.SysReportDataMapper;
import com.ciic.reporter.reporttools.service.ISysReportDataService;
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
 * @since 2020-11-06
 */
@Service
public class SysReportDataServiceImpl extends ServiceImpl<SysReportDataMapper, SysReportData> implements ISysReportDataService {

    @Autowired
    private SysReportDataMapper sysReportDataMapper;


    @Override
    public IPage<SysReportData> selectReportData(Long current, Long size,String cusId,String reportName) {
        IPage<SysReportData> page = new Page<>(current,size);//设置初始页,每页条数
        QueryWrapper<SysReportData> reportDataQueryWrapper = new QueryWrapper<>();
        reportDataQueryWrapper.and(Wrapper -> Wrapper.like("cus_id", cusId).or().eq("is_public",1)).like("report_name",reportName);

//        reportDataQueryWrapper.like("report_name",reportName).or().eq("report_name",reportName);
        reportDataQueryWrapper.eq("status","0");
        IPage<SysReportData> selectPage = sysReportDataMapper.selectPage(page, reportDataQueryWrapper);//分页条件查询
        return selectPage;
    }

    @Override
    public SysReportData selectByCusId(String cusId) {
        SysReportData reportData = sysReportDataMapper.getByCusId(cusId);
        return reportData;
    }
}
