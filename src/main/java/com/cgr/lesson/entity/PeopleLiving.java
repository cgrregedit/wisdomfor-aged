package com.cgr.lesson.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class PeopleLiving implements Serializable {
    private String id;

    private String pid;

    private Integer kitchenExhaust;

    private Integer fuelType;

    private Integer water;

    private Integer toilet;

    private Integer livestockFarm;

    private String createId;

    private Date createTime;

    private String updateId;

    private Date updateTime;

    private Integer deleted;

    private String remark1;

    private String remark2;

    private String remark3;

    private String remark4;

    private String remark5;
}