package com.ciic.reporter.dbManage.service;

import com.ciic.reporter.dbManage.entity.SysDatasetDetail;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author stt
 * @since 2020-11-06
 */
public interface ISysDatasetDetailService extends IService<SysDatasetDetail> {

    /**
     * sql查询列名
     * @param sqlContent
     */
    List queryColList(String sqlContent);

    /**
     * 修改字段展示名称保存
     * @param sysDatasetDetail
     */
    void updateDatasetDetail(SysDatasetDetail sysDatasetDetail);
}
