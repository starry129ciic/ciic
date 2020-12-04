package com.ciic.reporter.reporttools.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ciic.reporter.reporttools.entity.SysReportData;
import com.ciic.reporter.reporttools.service.ISysReportDataService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Console;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wuhang
 * @since 2020-11-06
 */
@RestController
@RequestMapping("/reporttools/sys-report-data")
public class SysReportDataController {

    @Resource
    private ISysReportDataService iSysReportDataService;



    @GetMapping("query")
    public IPage<SysReportData> queryList(@RequestParam("current") Long current,@RequestParam("size") Long size,@RequestParam("cusId") String cusId,@RequestParam("reportName") String reportName){

        IPage<SysReportData> pageList = iSysReportDataService.selectReportData(current,size,cusId,reportName);
        return pageList;
    }


   /* @GetMapping("/query/{cusId}")
    public List<SysReportData> queryLsit(@PathVariable("cusId") String cusId){
        SysReportData reportData = iSysReportDataService.selectByCusId(cusId);
        return (List<SysReportData>) reportData;
    }
*/








}
