package com.cgr.lesson.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 15:37 2020-05-29
 * @ Description：生活习惯编辑接收类
 * @ Modified By：
 */
@Data
public class PeopleLivingUpdateReqVO {
    @ApiModelProperty(value = "ID")
    @NotBlank(message = "主键不能为空")
    private String id;

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
