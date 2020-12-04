package com.ciic.reporter.reporttools.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;


@Data
@EqualsAndHashCode(callSuper = false)
public class ReportShowVo {

    private static final long serialVersionUID = 1L;

    private String reportId;

    private String id;

    private String datasourceId;

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

    private String reportSelectSql;


    private String dbSourceId;

    private String dsId;

    private String detailId;

    private String fieldEnName;

    private String fieleChEnName;

    private String name;

    private List list;

    //报表名称
    private String reportName;

    private String reportEnName;

    private String reportChEnName;

    private String templateId;

    private String code;

    //公私有
    private String isPublic;

    //停用启用删除
    private String status;


    private String cusId;

    private String cusName;

    private String branchId;

    private String dataSetId;


    private String isUpdate;

    private String isPrinting;

    private String isExport;

    private String isPage;

    private Integer pageLines;

    private String customHeaderId;

    private String customFooterId;

    private String customHeader;

    private String customFooter;

    private String term;

    public String getDataSetId() {
        return dataSetId;
    }

    public void setDataSetId(String dataSetId) {
        this.dataSetId = dataSetId;
    }

    public String getDbSourceId() {
        return dbSourceId;
    }

    public void setDbSourceId(String dbSourceId) {
        this.dbSourceId = dbSourceId;
    }

    public String getDsId() {
        return dsId;
    }

    public void setDsId(String dsId) {
        this.dsId = dsId;
    }

    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }


    public void setFieldEnName(String fieldEnName) {
        this.fieldEnName = fieldEnName;
    }

    public String getFieleChEnName() {
        return fieleChEnName;
    }

    public void setFieleChEnName(String fieleChEnName) {
        this.fieleChEnName = fieleChEnName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getReportEnName() {
        return reportEnName;
    }

    public void setReportEnName(String reportEnName) {
        this.reportEnName = reportEnName;
    }

    public String getReportChEnName() {
        return reportChEnName;
    }

    public void setReportChEnName(String reportChEnName) {
        this.reportChEnName = reportChEnName;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(String isPublic) {
        this.isPublic = isPublic;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(String isUpdate) {
        this.isUpdate = isUpdate;
    }

    public String getIsPrinting() {
        return isPrinting;
    }

    public void setIsPrinting(String isPrinting) {
        this.isPrinting = isPrinting;
    }

    public String getIsExport() {
        return isExport;
    }

    public void setIsExport(String isExport) {
        this.isExport = isExport;
    }

    public String getIsPage() {
        return isPage;
    }

    public void setIsPage(String isPage) {
        this.isPage = isPage;
    }

    public Integer getPageLines() {
        return pageLines;
    }

    public void setPageLines(Integer pageLines) {
        this.pageLines = pageLines;
    }

    public String getCustomHeaderId() {
        return customHeaderId;
    }

    public void setCustomHeaderId(String customHeaderId) {
        this.customHeaderId = customHeaderId;
    }

    public String getCustomFooterId() {
        return customFooterId;
    }

    public void setCustomFooterId(String customFooterId) {
        this.customFooterId = customFooterId;
    }

    public String getCustomHeader() {
        return customHeader;
    }

    public void setCustomHeader(String customHeader) {
        this.customHeader = customHeader;
    }

    public String getCustomFooter() {
        return customFooter;
    }

    public void setCustomFooter(String customFooter) {
        this.customFooter = customFooter;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getReportId() {
        return reportId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getDatasourceId() {
        return datasourceId;
    }

    public void setDatasourceId(String datasourceId) {
        this.datasourceId = datasourceId;
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

    public String getReportSelectSql() {
        return reportSelectSql;
    }

    public void setReportSelectSql(String reportSelectSql) {
        this.reportSelectSql = reportSelectSql;
    }
}
