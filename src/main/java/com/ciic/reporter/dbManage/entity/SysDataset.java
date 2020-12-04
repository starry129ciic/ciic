package com.ciic.reporter.dbManage.entity;

import java.time.LocalDate;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author stt
 * @since 2020-11-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysDataset implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;//主键id

    private String dbSourceId;//数据源id

    private String dsId;//数据集id

    private String dsName;//数据集名称

    private String dsType;//数据集类型

    private String sort;//排序

    private String status;//状态

    private String createBy;//创建者

    private LocalDate createDate;//创建时间

    private String updateBy;//更新人

    private LocalDate updateDate;//更新时间

    private String remarks;//备注

    private String cusId;//客户id

    private String cusName;//客户名称

    private String branchId;

}
