package com.cgr.lesson.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class PeoplePastHistory implements Serializable {
    private String id;

    private String pid;

    private Integer diseaseHistory;

    private Integer operationHistory;

    private Integer traumaHistory;

    private Integer bunkoHistory;

    private Integer fatherDisease;

    private Integer montherDisease;

    private Integer brotherDisease;

    private Integer childrenDisease;

    private Integer heredityDisease;

    private Integer disability;

    private String disabilityText;

    private String disabilityNum;

    private Integer disabilityGrade;

    private String createId;

    private Date createTime;

    private String updateId;

    private Date updateTime;

    private String remark1;

    private String remark2;

    private String remark3;

    private String remark4;

    private Integer deleted;
}