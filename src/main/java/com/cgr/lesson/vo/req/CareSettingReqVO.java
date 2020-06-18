package com.cgr.lesson.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 15:33 2020-06-15
 * @ Description：护理级别
 * @ Modified By：
 */

@Data
public class CareSettingReqVO {

    @ApiModelProperty("护理级别名称")
    @NotBlank(message = "护理名称不能为空")
    private String careName;

    @ApiModelProperty("护理对象")
    private String careObj;

    @ApiModelProperty("护理费用")
    private BigDecimal careCost;

    @ApiModelProperty("说明")
    private String careExplain;

    @ApiModelProperty("执行计划")
    @NotEmpty(message = "执行计划不能为空")
    private List<CareSettingDetailReqVO> careSettingDetail;
}
