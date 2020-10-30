package com.ciic.reporter.db1.controller;


import com.alibaba.druid.support.json.JSONUtils;
import com.ciic.reporter.db1.entity.RpMain;
import com.ciic.reporter.db1.service.IRpMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 报表主表 前端控制器
 * </p>
 *
 * @author caoxh
 * @since 2020-10-27
 */
@RestController
@RequestMapping("/db1/rp-main")
public class RpMainController {
    @Autowired
    IRpMainService rpMainService;


    @GetMapping("/test")
    public String showtable( HttpServletRequest request) {
        List<RpMain> l=rpMainService.list();
        System.out.println(l);

        return JSONUtils.toJSONString("OK");
    }
}
