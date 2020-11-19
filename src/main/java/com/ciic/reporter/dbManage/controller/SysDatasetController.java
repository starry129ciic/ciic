package com.ciic.reporter.dbManage.controller;


import com.ciic.reporter.dbManage.entity.SysDataset;
import com.ciic.reporter.dbManage.entity.SysDatasource;
import com.ciic.reporter.dbManage.service.ISysDatasetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author stt
 * @since 2020-11-06
 */
@RestController
@RequestMapping("/dbManage/sys-dataset")
public class SysDatasetController {

    @Autowired
    private ISysDatasetService sysDatasetService;

    @GetMapping("/query")
    @ResponseBody
    public List queryDataSet(){
        List list = sysDatasetService.queryDataSetList();
        return list;
    }

    @GetMapping("/queryTreeList")
    @ResponseBody
    public List queryTreeList(){
        List list = sysDatasetService.queryTreeList();
        return list;
    }

    @PostMapping("/queryDataByTreeKey")
    @ResponseBody
    public List queryDataByTreeKey(@RequestBody String dataSetId){
        String id = dataSetId.substring(dataSetId.indexOf(":") + 2 , dataSetId.length() - 2);
        List list = sysDatasetService.queryDataByTreeKey(id);
        return list;
    }

}
