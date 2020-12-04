package com.ciic.reporter.reporttools.mapper;

import com.ciic.reporter.reporttools.entity.SysReportData;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ciic.reporter.reporttools.vo.ReportShowVo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wuhang
 * @since 2020-11-06
 */
public interface SysReportDataMapper extends BaseMapper<SysReportData> {

    @Select("SELECT d.*,m.* FROM cus_main m,sys_report_data d WHERE d.id=m.id")
    List<SysReportData> selectReportData();



    void insertFrom(ReportShowVo reportShowVo);

    String selectId();


    SysReportData getByCusId(String cusId);
}
