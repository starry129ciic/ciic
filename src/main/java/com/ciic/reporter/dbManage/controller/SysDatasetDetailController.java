package com.ciic.reporter.dbManage.controller;


import com.ciic.reporter.dbManage.entity.SysDatasetDetail;
import com.ciic.reporter.dbManage.service.ISysDatasetDetailService;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    private ISysDatasetDetailService sysDatasetDetailService;

    @PostMapping("/addDatasetDetail")
    @ResponseBody
    public void addDatasetDetail(@RequestBody SysDatasetDetail sysDatasetDetail){
        String tableId = sysDatasetDetail.getFieldId().substring(0,4);
        sysDatasetDetail.setDsId(tableId);
        sysDatasetDetail.setTableId(tableId);
        sysDatasetDetailService.save(sysDatasetDetail);
    }

    @PostMapping("/updateDatasetDetail")
    @ResponseBody
    public void updateDatasetDetail(@RequestBody SysDatasetDetail sysDatasetDetail){
        sysDatasetDetailService.updateDatasetDetail(sysDatasetDetail);
    }

    @PostMapping("/saveInfo")
    @ResponseBody
    public void saveInfo(@RequestBody SysDatasetDetail sysDatasetDetail){
        sysDatasetDetailService.save(sysDatasetDetail);
    }

    @PostMapping("/analysSql")
    @ResponseBody
    public List analysSql(@RequestBody String sqlContent) {
        List colList = new ArrayList();
        List queryColList = new ArrayList();
        String sqlContentValue = sqlContent.substring(sqlContent.indexOf(":") + 2, sqlContent.length() - 2).toLowerCase();
        if (sqlContentValue != null && !"".equals(sqlContentValue)) {
            String sqlContentChild = sqlContentValue.substring(sqlContentValue.indexOf("select") + 6, sqlContentValue.indexOf("from") - 1);
            if ("*".equals(sqlContentChild.trim())) {
                List list = sysDatasetDetailService.queryColList(sqlContentValue);
                if (list != null && list.size() > 0) {
                    Map<String, String> colMap = (Map) list.get(0);
                    for (Object key : colMap.keySet()) {
                        Map map = new HashMap();
                        map.put("field_id", key);
                        queryColList.add(map);
                    }
                }
            } else {
                String[] cols = sqlContentChild.split(",");
                for (String col : cols) {
                    Map fieldMap = new HashMap();
                    if (StringUtils.isNotEmpty(col) && col.indexOf("as") == -1) {
                        fieldMap.put("field_id", col);
                        colList.add(fieldMap);
                    } else {
                        col = col.substring(col.indexOf("as") + 2, col.length()).trim();
                        fieldMap.put("field_id", col);
                        colList.add(fieldMap);
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
