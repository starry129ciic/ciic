package com.ciic.reporter.reporttools.service;

import com.ciic.reporter.reporttools.entity.ReportShowVoEntity;
import com.ciic.reporter.reporttools.entity.ReportShow;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ciic.reporter.reporttools.vo.ReportShowVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wuhang
 * @since 2020-11-11
 */
public interface IReportShowService extends IService<ReportShow> {


    ReportShow getByColId(String colid);

    int updateReportShow(ReportShow reportShow);

    void save1(List<ReportShowVo> reportShowVoList);

    void saveForm(ReportShowVo reportShowVo);

    void saveForm1(Object reportShowVo);


//
//    void save1(ReportShowVo entity1);
}
