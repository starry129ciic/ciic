package com.ciic.reporter.dbManage.service.impl;

import com.ciic.reporter.dbManage.entity.SysDatasource;
import com.ciic.reporter.dbManage.mapper.SysDatasourceMapper;
import com.ciic.reporter.dbManage.service.ISysDatasourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author stt
 * @since 2020-11-10
 */
@Service
public class SysDatasourceServiceImpl extends ServiceImpl<SysDatasourceMapper, SysDatasource> implements ISysDatasourceService {
    @Autowired
    private SysDatasourceMapper sysDatasourceMapper;

    @Override
    public List queryDatasourceList(String dataSetId) {
        List dbList = sysDatasourceMapper.queryDatasourceList(dataSetId);
        return dbList;
    }

    @Override
    public List queryCodeList() {
        List codeList = sysDatasourceMapper.queryCodeList();
        return codeList;
    }

    @Override
    public List queryDataSourceByCode(String code) {
        List dbList = sysDatasourceMapper.queryDataSourceByCode(code);
        return dbList;
    }

}
