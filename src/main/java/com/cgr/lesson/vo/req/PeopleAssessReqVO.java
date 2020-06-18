package com.cgr.lesson.vo.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 10:31 2020-06-10
 * @ Description：
 * @ Modified By：
 */
@Data
public class PeopleAssessReqVO {
    @ApiModelProperty(value = "个人ID")
    @NotBlank(message = "个人ID不能为空")
    private String pid;

    @ApiModelProperty(value = "身份证号码")
    private String idcard;

    @ApiModelProperty(value = "开始时间")
    @NotNull(message = "开始时间不能为空！")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date startTime;
}
