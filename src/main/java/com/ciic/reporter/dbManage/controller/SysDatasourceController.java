package com.ciic.reporter.dbManage.controller;


import com.alibaba.fastjson.JSON;
import com.ciic.reporter.common.StatusEnum;
import com.ciic.reporter.dbManage.entity.SysDatasource;
import com.ciic.reporter.dbManage.service.ISysDatasourceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

import static java.time.LocalDate.now;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author stt
 * @since 2020-11-10
 */
@RestController
@RequestMapping("/dbManage/sys-datasource")
public class SysDatasourceController {

    @Autowired
    private ISysDatasourceService sysDatasourceService;


//    @PostMapping("/saveDataSource")
//    @ResponseBody
//    public void addDataSource(@RequestBody Object datasource) {
////        HashMap<String,Object> params=(LinkedHashMap<String,Object>)datasource;
////        SysDatasource sysDatasource = new SysDatasource();
////        System.out.println(datasource);
////
////        Set keys=params.keySet();
////        for(Object keyTemp:keys){
////            Map maps = (Map)JSON.parse(params.get(keyTemp.toString()).toString());
////            sysDatasource.setId(params.get());
////            System.out.println(maps);
////        }
//
//    }


    @PostMapping("/saveDataSource")
    @ResponseBody
    public void addDataSource(@RequestBody SysDatasource db){
        if(StringUtils.isEmpty(db.getId()))
        {
            db.setId(UUID.randomUUID().toString());
        }
        if(db.getCreateDate() != null){
            System.out.println("不等于空");
            //只要进行操作就对修改时间进行更新
            db.setUpdateDate(now());
            System.out.println("getCreateDate不为空");
        }
        sysDatasourceService.removeById(db.getId());
        //判断创建日期是否为空如果为空就是第一次创建就创建时间
        if(db.getCreateDate()==null){
            System.out.println("getCreateDate为空");
            db.setCreateDate(now());
            //只要进行操作就对修改时间进行更新
        }


        db.setStatus(StatusEnum.NOMAL);
        if(StringUtils.isEmpty(db.getDbInitalLinkCounts())) {
            db.setDbInitalLinkCounts("10");
        }
        if(StringUtils.isEmpty(db.getDbMinimumLinkCounts())) {
            db.setDbMinimumLinkCounts("10");
        }
        if(StringUtils.isEmpty(db.getDbMaximumLinkCounts())) {
            db.setDbMaximumLinkCounts("20");
        }
        sysDatasourceService.save(db);
    }

    @PostMapping("/queryDataSource")
    @ResponseBody
    public List queryDataSource(@RequestBody String id){
        String datasetId = id.substring(id.indexOf(":") + 2, id.length()-2);
        List dbList = sysDatasourceService.queryDatasourceList(datasetId);
        return dbList;
    }

    @GetMapping("/queryCodeList")
    @ResponseBody
    public List queryDatasourceCode(){
        List list = sysDatasourceService.queryCodeList();
        return list;
    }

    @PostMapping("/queryDataSourceByCode")
    public List queryDataSourceByCode(@RequestBody String dbCode){
        String code = dbCode.substring(dbCode.indexOf(":") + 2, dbCode.length()-2);
        List list = sysDatasourceService.queryDataSourceByCode(code);
        return list;
    }

    @PostMapping("/testURL")
    @ResponseBody
    public String testURL(@RequestBody SysDatasource sysDatasource){
        //驱动类
        String driver = sysDatasource.getDbDriver();
        Connection conn=null;
        //提示信息
        String message = "";
        try {
            String url = sysDatasource.getDbLink();//连接字符串
            String userName = sysDatasource.getDbUsername();//用户名
            String password = sysDatasource.getDbPassword();//密码
            if (!"".equals(driver) && driver != null) {
                Class.forName(driver);//加载数据驱动
                conn = DriverManager.getConnection(url, userName, password);// 连接数据库
            }
            System.out.println(conn);
        } catch (SQLException | ClassNotFoundException throwables) {
//            message = "连接失败！";
            throwables.printStackTrace();
        }
        if (conn != null) {
            message = "连接成功！";
        }
        return message;
    }

}
