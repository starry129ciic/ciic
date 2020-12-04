package com.ciic.reporter.reporttools.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ciic.reporter.reporttools.entity.SysReportData;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wuhang
 * @since 2020-11-06
 */
public interface ISysReportDataService extends IService<SysReportData> {


    IPage<SysReportData> selectReportData(Long current, Long size ,String cusId,String reportName);


    SysReportData selectByCusId(String cusId);
}
