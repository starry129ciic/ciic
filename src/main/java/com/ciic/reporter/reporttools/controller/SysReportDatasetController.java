package com.ciic.reporter.reporttools.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ciic.reporter.common.StatusEnum;
import com.ciic.reporter.reporttools.entity.ReportShow;
import com.ciic.reporter.reporttools.entity.SysDataset;
import com.ciic.reporter.reporttools.entity.SysReportData;
import com.ciic.reporter.reporttools.service.ISysDatasetService;
import com.ciic.reporter.reporttools.vo.SysDataSetDetailVo;
import com.ciic.reporter.service.IDataService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
 * @since 2020-11-17
 */
@RestController
@RequestMapping("/reporttools/sys-dataset")
public class SysReportDatasetController {

    @Autowired
    private ISysDatasetService sysDatasetService;

    @Autowired
    IDataService dataService;

    @GetMapping("/query")
    @ResponseBody
    public List queryDataset(){
        List list = sysDatasetService.queryDataSetList();
        return list;
    }

    @PostMapping("/queryTable")
    @ResponseBody
    public String queryTable(@RequestBody Object queryTable){
        HashMap<String,Object> params=(LinkedHashMap<String,Object>)queryTable;
        SysReportData newReporterData=new SysReportData();
        //报表的主键

        String sql="select ";
        Set keys=params.keySet();

        List<String> knowLists=new ArrayList<String>();
        String[] knowKeys={"reportId","reportName","reportEnName","reportChEnName","remarks","code","isPublic","isPrinting","isExport","cusId","isModelPrinting"};
        CollectionUtils.addAll(knowLists,knowKeys);
        String columns="";
        String where ="1=1";
        String groupby="";
        String orderby="";
        Boolean needGroupBy=false,hasNomalColumns=false;//只有当同时存在分组函数和普通列才需要group by
        List<ReportShow> shows=new ArrayList<ReportShow>();
        for (Object keyTemp:keys) {
            if(!knowLists.contains(keyTemp))
            {
                try {
                    Map maps = (Map) JSON.parse(params.get(keyTemp.toString()).toString());
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
                        columns=columns+","+maps.get("id") +" "+(maps.get("label")!=null?maps.get("label").toString():maps.get("id").toString());
                    }//组装分组函数列
                    else if(keyTemp.toString().endsWith("3"))
                    {
                        needGroupBy=true;
                        if("count".equals(maps.get("condition"))) {
                            columns = columns + ",count(" + maps.get("id")+") "+" 计数"+(maps.get("label")!=null?maps.get("label").toString():maps.get("id").toString());
                        }else if("countdistinct".equals(maps.get("condition"))) {
                            columns = columns + ",count( distinct" + maps.get("id")+") "+" 去重计数"+(maps.get("label")!=null?maps.get("label").toString():maps.get("id").toString());
                        }else if("sum".equals(maps.get("condition"))) {
                            columns = columns + ",sum( " + maps.get("id")+") "+" 求和"+(maps.get("label")!=null?maps.get("label").toString():maps.get("id").toString());
                        }
                        else if("max".equals(maps.get("condition"))) {
                            columns = columns + ",max( " + maps.get("id")+") "+" 最大"+(maps.get("label")!=null?maps.get("label").toString():maps.get("id").toString());
                        }
                        else if("min".equals(maps.get("condition"))) {
                            columns = columns + ",min( " + maps.get("id")+") "+" 最小"+(maps.get("label")!=null?maps.get("label").toString():maps.get("id").toString());
                        }
                        else if("sumdistinct".equals(maps.get("condition"))) {
                            columns = columns + ",sum(distinct " + maps.get("id")+") "+" 去重求和"+(maps.get("label")!=null?maps.get("label").toString():maps.get("id").toString());
                        }else if("avg".equals(maps.get("condition"))) {
                            columns = columns + ",avg( " + maps.get("id")+") "+" 平均"+(maps.get("label")!=null?maps.get("label").toString():maps.get("id").toString());
                        }else if("avgdistinct".equals(maps.get("condition"))) {
                            columns = columns + ",avg(distinct " + maps.get("id")+") "+" 去重平均"+(maps.get("label")!=null?maps.get("label").toString():maps.get("id").toString());
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
        if(dataset==null|| StringUtils.isEmpty(dataset.getDbSourceId()))
        {
            return "<span>查询数据失败</span>";
        }
        //如果没有选择任何数据集的列,则不保存,需要报错到前台.
        if(columns.length()<1)
        {
            //提示用户需要选择展示列
            return "<span>无预览数据</span>";
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
        sql=sql+columns +" from ("+dataset.getDsId()+") t1 where "+where+" "+groupby+" "+orderby +" limit 0,1";

        List<Map<String, Object>> dataList = dataService.getData(dataset.getDbSourceId(), sql);
        if(dataList==null|| dataList.size()<1)
        {
            return "<span>查询数据失败</span>";
        }else {
            Map<String, Object> mapResult=dataList.get(0);
            Set keyResults=mapResult.keySet();
            String resultHtml="<table border='1px' width='90%'><tr>";
            for(Object keyResult:keyResults)
            {
                resultHtml+="<th>"+keyResult+"</th>";
            }
            resultHtml+="</tr><tr>";
            for(Object keyResult:keyResults)
            {
                resultHtml+="<td>"+mapResult.get(keyResult)+"</td>";
            }
            resultHtml+="</tr></table>";
            return resultHtml;
        }
    }


    @GetMapping("/querytree/{dsType}/{cid}")
    public List queryTree(@PathVariable("dsType")  String dsType,@PathVariable("cid") String cid){
        //当类型为客户查询的时候,使用接口返回列指标数据项
        if (dsType == "4"){
            List tree2List = sysDatasetService.queryTree2List();
            return tree2List;
        }

        System.out.println("前端传来的id是"+cid);
        List queryTree1List = sysDatasetService.queryTree1List(cid);

        List<SysDataSetDetailVo> resultVO=new ArrayList<SysDataSetDetailVo>();
        if (queryTree1List != null && queryTree1List.size()>0) {
            String id="";
            String label="";
            SysDataSetDetailVo vo=new SysDataSetDetailVo();
            for (int i=0;i<queryTree1List.size();i++) {
                Map map = (Map)queryTree1List.get(i);
                if(id.isEmpty()||!id.equals((String)map.get("ds_id")))
                {
                    id=(String)map.get("ds_id");
                    label=(String)map.get("ds_name");
                    vo=new SysDataSetDetailVo();
                    vo.setId((String)map.get("id"));
                    vo.setName((String)map.get("ds_name"));
                    vo.setDbSourceId((String)map.get("db_source_id"));
                    ArrayList<SysDataSetDetailVo> vop = new ArrayList<>();
                    vo.setList(vop);
                    resultVO.add(vo);
                }
                SysDataSetDetailVo voChild=new SysDataSetDetailVo();
                voChild.setId((String)map.get("field_id"));
                voChild.setName((String)map.get("field_name"));
                voChild.setDetailId((String)map.get("detial_id"));
                voChild.setFieldEnName((String)map.get("field_en_name"));
                voChild.setFieleChEnName((String)map.get("field_ch_en_name"));
                vo.getList().add(voChild);
            }
        }
        return resultVO;
    }


}
