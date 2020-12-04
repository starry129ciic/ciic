package com.ciic.reporter.reporttools.vo;


import java.util.List;

public class SysDataSetDetailVo {


    private String id;

     private String dbSourceId;

     private String dsId;

     private String detailId;

     private String fieldEnName;

     private String fieleChEnName;

     private String name;

     private List list;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getFieldEnName() {
        return fieldEnName;
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
}
