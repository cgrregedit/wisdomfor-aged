package com.cgr.lesson.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 21:08 2020-06-03
 * @ Description：社会参与评估表
 * @ Modified By：
 */
@Data
public class PeopleAssessFourAddReqVO {

    @ApiModelProperty(value = "个人ID")
    @NotBlank(message = "个人ID不能为空")
    private String pid;

    @ApiModelProperty(value = "生活能力")
    @NotNull(message = "生活能力得分不能为空")
    private Integer living;

    @ApiModelProperty(value = "工作能力")
    @NotNull(message = "工作能力得分不能为空")
    private Integer working;

    @ApiModelProperty(value = "时间/空间定向")
    @NotNull(message = "时间/空间定向得分不能为空")
    private Integer timeConcept;

    @ApiModelProperty(value = "人物定向")
    @NotNull(message = "人物定向得分不能为空")
    private Integer characterOrientation;

    @ApiModelProperty(value = "社会交往能力")
    @NotNull(message = "社会交往能力得分不能为空")
    private Integer socialInteraction;

}
