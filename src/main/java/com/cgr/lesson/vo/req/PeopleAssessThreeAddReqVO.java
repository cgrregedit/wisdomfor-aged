package com.cgr.lesson.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 10:33 2020-06-02
 * @ Description：
 * @ Modified By：
 */

@Data
public class PeopleAssessThreeAddReqVO {
    @ApiModelProperty(value = "评估编号")
    @NotBlank(message = "评估编号不能为空")
    private String pid;

    @ApiModelProperty(value = "意识水平")
    private Integer awarenessLevel;

    @ApiModelProperty(value = "视力")
    private Integer visualAcuity;

    @ApiModelProperty(value = "听力")
    private Integer hearing;

    @ApiModelProperty(value = "沟通交流")
    private Integer communication;

}
