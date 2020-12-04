package com.ciic.reporter.reporttools.controller;


import com.ciic.reporter.reporttools.service.ISysDatasetService;
import com.ciic.reporter.reporttools.vo.SysDataSetDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/query")
    @ResponseBody
    public List queryDataset(){
        List list = sysDatasetService.queryDataSetList();
        return list;
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
