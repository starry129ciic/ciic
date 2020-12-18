package com.ciic.reporter.reporttools.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ciic.reporter.common.StatusEnum;
import com.ciic.reporter.reporttools.entity.ReportShowVoEntity;
import com.ciic.reporter.reporttools.entity.ReportShow;
import com.ciic.reporter.reporttools.entity.SysDataset;
import com.ciic.reporter.reporttools.entity.SysReportData;
import com.ciic.reporter.reporttools.service.IReportShowService;
import com.ciic.reporter.reporttools.service.ISysDatasetService;
import com.ciic.reporter.reporttools.service.ISysReportDataService;
import com.ciic.reporter.reporttools.vo.ReportShowVo;
import com.ciic.reporter.reporttools.vo.SysDataSetDetailVo;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wuhang
 * @since 2020-11-11
 */
@RestController
@RequestMapping("/reporttools/report-show")
public class ReportShowController {

    @Autowired
    private IReportShowService reportShowService;
    @Autowired
    private ISysReportDataService sysReportDataService;
    @Autowired
    private ISysDatasetService sysDatasetService;

    @GetMapping("query")
    public List<ReportShow> queryList(){
        List<ReportShow> reportShows = reportShowService.list();
        return reportShows;
    }

//    //最后表单增加方法
//    @ResponseBody
//    @PostMapping("/addReportShow")
//    public void addReportShow(@RequestBody ReportShowVo reportShowVo){
//        iReportShowService.saveForm(reportShowVo);
//    }

    //最后表单增加方法
    @ResponseBody
    @PostMapping("/addReportShow")
    public void addReportShow(@RequestBody Object reportShowVo){
        System.out.println(reportShowVo);
        HashMap<String,Object> params=(LinkedHashMap<String,Object>)reportShowVo;
        SysReportData newReporterData=new SysReportData();
        //报表的主键
        String id=params.get("reportId")!=null?params.get("reportId").toString():UUID.randomUUID().toString();
        String sql="select ";
        Set keys=params.keySet();

        List<String> knowLists=new ArrayList<String>();
        String[] knowKeys={"reportId","reportName","reportEnName","reportChEnName","remarks","code","isPublic","isPrinting","isExport","cusId","isModelPrinting"};
        CollectionUtils.addAll(knowLists,knowKeys);
        String columns="";
        String where ="1=1 {{where}}";
        String groupby="";
        String orderby="";
        Boolean needGroupBy=false,hasNomalColumns=false;//只有当同时存在分组函数和普通列才需要group by
        List<ReportShow> shows=new ArrayList<ReportShow>();
        for (Object keyTemp:keys) {
            if(!knowLists.contains(keyTemp))
            {
                try {
                    Map maps = (Map) JSON.parse(params.get(keyTemp.toString()).toString());
                    ReportShow show=new ReportShow();
                    show.setReportId(id);
                    show.setDatasetId(maps.get("dsId")!=null?maps.get("dsId").toString():"");
                    //分组函数的列名是函数和名称的组合
                    if(!"3".equals(maps.get("colType").toString())) {
                        show.setColId(maps.get("id") != null ? maps.get("id").toString() : "");
                    }else
                    {
                        show.setColId(maps.get("id") != null ? maps.get("id").toString()+"_"+maps.get("condition").toString() : "");
                    }
                    show.setColName(maps.get("label")!=null?maps.get("label").toString():"");
                    show.setColEnName(maps.get("fieldEnName")!=null?maps.get("fieldEnName").toString():"");
                    show.setColChEnName(maps.get("fieleChEnName")!=null?maps.get("fieleChEnName").toString():"");
                    show.setDetailOrder(Integer.parseInt(maps.get("detailOrder")!=null?maps.get("detailOrder").toString():""));
                    show.setColType(Integer.parseInt(maps.get("colType")!=null?maps.get("colType").toString():""));
                    show.setConditionName(maps.get("condition")!=null?maps.get("condition").toString():"");
                    show.setConditionValue(maps.get("conditionValue")!=null?maps.get("conditionValue").toString():"");
                    show.setConditionValue2(maps.get("conditionValue2")!=null?maps.get("conditionValue2").toString():"");
                    show.setStatues(StatusEnum.NOMAL);
                    show.setCreateDate(LocalDate.now());
                    shows.add(show);//未考虑主键问题
                    //查询条件组合
                    if(keyTemp.toString().endsWith("1"))
                    {
                        Object tempWhereObj =maps.get("condition");
                        if(tempWhereObj!=null)
                        {
                            if(">".equals(maps.get("condition")))
                            {
                                where=where+" and "+maps.get("id") +" >'"+maps.get("conditionValue")+"'";
                            }else if(">=".equals(maps.get("condition")))
                            {
                                where=where+" and "+maps.get("id") +" >='"+maps.get("conditionValue")+"'";
                            }
                            else if("=".equals(maps.get("condition")))
                            {
                                where=where+" and "+maps.get("id") +"='"+maps.get("conditionValue")+"'";
                            }
                            else if("<=".equals(maps.get("condition")))
                            {
                                where=where+" and "+maps.get("id") +" <='"+maps.get("conditionValue")+"'";
                            }
                            else if("<".equals(maps.get("condition")))
                            {
                                where=where+" and "+maps.get("id") +" <'"+maps.get("conditionValue")+"'";
                            }
                            else if("!=".equals(maps.get("condition")))
                            {
                                where=where+" and "+maps.get("id") +" !='"+maps.get("conditionValue")+"'";
                            }
                            else if("between".equals(maps.get("condition")))
                            {
                                where=where+" and "+maps.get("id") +" between '"+maps.get("conditionValue")+"' and '"+maps.get("conditionValue2")+"'";
                            }
                            else if("isnull".equals(maps.get("condition")))
                            {
                                where=where+" and "+maps.get("id") +" is null ";
                            }
                            else if("isnotnull".equals(maps.get("condition")))
                            {
                                where=where+" and "+maps.get("id") +" is not null ";
                            }
                            else if("isempty".equals(maps.get("condition")))
                            {
                                where=where+" and "+maps.get("id") +" ='' ";
                            }
                        }
                    }
                    //组装普通列
                    else if(keyTemp.toString().endsWith("2"))
                    {
                        hasNomalColumns=true;
                        columns=columns+","+maps.get("id");
                    }//组装分组函数列
                    else if(keyTemp.toString().endsWith("3"))
                    {
                        needGroupBy=true;
                        if("count".equals(maps.get("condition"))) {
                            columns = columns + ",count(" + maps.get("id")+") "+maps.get("id")+"_"+maps.get("condition").toString();
                        }else if("countdistinct".equals(maps.get("condition"))) {
                            columns = columns + ",count( distinct" + maps.get("id")+") "+maps.get("id")+"_"+maps.get("condition").toString();
                        }else if("sum".equals(maps.get("condition"))) {
                            columns = columns + ",sum( " + maps.get("id")+") "+maps.get("id")+"_"+maps.get("condition").toString();
                        }
                        else if("max".equals(maps.get("condition"))) {
                            columns = columns + ",max( " + maps.get("id")+") "+maps.get("id")+"_"+maps.get("condition").toString();
                        }
                        else if("min".equals(maps.get("condition"))) {
                            columns = columns + ",min( " + maps.get("id")+") "+maps.get("id")+"_"+maps.get("condition").toString();
                        }
                        else if("sumdistinct".equals(maps.get("condition"))) {
                            columns = columns + ",sum(distinct " + maps.get("id")+") "+maps.get("id")+"_"+maps.get("condition").toString();
                        }else if("avg".equals(maps.get("condition"))) {
                            columns = columns + ",avg( " + maps.get("id")+") "+maps.get("id")+"_"+maps.get("condition").toString();
                        }else if("avgdistinct".equals(maps.get("condition"))) {
                            columns = columns + ",avg(distinct " + maps.get("id")+") "+maps.get("id")+"_"+maps.get("condition").toString();
                        }
                    }//组装排序
                    else if(keyTemp.toString().endsWith("4"))
                    {
                        orderby=orderby+","+maps.get("id") +" "+maps.get("condition");
                    }
                }catch (Exception e){}

            }
        }
        //组装groupnby
        if(needGroupBy&hasNomalColumns) {
            for (Object keyTemp : keys) {
                if (!knowLists.contains(keyTemp)) {
                    try {
                        Map maps = (Map) JSON.parse(params.get(keyTemp.toString()).toString());
                         if(keyTemp.toString().endsWith("2"))
                        {
                            groupby=groupby+","+maps.get("id");
                        }
                    } catch (Exception e) {
                    }
                }
            }
        }
        //根据DataSetId  获取DataSet;
        QueryWrapper<SysDataset> querySysDataset=new QueryWrapper<>();
        querySysDataset.eq("id",params.get("dataSetId").toString());
        SysDataset dataset=sysDatasetService.getOne(querySysDataset);
        //如果没有选择任何数据集的列,则不保存,需要报错到前台.
        if(columns.length()<1)
        {
            //提示用户需要选择展示列
            return;
        }
        columns=columns.substring(1);
        if(groupby.length()>0)
        {
            groupby="group by "+groupby.substring(1);
        }
        if(orderby.length()>0)
        {
            orderby="order by "+orderby.substring(1);
        }
        sql=sql+columns +" from ("+dataset.getDsId()+") t1 where "+where+" "+groupby+" "+orderby;

        newReporterData.setId(id);
        newReporterData.setDataSourceId(dataset.getDbSourceId());
        newReporterData.setDataSetId(params.get("dataSetId").toString());
        newReporterData.setReportName(params.get("reportName").toString());
        newReporterData.setRemarks(params.get("remarks").toString());
        newReporterData.setReportEnName(params.get("reportEnName").toString());
        newReporterData.setReportChEnName(params.get("reportChEnName").toString());
        newReporterData.setReportSelectSql(sql);
        newReporterData.setTemplateId(params.get("templateId")!=null?params.get("templateId").toString():"");
        newReporterData.setCode(params.get("code")!=null?params.get("code").toString():"");
        newReporterData.setIsPublic(params.get("isPublic")!=null?params.get("isPublic").toString():"");
        newReporterData.setIsPrinting(params.get("isPrinting")!=null?params.get("isPrinting").toString():"");
        newReporterData.setIsExport(params.get("isExport")!=null?params.get("isExport").toString():"");
        newReporterData.setCusId(params.get("cusId")!=null?params.get("cusId").toString():"");
        newReporterData.setBranchId(dataset.getBranchId());
        newReporterData.setStatus(StatusEnum.NOMAL);
        newReporterData.setCreateDate(LocalDate.now());
        //开始保存数据
        //需要事务
        Map<String, Object> conditionDelReporterData=new HashMap<>();
        conditionDelReporterData.put("id",newReporterData.getId());
        sysReportDataService.removeByMap(conditionDelReporterData);
        sysReportDataService.save(newReporterData);
        //明细表 先删除,后添加
        Map<String, Object> conditionDelShow=new HashMap<>();
        conditionDelShow.put("report_id",newReporterData.getId());
        reportShowService.removeByMap(conditionDelShow);
        for (ReportShow needSaveShow:
             shows) {
            needSaveShow.setDatasetId(dataset.getId());
            needSaveShow.setId(UUID.randomUUID().toString());
            reportShowService.save(needSaveShow);
        }
    }






    

    //点击修改发送请求根据id查询数据
    @CrossOrigin
    @ResponseBody
    @GetMapping("queryId/{reportId}")
    public Map queryId(@PathVariable("reportId") String reportId){
        QueryWrapper<SysReportData> wrapperReportData = new QueryWrapper<>();
        wrapperReportData.eq("id",reportId);
        Map<String, Object> mapReportData=sysReportDataService.getMap(wrapperReportData);
        QueryWrapper<ReportShow> wrapperReportShow = new QueryWrapper<>();
        wrapperReportShow.eq("report_id",reportId);
        wrapperReportShow.orderByAsc("col_type");
        wrapperReportShow.orderByAsc("detail_order");
        List<ReportShow> mapReportShow=reportShowService.list(wrapperReportShow);
        List<ReportShow> data1=new ArrayList<>();
        List<ReportShow> data2=new ArrayList<>();
        List<ReportShow> data3=new ArrayList<>();
        List<ReportShow> data4=new ArrayList<>();
        for (ReportShow tempShow:mapReportShow
             ) {
            if(1==(tempShow.getColType()))
            {
                data1.add(tempShow);
            }else if(2==(tempShow.getColType()))
            {
                data2.add(tempShow);
            }else if(3==(tempShow.getColType()))
            {tempShow.setColId(tempShow.getColId().replace("_"+tempShow.getConditionName(),""));
                data3.add(tempShow);
            }else if(4==(tempShow.getColType()))
            {
                data4.add(tempShow);
            }
        }
        mapReportData.put("child1",data1);
        mapReportData.put("child2",data2);
        mapReportData.put("child3",data3);
        mapReportData.put("child4",data4);
        return mapReportData;
    }



    //    //复杂表单增加方法
//    @ResponseBody
//    @PostMapping("/addReportShow")
//    public void addDigForm(@RequestBody List<ReportShowVo> reportShowVoList){
//        iReportShowService.save1(reportShowVoList);
//    }




//    增加表单页面
//    @ResponseBody
//    @PostMapping("/adddigform")
//    public void addDigForm(@RequestBody ReportShow reportShow){
//        iReportShowService.save(reportShow);
//    }
}