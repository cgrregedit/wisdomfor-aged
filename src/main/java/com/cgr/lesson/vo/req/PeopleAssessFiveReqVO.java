package com.cgr.lesson.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 21:04 2020-06-10
 * @ Description：
 * @ Modified By：
 */

@Data
public class PeopleAssessFiveReqVO {
    @ApiModelProperty("评估编号")
    @NotBlank(message = "评估编号不能为空")
    private String pid;

    @ApiModelProperty(value = "")
    @NotNull(message = "日常生活活动等级不能为空")
    private Integer firstGrade;

    @ApiModelProperty(value = "精神状态等级")
    @NotNull(message = "精神状态等级不能为空")
    private Integer secondGrade;

    @ApiModelProperty(value = "感知觉与沟通")
    @NotNull(message = "感知觉与沟通等级不能为空")
    private Integer threeGrade;

    @ApiModelProperty(value = "社会参与等级")
    @NotNull(message = "社会参与等级不能为空")
    private Integer fourGrade;

    @ApiModelProperty(value = "长者能力初步等级")
    @NotNull(message = "长者能力初步等级不能为空")
    private Integer initialGrade;

    @ApiModelProperty(value = "长者能力最终等级")
    @NotNull(message = "长者邓丽最终等级不能为空")
    private Integer finalGrade;
}
