package com.ciic.reporter.cusmain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.persistence.Table;

@Data
@Table(name ="cus_main")
public class CusMain {

    @TableId
    private String id;

    private String name;

    private String serial;

    private String report;

    private String server;


}
