package com.cgr.lesson.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 14:36 2020-05-14
 * @ Description： 接收前端日志表单VO
 * @ Modified By：
 */
@Data
public class SysLogPageReqVO {

    @ApiModelProperty("当前页数")
    private Integer pageNum;

    @ApiModelProperty("当前页总数")
    private Integer pageSize;

    @ApiModelProperty(value = "用户操作动作")
    private String operation;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "账号")
    private String username;

    @ApiModelProperty(value = "开始时间")
    private String startTime;

    @ApiModelProperty(value = "结束时间")
    private String endTime;
}
