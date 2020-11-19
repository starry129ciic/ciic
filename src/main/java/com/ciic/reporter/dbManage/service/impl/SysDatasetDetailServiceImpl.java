package com.ciic.reporter.dbManage.service.impl;

import com.ciic.reporter.dbManage.entity.SysDatasetDetail;
import com.ciic.reporter.dbManage.mapper.SysDatasetDetailMapper;
import com.ciic.reporter.dbManage.service.ISysDatasetDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author caoxh
 * @since 2020-11-06
 */
@Service
public class SysDatasetDetailServiceImpl extends ServiceImpl<SysDatasetDetailMapper, SysDatasetDetail> implements ISysDatasetDetailService {

    @Autowired
    private SysDatasetDetailMapper sysDatasetDetailMapper;

    @Override
    public List queryColList(String sqlContent) {
        List colList = sysDatasetDetailMapper.queryColList(sqlContent);
        return colList;
    }

    @Override
    public void updateDatasetDetail(SysDatasetDetail sysDatasetDetail) {
        //主键id
        String id = sysDatasetDetail.getId();
        //字段标识
        String fieldId = sysDatasetDetail.getFieldId();
        //字段名称
        String fieldName = sysDatasetDetail.getFieldName();
        //字段英文名称
        String fieldEnName = sysDatasetDetail.getFieldEnName();
        //字段中英文名称
        String fieldChEnName = sysDatasetDetail.getFieldChEnName();
        sysDatasetDetailMapper.updateDatasetDetail(fieldId, fieldName, fieldEnName, fieldChEnName, id);
    }

}
