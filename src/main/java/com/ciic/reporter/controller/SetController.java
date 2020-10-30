package com.ciic.reporter.controller;

import com.ciic.reporter.common.DynamicDataSourceContextHolder;
import com.ciic.reporter.service.IDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Controller("/set")
@RestController
public class SetController {
    @Autowired
    IDataService dataService;

    @GetMapping("/first")
    public String getDataSource(String datasource) {
        String dataSourceType =datasource;
        DynamicDataSourceContextHolder.setDataSourceType(dataSourceType);
        String sql="";
        if("first".equals(dataSourceType))
        {
            sql="select * from datasource";
        }
        if("second".equals(dataSourceType))
        {
            sql="select * from datasource2";
        }
        List<Map<String, Object>> c= dataService.getData(datasource,sql);
        DynamicDataSourceContextHolder.clearDataSourceType();
        return "myFirstDataSource"+c.size();
    }

}
