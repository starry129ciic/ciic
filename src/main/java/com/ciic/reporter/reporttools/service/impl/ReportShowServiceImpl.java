package com.ciic.reporter.reporttools.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ciic.reporter.reporttools.entity.ReportShow;
import com.ciic.reporter.reporttools.entity.SysReportData;
import com.ciic.reporter.reporttools.mapper.ReportShowMapper;
import com.ciic.reporter.reporttools.mapper.SysReportDatasetDetailMapper;
import com.ciic.reporter.reporttools.mapper.SysReportDataMapper;
import com.ciic.reporter.reporttools.service.IReportShowService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ciic.reporter.reporttools.vo.ReportShowVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wuhang
 * @since 2020-11-11
 */
@Service
public class ReportShowServiceImpl extends ServiceImpl<ReportShowMapper, ReportShow> implements IReportShowService {

    @Autowired
    private ReportShowMapper reportShowMapper;

    @Autowired
    private SysReportDataMapper sysReportDataMapper;

    @Autowired
    private SysReportDatasetDetailMapper sysDatasetDetailMapper;

//    @Autowired
//    private ReportShowVo reportShowVo;

    @Override
    public ReportShow getByColId(String colid) {
        return  reportShowMapper.getByColId(colid);
    }

    @Override
    public int updateReportShow(ReportShow reportShow) {
        QueryWrapper<ReportShow> queryWrapper = new QueryWrapper<>();
        QueryWrapper<ReportShow> colId = queryWrapper.eq("col_id", reportShow.getColId());
        int i = reportShowMapper.update(reportShow,colId);
        return i;
    }

    @Override
    public void save1(List<ReportShowVo> reportShowVoList) {

//        String sql = "";
//
//        for (ReportShowVo vo : reportShowVoList){
//            sql += vo.getReportSelectSql() + " ";
//        }

        Long reportId = Long.valueOf(reportShowMapper.selectReportId());
//        System.out.println(sql);
//        String finalSql = sql;
        reportShowVoList.forEach(reportShowVo -> {
            reportShowVo.setColId(reportShowVo.getColId());
            reportShowVo.setId(String.valueOf(Long.parseLong(reportShowMapper.selectId())+1));
            reportShowVo.setReportId(String.valueOf(reportId+1));
//            reportShowVo.setReportSelectSql(finalSql);
            reportShowVo.setRemarks(reportShowVo.getReportSelectSql());
            reportShowMapper.insert(reportShowVo);
        });
        SysReportData sysReportData = new SysReportData();
        sysReportData.setId(String.valueOf(reportId+1));
        sysReportData.setReportName("报表1");
        String sql = "";
        for (ReportShowVo vo : reportShowVoList){
            if (vo.getReportSelectSql()!=null){
                sql += "select * from sys_report_data where 1=1 order by  " + vo.getReportSelectSql();
            }
        }
        sysReportData.setReportSelectSql(sql);
        sysReportDataMapper.insert(sysReportData);

    }


    //增加前台基本信息设置表传来的数据
    @Override
    public void saveForm(ReportShowVo reportShowVo) {
        
        //从数据库查询出上一个id是多少
        Long reportId = Long.valueOf(sysReportDataMapper.selectId());
        //根据上一个id加一
        reportShowVo.setId(String.valueOf(Long.parseLong(sysReportDataMapper.selectId())+1));
        reportShowVo.setReportId(String.valueOf(reportId+1));

    }

    @Override
    public void saveForm1(Object reportShowVo) {

    }


    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid.substring(0, 8) + uuid.substring(9, 13) + uuid.substring(14, 18) + uuid.substring(19, 23) + uuid.substring(24);
    }
//
//     @Override
//    public void save1(List<ReportShowVo> reportShowVoList) {
//
//        reportShowVoList.forEach(reportShowVo -> {
//            reportShowVoList.add();
//            System.out.println(reportShowVo);
//        });
//        reportShowMapper.insert(reportShowVo);
//
//    }
//    @Override
//    public void save1(ReportShowVo entity1) {
//        list().forEach(reportShow -> {
//            System.out.println(reportShow);
//        });
//    }

//    @Override
//    public void save1(List<ReportShowVo> reportShowVo) {
//        list().forEach(reportShow -> {
//            System.out.println(reportShow);
//        });
//    }

}
