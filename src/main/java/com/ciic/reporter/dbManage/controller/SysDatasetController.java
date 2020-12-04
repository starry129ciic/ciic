package com.ciic.reporter.dbManage.controller;


import com.ciic.reporter.dbManage.entity.SysDataset;
import com.ciic.reporter.dbManage.entity.SysDatasetDetail;
import com.ciic.reporter.dbManage.entity.SysDatasource;
import com.ciic.reporter.dbManage.service.ISysDatasetDetailService;
import com.ciic.reporter.dbManage.service.ISysDatasetService;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.time.LocalDate.now;

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
    @Autowired
    private ISysDatasetDetailService sysDatasetDetailService;
//    @Autowired
//    private SysDataset sysDataset;

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
        String id = "";
        if (dataSetId != null && !"".equals(dataSetId)) {
            id = dataSetId.substring(dataSetId.indexOf(":") + 2 , dataSetId.length() - 2);
        }
        List list = sysDatasetService.queryDataByTreeKey(id);
        return list;
    }

    //Sql查询页面保存
    @PostMapping("/saveInfo")
    @ResponseBody
    public List saveInfo(@RequestBody SysDataset sysDataset){
        String dsId = sysDataset.getDsId();
        List list = new ArrayList();
        if (dsId != null && !"".equals(dsId)) {
            list = sysDatasetDetailService.queryDataTableList(dsId);
        }
        sysDataset.setCreateDate(now());
        sysDatasetService.save(sysDataset);
        return list;
    }

    //客户查询页面保存
    @ResponseBody
    @RequestMapping("/saveDataSet")
    public void saveDataSet(@RequestBody SysDataset sysDataset) {
        String cusId = sysDataset.getCusId();
        String cusid = "";
        String branchId = "";
        if (!"".equals(cusId) && cusId != null) {
            cusid = cusId.substring(0,cusId.indexOf("***"));
            branchId = cusId.substring(cusId.indexOf("***") + 3, cusId.length());
        }
        //获取参数主键id
        String ID = sysDataset.getId();
        if ("".equals(ID) && ID == null) {
            ID = UUID.randomUUID().toString().replace("-", "");
        }
        sysDataset.setId(ID);
        sysDataset.setCusId(cusid);
        sysDataset.setBranchId(branchId);
        sysDataset.setCreateDate(now());
        sysDataset.setDsType("5");
        sysDataset.setStatus("1");
        sysDatasetService.save(sysDataset);
    }
}
