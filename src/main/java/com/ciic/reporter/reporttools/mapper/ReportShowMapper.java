package com.ciic.reporter.reporttools.mapper;

import com.ciic.reporter.reporttools.entity.ReportShow;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ciic.reporter.reporttools.vo.ReportShowVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wuhang
 * @since 2020-11-11
 */
public interface ReportShowMapper extends BaseMapper<ReportShow> {

    ReportShow getByColId(String colid);

//    void updateReportShow(@Param("id") String colId,@Param("colName") String colName,@Param("colEnName") String colEnName,@Param("colChEnName") String colChEnName);

    int updateByColId(ReportShow reportShow);

    void insert(ReportShowVo reportShowVo);

    String selectId();

    String selectReportId();
}
