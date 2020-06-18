package com.cgr.lesson.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 12:34 2020-05-30
 * @ Description：入院评估一
 * @ Modified By：
 */
@Data
public class PeopleAssessFirstAddReqVO {
    @NotBlank(message = "评估编号不能为空")
    @ApiModelProperty(value = "评估编号")
    private String pid;

    @NotNull(message = "请对进食项进行评估")
    @ApiModelProperty(value = "进食")
    private Integer eating;

    @NotNull(message = "请对洗澡项进行评估")
    @ApiModelProperty(value = "洗澡")
    private Integer bath;

    @NotNull(message = "请对修饰项进行评估")
    @ApiModelProperty(value = "修饰")
    private Integer modification;

    @NotNull(message = "请对穿衣项进行评估")
    @ApiModelProperty(value = "穿衣")
    private Integer clothing;

    @NotNull(message = "请对大便控制项进行评估")
    @ApiModelProperty(value = "大便控制")
    private Integer shit;

    @NotNull(message = "请对小便项进行评估")
    @ApiModelProperty(value = "小便控制")
    private Integer urination;

    @NotNull(message = "请对如厕项进行评估")
    @ApiModelProperty(value = "如厕")
    private Integer toilet;

    @NotNull(message = "请对床椅移动项进行评估")
    @ApiModelProperty(value = "床椅移动")
    private Integer bedTransfer;

    @NotNull(message = "请对平地行走项进行评估")
    @ApiModelProperty(value = "平地行走")
    private Integer walking;

    @NotNull(message = "请对上下楼梯项进行评估")
    @ApiModelProperty(value = "上下楼梯")
    private Integer stairs;
}
