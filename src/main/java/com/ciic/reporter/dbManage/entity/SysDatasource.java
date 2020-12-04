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
 * @since 2020-11-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysDatasource implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String dbName;

    private String dbCode;

    private String dbLink;

    private String dbUsername;

    private String dbPassword;

    private String dbDriver;

    private String dbInitalLinkCounts;

    private String dbMinimumLinkCounts;

    private String dbMaximumLinkCounts;

    private String status;

    private String createBy;

    private LocalDate createDate;

    private String updateBy;

    private LocalDate updateDate;

    private String remarks;


}
