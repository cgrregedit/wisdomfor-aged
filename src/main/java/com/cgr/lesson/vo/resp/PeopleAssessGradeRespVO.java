package com.cgr.lesson.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 15:48 2020-06-10
 * @ Description：
 * @ Modified By：
 */

@Data
public class PeopleAssessGradeRespVO {
    @ApiModelProperty(value = "评估编号")
    private String id;

    @ApiModelProperty(value = "日常生活活动等级")
    private Integer firstgrade;

    @ApiModelProperty(value = "精神状态等级")
    private Integer secondgrade;

    @ApiModelProperty(value = "感知觉与沟通等级")
    private Integer threegrade;

    @ApiModelProperty(value = "社会参与等级")
    private Integer fourgrade;
}
