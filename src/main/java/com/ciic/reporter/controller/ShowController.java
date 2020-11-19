package com.ciic.reporter.controller;

import com.ciic.reporter.service.IDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller()
public class ShowController {
    @Autowired
    IDataService dataService;

    @GetMapping("/showtable")
    public String showtable(ModelMap mav, String datasid, String lang) {
        // 输入参数验证
        // 报表编号不能为空
        if(StringUtils.isEmpty(datasid))
        {
            return "error";
        }
        // 报表编号不能太长
        if(StringUtils.length(datasid)>64)
        {
            return "error";
        }
        //为了防止SQL注入，报表编号只能传字母数字和横岗
        Pattern p= Pattern.compile("[^0-9a-zA-Z\\-]");
        Matcher m= p.matcher(datasid);
        if(m.find())
        {
            return "error";
        }
        //找到需要执行的所有语句并执行。
        String mainRP = "select * from sys_report_data where code='" + datasid + "' and status=0";
        List<Map<String, Object>> mainRPL = dataService.getData("first", mainRP);
        Map<String, Object> mainSqlMap = mainRPL.get(0);

        if("cn".equals(lang)){
            mav.addAttribute("headdiv", mainSqlMap.get("headdiv"));
            mav.addAttribute("footdiv", mainSqlMap.get("footdiv"));
        }else if("en".equals(lang)){
            mav.addAttribute("headdiv", mainSqlMap.get("enheaddiv"));
            mav.addAttribute("footdiv", mainSqlMap.get("enfootdiv"));
        }else if("cnen".equals(lang)){
            mav.addAttribute("headdiv", mainSqlMap.get("cnenheaddiv"));
            mav.addAttribute("footdiv", mainSqlMap.get("cnenfootdiv"));
        }
        return "show/showtable";
    }



}
