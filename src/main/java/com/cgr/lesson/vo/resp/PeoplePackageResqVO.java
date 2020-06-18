package com.cgr.lesson.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 12:27 2020-06-16
 * @ Description：套餐生成列表查询返回类
 * @ Modified By：
 */

@Data
public class PeoplePackageResqVO {
    @ApiModelProperty("个人ID")
    private String id;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("档案号")
    private String peopleid;

    @ApiModelProperty("性别")
    private String sex;

    @ApiModelProperty("身份证号")
    private String idcard;

    @ApiModelProperty("电话")
    private String phone;

    @ApiModelProperty("评估结果")
    private String assessresult;

    @ApiModelProperty("护理级别ID")
    private String careid;

    @ApiModelProperty("护理级别名称")
    private String carename;
}
