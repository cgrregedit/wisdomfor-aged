package com.cgr.lesson.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class PeopleAssessFirst implements Serializable {
    private String id;

    private String pid;

    private Integer eating;

    private Integer bath;

    private Integer modification;

    private Integer clothing;

    private Integer shit;

    private Integer urination;

    private Integer toilet;

    private Integer bedTransfer;

    private Integer walking;

    private Integer stairs;

    private Integer totalScore;

    private Integer grade;

    private Integer deleted;

    private String createId;

    private Date createTime;

    private String updateId;

    private Date updateTime;

    private String remark1;

    private String remark2;

    private String remark3;

    private String remark4;

    private String remark5;

}