package com.ciic.reporter.cusmain.controller;


import com.ciic.reporter.cusmain.entity.CusMain;
import com.ciic.reporter.cusmain.service.CusMainService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("CusMain")
@RequiredArgsConstructor
@CrossOrigin(origins = "*",maxAge = 3600)
public class CusMainController {
    @Autowired
     CusMainService cusMainService;

    //查询
    @GetMapping("query")
    public List<CusMain> queryList(){
       List<CusMain> entityList =cusMainService.list();
       return entityList;
    }


}
