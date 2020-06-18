package com.cgr.lesson.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 17:10 2020-06-16
 * @ Description：
 * @ Modified By：
 */
@Data
public class PeoplePackageUpdateReqVO {
    @ApiModelProperty("护理级别ID")
    @NotBlank(message = "护理级别ID不能为空")
    private String careId;

    @ApiModelProperty("开始时间")
    @NotNull(message = "开始时间不能为空")
    private Date startTime;
}
