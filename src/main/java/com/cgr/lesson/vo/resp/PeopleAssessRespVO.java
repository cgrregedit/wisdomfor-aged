package com.cgr.lesson.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 18:17 2020-06-10
 * @ Description：
 * @ Modified By：
 */
@Data
public class PeopleAssessRespVO {
    @ApiModelProperty("评估单号")
    private String id;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("性别")
    private String sex;

    @ApiModelProperty("身份证号")
    private String idcard;

    @ApiModelProperty("档案号")
    private String peopleid;

    @ApiModelProperty("开始时间")
    private Date starttime;

    @ApiModelProperty("日常生活活动等级")
    private String firstgrade;

    @ApiModelProperty("精神状态等级")
    private String secondgrade;

    @ApiModelProperty("感知觉与沟通等级")
    private String threegrade;

    @ApiModelProperty("社会参与等级")
    private String fourgrade;

    @ApiModelProperty("是否完成评测")
    private String ornot_assess;

    @ApiModelProperty("评测结果")
    private String assess_result;
}
