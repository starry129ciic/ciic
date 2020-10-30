package com.ciic.reporter.db1.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 报表主表
 * </p>
 *
 * @author caoxh
 * @since 2020-10-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RpMain implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId
    private String id;
    /**
     * 自定义查询编号
     */
    private String code;

    /**
     * 自定义查询名称
     */
    private String cnname;

    /**
     * 自定义查询英文名称
     */
    private String enname;

    /**
     * 自定义查询使用的模板
     */
    private String templateId;

    /**
     * 客户组
     */
    private String cusGroupId;

    /**
     * 客户
     */
    private String cusId;

    /**
     * 业务员
     */
    private String operatorId;

    /**
     * 薪酬组
     */
    private String payGroupId;

    /**
     * HR
     */
    private String hrId;

    /**
     * 雇员
     */
    private String empId;

    /**
     * 从哪个数据库中读取数据
     */
    private String datasourceId;

    /**
     * 输出数据之前需要执行的语句
     */
    private String beSql;

    /**
     * 输出数据需要执行的语句
     */
    private String mainSql;

    /**
     * 输出数据之后需要执行的语句
     */
    private String endSql;

    /**
     * 是否分页
     */
    private Integer ispage;

    /**
     * 默认分页每页展示行数
     */
    private Integer defPageNum;

    /**
     * 是否打印
     */
    private Integer isprint;

    /**
     * 是否导出
     */
    private Integer isexport;

    /**
     * 状态（0正常 1删除 2停用）
     */
    private String status;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private LocalDateTime updateDate;

    /**
     * 备注信息
     */
    private String remarks;

    /**
     * 表头HTML
     */
    private String headdiv;

    /**
     * 表尾HTML
     */
    private String footdiv;

    /**
     * 英文表头HTML
     */
    private String enheaddiv;

    /**
     * 英文表尾HTML
     */
    private String enfootdiv;

    /**
     * 中英文表头HTML
     */
    private String cnenheaddiv;

    /**
     * 中英文表尾HTML
     */
    private String cnenfootdiv;


}
