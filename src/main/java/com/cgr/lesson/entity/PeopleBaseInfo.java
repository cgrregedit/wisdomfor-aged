package com.cgr.lesson.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class PeopleBaseInfo implements Serializable {
    private String id;

    private String name;

    private String peopleId;

    private Integer sex;

    private Date birthday;

    private String idCard;

    private String workPlace;

    private String phone;

    private String contactsName;

    private String contactsPhone;

    private Integer residenceType;

    private String nation;

    private Integer blood;

    private Integer bloodRh;

    private Integer education;

    private Integer profession;

    private Integer maritalStatus;

    private Integer payType;

    private String payText;

    private Integer allergicHistory;

    private Integer exposureHistory;

    private String medicalCard;

    private String remark1;

    private String remark2;

    private String remark3;

    private String remark4;

    private String remark5;

    private String createId;

    private Date createTime;

    private String updateId;

    private Date updateTime;

    private Integer deleted;

    private String remark6;
}