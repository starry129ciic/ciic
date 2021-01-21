package com.ciic.reporter.dbManage.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.enums.SqlLike;
import com.ciic.reporter.common.StatusEnum;
import com.ciic.reporter.cusmain.entity.CusMain;
import com.ciic.reporter.dbManage.entity.SysDataset;
import com.ciic.reporter.dbManage.entity.SysDatasetDetail;
import com.ciic.reporter.dbManage.entity.SysDatasource;
import com.ciic.reporter.dbManage.service.ISysDatasetDetailService;
import com.ciic.reporter.dbManage.service.ISysDatasourceService;
import com.ciic.reporter.service.IDataService;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.regex.Pattern;

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
@RequestMapping("/dbManage/sys-dataset-detail")
public class SysDatasetDetailController {

    @Autowired
    IDataService dataService;
    @Autowired
    private ISysDatasetDetailService sysDatasetDetailService;
    @Autowired
    private ISysDatasourceService sysDatasourceService;

    @PostMapping("/addDatasetDetail")
    @ResponseBody
    public List addDatasetDetail(@RequestBody SysDatasetDetail sysDatasetDetail){
        sysDatasetDetailService.save(sysDatasetDetail);
        List list = new ArrayList();
        String dsId = sysDatasetDetail.getDsId();
        if (dsId != null && !"".equals(dsId)) {
            list = sysDatasetDetailService.queryDataTableList(dsId);
        }
        return list;
    }

    @PostMapping("/updateDatasetDetail")
    @ResponseBody
    public List updateDatasetDetail(@RequestBody SysDatasetDetail sysDatasetDetail){
        sysDatasetDetailService.updateById(sysDatasetDetail);
        List list = new ArrayList();
        String dsId = sysDatasetDetail.getDsId();
        if (dsId != null && !"".equals(dsId)) {
            list = sysDatasetDetailService.queryDataTableList(dsId);
        }
        return list;
    }

    @ResponseBody
    @PostMapping("/deleteCol")
    public List deleteDatasetDetail(@RequestBody String id) {
        List list = new ArrayList();
        String ID = "";
        String dsId = "";
        if (id != null && !"".equals(id)) {
            ID = id.substring(id.indexOf(":") + 2, id.length() - 2);
            if (ID != null && !"".equals(ID)) {
                List list1 = sysDatasetDetailService.querydsIdByid(ID);
                if (list1 != null && list1.size()>0) {
                    Map map = (Map)list1.get(0);
                    dsId = (String)map.get("ds_id");
                }
            }
        }
        sysDatasetDetailService.removeById(ID);
        if (dsId != null && !"".equals(dsId)) {
            list = sysDatasetDetailService.queryDataTableList(dsId);
        }
        return list;
    }

    @PostMapping("/queryCusList")
    @ResponseBody
    public List queryCusList(){
        List cusList = new ArrayList();
        String sql = "SELECT s.`ID`,s.`TENANT_ID`,s.CUS_ACTUAL_NAME FROM cus_base_info s";
        List<Map<String, Object>> list = dataService.getData("second", sql);

        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Map cusMap = (Map) list.get(i);
                Map cMap = new HashMap();
                String cusId = String.valueOf(cusMap.get("ID")) ;
                String branchId = (String) cusMap.get("TENANT_ID");
                String cusName = (String) cusMap.get("CUS_ACTUAL_NAME");
                String cusKey = cusId + "***" + branchId;
                cMap.put("cusId", cusKey);
                cMap.put("cusName", cusName);
                cusList.add(cMap);
            }
        }
        return cusList;
    }

    @GetMapping("/queryPageCusList")
    @ResponseBody
    public Map queryPageCusList(@RequestParam("current") Long current,@RequestParam("size") Long size,@RequestParam("cusId") String cusId,@RequestParam("cusName") String cusName){
        List cusList = new ArrayList();
        //判断分页是否正确,是否为数字 数字是否为正数

        if(size==null||size<1)
        {
            size= new Long(10);
        }
        if(current==null||current<1)
        {
            current= new Long(1);
        }

            String sql = "SELECT s.`ID`,s.`TENANT_ID`,s.CUS_ACTUAL_NAME FROM cus_base_info s where 1=1 ";
        String countSql="SELECT count(1) dcount FROM cus_base_info s where 1=1 ";
        if(!"".equals(cusId)&&null!=cusId)
        {
            sql=sql+" and (ID like '%"+cusId+"%'";
            countSql=countSql+" and (ID like '%"+cusId+"%'";
            sql=sql+" or CUS_ACTUAL_NAME like '%"+cusName+"%')";
            countSql=countSql+" or CUS_ACTUAL_NAME like '%"+cusName+"%')";
        }
//        if(!"".equals(cusName)&&null!=cusName)
//        {
//            sql=sql+" and CUS_ACTUAL_NAME like '%"+cusName+"%'";
//            countSql=countSql+" and CUS_ACTUAL_NAME like '%"+cusName+"%'";
//        }
        sql +=" limit "+((current-1)*size) +","+((current)*size);
        List<Map<String, Object>> list = dataService.getData("second", sql);
        List<Map<String, Object>> listCount = dataService.getData("second", countSql);

        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Map cusMap = (Map) list.get(i);
                Map cMap = new LinkedHashMap();
                cMap.put("cusId",String.valueOf(cusMap.get("ID")));
                cMap.put("cusName", (String) cusMap.get("CUS_ACTUAL_NAME"));
                cusList.add(cMap);
            }
        }
        Map mapPage = new HashMap();
        mapPage.put("current",current);
        mapPage.put("size",size);
        mapPage.put("total",listCount.get(0).get("dcount"));
        mapPage.put("cusList",cusList);
        return mapPage;
    }



    //客户查询-新增
    @PostMapping("/addDataSet")
    @ResponseBody
    public String addDataSet(@RequestBody SysDatasetDetail sysDatasetDetail){
        String dsId = sysDatasetDetail.getDsId();
        if ("".equals(dsId) || dsId == null) {
            dsId = UUID.randomUUID().toString().replace("-", "");
            sysDatasetDetail.setDsId(dsId);
        }
        sysDatasetDetail.setCreateDate(now());
        sysDatasetDetail.setStatus(StatusEnum.NOMAL);
        sysDatasetDetailService.save(sysDatasetDetail);
        return dsId;
    }

    //客户查询-加载表数据
    @ResponseBody
    @PostMapping("/queryData")
    public List queryData(@RequestBody String dsId){
        String id  = dsId.substring(dsId.indexOf(":") + 2, dsId.length()-2);
        List list = sysDatasetDetailService.queryDataTableList(id);
        return list;
    }

    @PostMapping("/analysSql")
    @ResponseBody
    public List analysSql(@RequestBody Map params) {
        String sqlContent=""+params.get("sqlContent");
        String dbCode=""+params.get("dbCode");
        List colList = new ArrayList();
        List queryColList = new ArrayList();
        String dsId = "";
        SysDatasetDetail sysDatasetDetail = new SysDatasetDetail();
        sysDatasetDetail.setCreateDate(now());
        sysDatasetDetail.setDsId(dsId);
        String sqlContentValue = sqlContent.substring(sqlContent.indexOf(":") + 2, sqlContent.length() - 2).toLowerCase();
        if (sqlContentValue != null && !"".equals(sqlContentValue)) {
            String sqlContentChild = sqlContentValue.substring(sqlContentValue.indexOf("select") + 6, sqlContentValue.indexOf("from") - 1);
            //根据DbSourceId  获取DbDriver;
            QueryWrapper<SysDatasource> querySysDatasource = new QueryWrapper<>();
            querySysDatasource.eq("db_code", dbCode);
            SysDatasource dataSource = sysDatasourceService.getOne(querySysDatasource);
            if (dataSource != null) {
                if ("*".equals(sqlContentChild.trim())) {
                    List list =null;
                    if("oracle.jdbc.OracleDriver".equals(dataSource.getDbDriver())){
                        list = dataService.getData(dbCode, "select * from (" +sqlContent+ ") aaa where rownum=1" );
                    }else {
                        list = dataService.getData(dbCode, sqlContent + " limit 0,1");
                    }
                    if (list != null && list.size() > 0) {
                        Map<String, String> colMap = (Map) list.get(0);
                        for (Object key : colMap.keySet()) {
                            String id = UUID.randomUUID().toString().replace("-", "");
                            Map map = new HashMap();
                            map.put("id", id);
                            map.put("field_id", key.toString().toUpperCase().trim());
                            map.put("ds_id", dsId);
                            queryColList.add(map);
                        }
                    }
                } else {
                    String[] cols = sqlContentChild.split(",");
                    for (String col : cols) {
                        Map fieldMap = new HashMap();
                        if (StringUtils.isNotEmpty(col) && col.indexOf("as") == -1) {
                            String id = UUID.randomUUID().toString().replace("-", "");
                            fieldMap.put("field_id", col.toUpperCase().trim());
                            fieldMap.put("id", id);
                            fieldMap.put("ds_id", dsId);
                            colList.add(fieldMap);
                        } else {
                            col = col.substring(col.indexOf("as") + 2, col.length()).trim();
                            String id = UUID.randomUUID().toString().replace("-", "");
                            fieldMap.put("field_id", col.toUpperCase().trim());
                            fieldMap.put("id", id);
                            fieldMap.put("ds_id", dsId);
                            colList.add(fieldMap);
                        }
                    }
                }
            }
        }
        if (queryColList != null && queryColList.size() > 0) {
            return queryColList;
        }
        return colList;
    }

}
