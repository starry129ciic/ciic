package com.ciic.reporter.dbManage.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ciic.reporter.common.StatusEnum;
import com.ciic.reporter.dbManage.entity.SysDataset;
import com.ciic.reporter.dbManage.entity.SysDatasetDetail;
import com.ciic.reporter.dbManage.entity.SysDatasource;
import com.ciic.reporter.dbManage.service.ISysDatasetDetailService;
import com.ciic.reporter.dbManage.service.ISysDatasetService;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.*;

import static java.time.LocalDate.now;
import static org.apache.commons.lang3.StringUtils.trim;

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

    @GetMapping("/queryDataByTreeKey")
    @ResponseBody
    public List queryDataByTreeKey(@RequestParam("dataSetId") String dataSetId){
//        String id = "";
//        if (dataSetId != null && !"".equals(dataSetId)) {
//            id = dataSetId.substring(dataSetId.indexOf(":") + 2 , dataSetId.length() - 2);
//        }

        List list = sysDatasetService.queryDataByTreeKey(dataSetId);
        return list;
    }

    //Sql查询页面保存
    @PostMapping("/saveInfo")
    @ResponseBody
    public void saveInfo(@RequestBody Object sysDataset){
        LinkedHashMap<String,Object> input=(LinkedHashMap<String,Object>)sysDataset;
        if(input==null||input.size()<1)
        {
            return;
        }
        //从前台获取报表的Id
        String dsId=input.get("dsId")==null?UUID.randomUUID().toString():input.get("dsId").toString();
        SysDataset dataset=new SysDataset();
        dataset.setId(dsId);
        dataset.setDsId(input.get("sql")==null?"":input.get("sql").toString());
        dataset.setDbSourceId(input.get("queryId")==null?"":input.get("queryId").toString());
        dataset.setDsName(input.get("dsName")==null?"":input.get("dsName").toString());
        dataset.setDsType(input.get("dsType")==null?"":input.get("dsType").toString());
        dataset.setSort(input.get("sort")==null?"0":input.get("sort").toString());
        dataset.setStatus(StatusEnum.NOMAL);
        dataset.setCreateDate(LocalDate.now());
        dataset.setRemarks(input.get("remark")==null?"":input.get("remark").toString());
        dataset.setCusId(input.get("cusId")==null?"":input.get("cusId").toString());
        dataset.setCusName(input.get("cusName")==null?"":input.get("cusName").toString());
        dataset.setBranchId(input.get("branchId")==null?"":input.get("branchId").toString());

        List<SysDatasetDetail> sqlList=new ArrayList<SysDatasetDetail>();
        for(String tempKey:input.keySet()) {
            if(tempKey.startsWith("defineData")) {
                Map<String ,Object> map=(Map<String,Object>) JSON.parse(input.get(tempKey).toString());
                if(map==null)
                {
                    continue;
                }
                SysDatasetDetail detail = new SysDatasetDetail();
                detail.setId(map.get("id")==null?UUID.randomUUID().toString():map.get("id").toString());
                detail.setDsId(dsId);
                detail.setTableId(map.get("tableId")==null?"":map.get("tableId").toString());
                detail.setTableName(map.get("tableName")==null?"":map.get("tableName").toString());
                detail.setFieldId(trim(map.get("field_id")==null?"":map.get("field_id").toString()));
                detail.setFieldName(map.get("field_name")==null?"":map.get("field_name").toString());
                detail.setFieldEnName(map.get("field_en_name")==null?"":map.get("field_en_name").toString());
                detail.setFieldChEnName(map.get("field_ch_en_name")==null?"":map.get("field_ch_en_name").toString());
                detail.setSort(map.get("index")==null?"":map.get("index").toString());
                detail.setStatus(StatusEnum.NOMAL);
                detail.setCreateDate(LocalDate.now());
                sqlList.add(detail);
            }
        }
        if(sqlList.size()<1)
        {
            return;
        }
        QueryWrapper<SysDataset> wrapperSysDataset=new QueryWrapper<SysDataset>();
        wrapperSysDataset.eq("id",dsId);
        sysDatasetService.remove(wrapperSysDataset);
        QueryWrapper<SysDatasetDetail> wrapperSysDatasetDetial=new QueryWrapper<SysDatasetDetail>();
        wrapperSysDatasetDetial.eq("ds_id",dsId);
        sysDatasetDetailService.remove(wrapperSysDatasetDetial);
        sysDatasetService.save(dataset);
        for(SysDatasetDetail temp:sqlList)
        {
            sysDatasetDetailService.save(temp);
        }
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
        sysDataset.setStatus(StatusEnum.NOMAL);
        sysDatasetService.save(sysDataset);
    }
}
