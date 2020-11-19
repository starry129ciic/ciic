package com.ciic.reporter.dbManage.controller;


import com.ciic.reporter.dbManage.entity.SysDatasource;
import com.ciic.reporter.dbManage.service.ISysDatasourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

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

    @PostMapping("/saveDataSource")
    @ResponseBody
    public void addDataSource(@RequestBody SysDatasource db){
        db.setCreateDate(now());
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
            Class.forName(driver);//加载数据驱动
            conn = DriverManager.getConnection(url, userName, password);// 连接数据库
            System.out.println(conn);
        } catch (SQLException throwables) {
//            message = "连接失败！";
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (conn != null) {
            message = "连接成功！";
        }
        return message;
    }

}
