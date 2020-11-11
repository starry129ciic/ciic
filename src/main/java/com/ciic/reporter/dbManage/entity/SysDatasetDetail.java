package com.ciic.reporter.dbManage.entity;

import java.time.LocalDate;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

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
public class SysDatasetDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    private String dsId;//数据集id

    private String tableId;//表id

    private String fieldId;//字段编码

    private String fieldName;//字段名称

    private String fieldEnName;//字段英文名称

    private String fieldChEnName;//字段中文英文名称

    private String sort;//排序

    private String status;//状态

    private String createBy;//创建者

    private LocalDate createDate;//创建时间

    private String updateBy;//更新人

    private LocalDate updateDate;//更新时间

    private String remarks;//备注

    @TableField(exist = false)
    private String sqlContent;//sql解析
}
