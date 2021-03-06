package com.ciic.reporter.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.ciic.reporter.service.IDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@RestController
public class ShowDataController {

    @Autowired
    IDataService dataService;

    /***
     * 加载自定义报表表头
     * @param datasid 报表编码
     * @param lang 语言
     * @return
     */
    @PostMapping("/showtabletitledata")
    public String showtabletitle(String datasid, String lang) {
        // 输入参数验证
        String error = checkDataSid(datasid);
        if (error != null) return error;
        //order by detial_order
        String mainDetialRP = "select t1.* from report_show t1 join sys_report_data t2 on t1.report_id=t2.id where t2.code='" + datasid + "' and statues=0 order by t1.detail_order asc ,t1.col_type asc";
        List<Map<String, Object>> mainDetialRPL = dataService.getData("first", mainDetialRP);
        return JSONUtils.toJSONString(mainDetialRPL);
    }


    /***
     * 加载自定义报表数据内容
     * @param datasid 报表编码
     * @param lang 语言
     * @param request 请求信息
     * @return
     */
    @PostMapping("/showtabledata")
    public String showtable(String datasid, String lang, HttpServletRequest request) {
        // 输入参数验证
        String error = checkDataSid(datasid);
        if (error != null) return error;
        Pattern p = Pattern.compile("[^0-9]");

        //验证分页
        String pageStr = request.getParameter("page");
        if(StringUtils.isEmpty(pageStr))
        {
            pageStr="1";
        }
        Matcher m = p.matcher(pageStr);
        int page = 1;
        if (!m.find()) {
            page = Integer.parseInt(pageStr);
        }
        String limitStr = request.getParameter("limit");
        if(StringUtils.isEmpty(limitStr))
        {
            limitStr="10";
        }
        m = p.matcher((limitStr.isEmpty()?"":limitStr));
        int limit = 10;
            if (!m.find()) {
                limit = Integer.parseInt(limitStr);
            }

        //找到需要执行的所有语句并执行。
        String mainRP = "select data_source_id,report_select_sql,sd.db_driver from sys_report_data sp join sys_datasource sd on sp.data_source_id=sd.db_code where code='" + datasid + "'";
        List<Map<String, Object>> mainRPL = dataService.getData("first", mainRP);
        Map<String, Object> mainSqlMap = mainRPL.get(0);
        String datasource_id = mainSqlMap.get("data_source_id") == null ? "" : mainSqlMap.get("data_source_id").toString();
//        String beSql = mainSqlMap.get("be_sql") == null ? "" : mainSqlMap.get("be_sql").toString();
        String mainSql = mainSqlMap.get("report_select_sql") == null ? "" : mainSqlMap.get("report_select_sql").toString();
//        String endSql = mainSqlMap.get("end_sql") == null ? "" : mainSqlMap.get("end_sql").toString();
//        String[] beSqlList = beSql.split(";");
//        for (String tempSql : beSqlList) {
//            if (!StringUtils.isEmpty(tempSql)) {
//                dataService.getData(datasource_id, tempSql);
//            }
//        }
        String dbDriver=mainSqlMap.get("db_driver") == null ? "" : mainSqlMap.get("db_driver").toString();
        //组装where条件
        String where = "";
        //查询主数据的条件
        String jsonStr = request.getParameter("jsonStr");
        List<Map<String, Object>> conditionList = null;
        if (!StringUtils.isEmpty(jsonStr)) {
            conditionList = (List<Map<String, Object>>) JSONUtils.parse(jsonStr);
        }
        if (conditionList != null && conditionList.size() > 0) {
            for (Map<String, Object> temMap : conditionList) {
                if ("equal".equals(temMap.get("conditionOptionVal"))) {
                    where += " " + temMap.get("logicalOperator").toString() + " " + temMap.get("conditionFieldVal") + "='"
                            + clearParam(((Map<String, Object>) temMap.get("conditionValueVal")).get("value").toString()) + "'";
                } else if ("like".equals(temMap.get("conditionOptionVal"))) {
                    where += " " + temMap.get("logicalOperator").toString() + " " + temMap.get("conditionFieldVal") + " like '%"
                            + clearParam(((Map<String, Object>) temMap.get("conditionValueVal")).get("value").toString()) + "%'";
                } else if ("between".equals(temMap.get("conditionOptionVal"))) {
                    where += " " + temMap.get("logicalOperator").toString() + " " + temMap.get("conditionFieldVal") + " between '"
                            + clearParam(((Map<String, Object>) temMap.get("conditionValueLeftVal")).get("value").toString()) + "'"
                            + " and '" + clearParam(((Map<String, Object>) temMap.get("conditionValueRightVal")).get("value").toString()) + "'";
                } else if ("unequal".equals(temMap.get("conditionOptionVal"))) {
                    where += " " + temMap.get("logicalOperator").toString() + " " + temMap.get("conditionFieldVal") + "!='"
                            + clearParam(((Map<String, Object>) temMap.get("conditionValueVal")).get("value").toString()) + "'";
                } else if ("empty".equals(temMap.get("conditionOptionVal"))) {
                    where += " " + temMap.get("logicalOperator").toString() + " " + temMap.get("conditionFieldVal") + " is null ";
                } else if ("notempty".equals(temMap.get("conditionOptionVal"))) {
                    where += " " + temMap.get("logicalOperator").toString() + " " + temMap.get("conditionFieldVal") + " is not null ";
                }
            }
        }

        mainSql = mainSql.replace("{{where}}",where);

        int count = 10;
        String selectCountsSql = "select count(*) AS decount from (" +  mainSql + ") a";
        String endSql = mainSql + " limit " + ((page - 1) * limit) + "," + (page * limit);
        if("oracle.jdbc.OracleDriver".equals(dbDriver)) {
            selectCountsSql = "select count(1) AS \"decount\" from (" +  mainSql + ") a";
            if (!StringUtils.isEmpty(selectCountsSql)) {
                count = Integer.parseInt(dataService.getData(datasource_id, selectCountsSql).get(0).get("decount").toString());
            }
            endSql ="select *" +
                    "  from (select t.*, rownum as no" +
                    "          from ("+mainSql+") t) " +
                    " where no between "+((page - 1) * limit)+" and "+(page * limit);
        }else
        {
            if (!StringUtils.isEmpty(selectCountsSql)) {
                count = Integer.parseInt(dataService.getData(datasource_id, selectCountsSql).get(0).get("decount").toString());
            }
        }


        List<Map<String, Object>> dataList = null;
        //如果是导出查询所有数据
        String type = request.getParameter("type");
        if("export".equals(type))
        {
            dataList = dataService.getData(datasource_id, mainSql);
        }else
        {
            dataList = dataService.getData(datasource_id, endSql);
        }
        Map resultEntity = new HashMap<String, Object>();
        resultEntity.put("code", 0);
        resultEntity.put("msg", "");
        resultEntity.put("count", count);
        resultEntity.put("page", page);
        resultEntity.put("limit", limit);
        resultEntity.put("data", dataList);
        return JSONUtils.toJSONString(resultEntity);
    }

    /**
     * 防止SQL注入
     * @param str
     * @return
     */
    public String clearParam(String str)
    {
        return str.replaceAll(".*([';]+|(--)+).*", " ");
    }

    /**
     * 验证报表参数的方法
     * @param datasid
     * @return
     */
    private String checkDataSid(String datasid) {
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
        return null;
    }
}
