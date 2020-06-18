package com.cgr.lesson.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysUser implements Serializable {
    private String id;

    private String username;

    private String salt;

    private String password;

    private String phone;

    private String deptId;

    private String realName;

    private String nickName;

    private String email;

    private Integer status;

    private Integer sex;

    private Integer deleted;

    private String createId;

    private String updateId;

    private Integer createWhere;

    private Date createTime;

    private Date updateTime;

    private String deptName;
}