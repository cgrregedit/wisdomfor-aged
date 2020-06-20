package com.cgr.lesson.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class FoodDishes implements Serializable {
    private String id;

    private String foodName;

    private BigDecimal price;

    private String photo;

    private Integer piquancy;

    private Integer mouthFeel;

    private Integer taste;

    private String tabooPopulation;

    private String foodNote;

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