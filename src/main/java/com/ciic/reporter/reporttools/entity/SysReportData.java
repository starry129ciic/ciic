package com.ciic.reporter.reporttools.entity;

import java.time.LocalDate;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author wuhang
 * @since 2020-11-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysReportData implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    //报表名称
    private String reportName;

    private String reportEnName;

    private String reportChEnName;

    private String templateId;

    private String code;

    private String reportSelectSql;

    //公私有
    private String isPublic;

    //停用启用删除
    private String status;

    private String createBy;

    private LocalDate createDate;

    private String updateBy;

    private LocalDate updateDate;

    private String cusId;

    private String cusName;

    private String branchId;

    private String remarks;

    private String isUpdate;

    private String isPrinting;

    private String isExport;

    private String isPage;

    private Integer pageLines;

    private String customHeaderId;

    private String customFooterId;

    private String customHeader;

    private String customFooter;

    private String dataSetId;

    private String dataSourceId;


    public String getDataSourceId() {
        return dataSourceId;
    }

    public void setDataSourceId(String dataSourceId) {
        this.dataSourceId = dataSourceId;
    }


    public String getDataSetId() {
        return dataSetId;
    }

    public void setDataSetId(String dataSetId) {
        this.dataSetId = dataSetId;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
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

    public String getReportSelectSql() {
        return reportSelectSql;
    }

    public void setReportSelectSql(String reportSelectSql) {
        this.reportSelectSql = reportSelectSql;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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
}
