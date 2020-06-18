package com.cgr.lesson.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 21:41 2020-06-01
 * @ Description：
 * @ Modified By：
 */

@Data
public class PeopleAssessSecondAddReqVO {
    @ApiModelProperty(value = "评估编号")
    @NotBlank(message = "评估编号不能为空！")
    private String pid;

    @ApiModelProperty(value = "认知功能")
    private Integer cognitive;

    @ApiModelProperty(value = "攻击行为")
    private Integer attacks;

    @ApiModelProperty(value = "精神状态")
    private Integer symptoms;
}
