package com.cgr.lesson.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class SysDict implements Serializable {
    private String id;

    private String typeid;

    private String typename;

    private String dictid;

    private String dictname;

    private Integer deleted;

    private Date createtime;
}