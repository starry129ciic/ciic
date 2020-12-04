package com.ciic.reporter.reporttools.entity;

import java.time.LocalDate;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author wuhang
 * @since 2020-11-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ReportShow implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String reportId;

    private String datasetId;

    private String colId;

    private String colName;

    private String colEnName;

    private String colChEnName;

    private String pColName;


    /**
     * 显示顺序
     */
    private Integer detailOrder;

    /**
     * 类型：1查询，2展示列，3汇总列，4排序列
     */
    private Integer colType;

    /**
     * 对应字典表名
     */
    private String dictTableName;

    /**
     * 条件框
     */
    private String edit;

    /**
     * #selectSex  edit:"radio"时templet:"爬山:ps|跳舞:tw" 等
     */
    private String templet;

    /**
     * 验证方式如：number，phone，email
     */
    @TableField("layVerify")
    private String layVerify;

    private String statues;

    private String createBy;

    private LocalDate createDate;

    private String updateBy;

    private LocalDate updateDate;

    private String remarks;

    private String conditionName;
    private  String conditionValue;
    private  String conditionValue2;


    public String getConditionName() {
        return conditionName;
    }

    public void setConditionName(String conditionName) {
        this.conditionName = conditionName;
    }

    public String getConditionValue2() {
        return conditionValue2;
    }

    public void setConditionValue2(String conditionValue2) {
        this.conditionValue2 = conditionValue2;
    }

    public String getConditionValue() {
        return conditionValue;
    }

    public void setConditionValue(String conditionValue) {
        this.conditionValue = conditionValue;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }


    public String getDatasetId() {
        return datasetId;
    }

    public void setDatasetId(String datasetId) {
        this.datasetId = datasetId;
    }

    public String getColId() {
        return colId;
    }

    public void setColId(String colId) {
        this.colId = colId;
    }

    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName;
    }

    public String getColEnName() {
        return colEnName;
    }

    public void setColEnName(String colEnName) {
        this.colEnName = colEnName;
    }

    public String getColChEnName() {
        return colChEnName;
    }

    public void setColChEnName(String colChEnName) {
        this.colChEnName = colChEnName;
    }

    public String getpColName() {
        return pColName;
    }

    public void setpColName(String pColName) {
        this.pColName = pColName;
    }

    public Integer getDetailOrder() {
        return detailOrder;
    }

    public void setDetailOrder(Integer detailOrder) {
        this.detailOrder = detailOrder;
    }

    public Integer getColType() {
        return colType;
    }

    public void setColType(Integer colType) {
        this.colType = colType;
    }

    public String getDictTableName() {
        return dictTableName;
    }

    public void setDictTableName(String dictTableName) {
        this.dictTableName = dictTableName;
    }

    public String getEdit() {
        return edit;
    }

    public void setEdit(String edit) {
        this.edit = edit;
    }

    public String getTemplet() {
        return templet;
    }

    public void setTemplet(String templet) {
        this.templet = templet;
    }

    public String getLayVerify() {
        return layVerify;
    }

    public void setLayVerify(String layVerify) {
        this.layVerify = layVerify;
    }

    public String getStatues() {
        return statues;
    }

    public void setStatues(String statues) {
        this.statues = statues;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
