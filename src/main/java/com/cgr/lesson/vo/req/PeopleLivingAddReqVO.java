package com.cgr.lesson.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 00:09 2020-05-29
 * @ Description：生活环境新增
 * @ Modified By：
 */

@Data
public class PeopleLivingAddReqVO {
    @ApiModelProperty(value = "外键ID")
    @NotBlank(message = "外键不能为空")
    private String pid;

    @ApiModelProperty(value = "厨房排风设施")
    private Integer kitchenExhaust;

    @ApiModelProperty(value = "燃料类型")
    private Integer fuelType;

    @ApiModelProperty(value = "饮水")
    private Integer water;

    @ApiModelProperty(value = "厕所")
    private Integer toilet;

    @ApiModelProperty("禽畜栏")
    private Integer livestockFarm;

}
