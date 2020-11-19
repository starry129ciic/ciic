package com.ciic.reporter.dbManage.service;

import com.ciic.reporter.dbManage.entity.SysDatasource;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author stt
 * @since 2020-11-10
 */
public interface ISysDatasourceService extends IService<SysDatasource> {
    /**
     * 根据数据集id,查询数据源
     * @return
     */
    List queryDatasourceList(String dataSetId);

    /**
     * 查询数据库编码
     * @return
     */
    List queryCodeList();

    /**
     * 通过数据源编码加载页面信息
     * @param code
     * @return
     */
    List queryDataSourceByCode(String code);
}
