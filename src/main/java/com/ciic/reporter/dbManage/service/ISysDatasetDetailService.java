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
     * 根据数据集id查询客户查询、sql查询页面数据
     * @param dsId
     */
    List queryDataTableList(String dsId);

    /**
     * 查询数据集id by id
     * @param id
     * @return
     */
    List querydsIdByid(String id);
}
