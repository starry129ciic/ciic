package com.ciic.reporter.reporttools.mapper;

import com.ciic.reporter.reporttools.entity.SysDatasetDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jdk.nashorn.internal.objects.AccessorPropertyDescriptor;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wuhang
 * @since 2020-11-17
 */
public interface SysReportDatasetDetailMapper extends BaseMapper<SysDatasetDetail> {

    SysDatasetDetail selectColName(String colId);
}
