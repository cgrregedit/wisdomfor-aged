package com.cgr.lesson.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 15:29 2020-06-15
 * @ Description：
 * @ Modified By：
 */

@Data
public class CareSettingDetailReqVO {

    @ApiModelProperty("项目编号")
    private String tpId;

    @ApiModelProperty("项目名称")
    private String tpName;

    @ApiModelProperty("项目类型")
    private String tpType;

    @ApiModelProperty("任务类型")
    private String taskTyep;

    @ApiModelProperty("执行频次")
    private String frequency;

    @ApiModelProperty("执行日期")
    private String execDate;

    @ApiModelProperty("执行时间")
    private String execTime;

}
