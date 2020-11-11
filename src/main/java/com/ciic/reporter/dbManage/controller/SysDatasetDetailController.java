package com.ciic.reporter.dbManage.controller;


import com.ciic.reporter.dbManage.entity.SysDatasetDetail;
import com.ciic.reporter.dbManage.service.ISysDatasetDetailService;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping("/dbManage/sys-dataset-detail")
public class SysDatasetDetailController {

    @Autowired
    private ISysDatasetDetailService sysDatasetDetailService;

    @PostMapping("/addDatasetDetail")
    @ResponseBody
    public void addDatasetDetail(@RequestBody SysDatasetDetail sysDatasetDetail){
        //
        String tableId = sysDatasetDetail.getFieldId().substring(0,4);
        sysDatasetDetail.setDsId(tableId);
        sysDatasetDetail.setTableId(tableId);
        sysDatasetDetailService.save(sysDatasetDetail);
    }

    @PostMapping("/analysSql")
    @ResponseBody
    public List analysSql(@RequestBody SysDatasetDetail sysDatasetDetail){
        List colList = null;
        String sqlContent = sysDatasetDetail.getSqlContent();
        if (sqlContent != null && !"".equals(sqlContent)) {
            sqlContent = sqlContent.substring(sqlContent.indexOf("select") + 1,sqlContent.indexOf("from") - 1);
            if ("*".equals(sqlContent.trim())) {

            } else {
                String[] cols = sqlContent.split(",");
                for (String col : cols) {
                    if ( StringUtils.isNotEmpty(col) && col.indexOf("as") == 0) {
                        colList.add(col);
                    } else {
                        col = col.substring(col.indexOf("as" + 1), col.length()).trim();
                        colList.add(col);
                    }
                }
            }
        }
        return list;
    }

}
