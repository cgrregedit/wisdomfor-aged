package com.cgr.lesson.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 14:36 2020-05-14
 * @ Description： 接收前端编辑科室表单VO
 * @ Modified By：
 */
@Data
public class DeptUpdateReqVO {
    @ApiModelProperty(value = "部门id")
    @NotBlank(message = "部门id不能为空")
    private String id;
    @ApiModelProperty(value = "部门呢名称")
    private String name;
    @ApiModelProperty(value = "父级id")
    private String pid;
    @ApiModelProperty(value = "部门状态(1:正常；0:弃用)")
    private Integer status;
    @ApiModelProperty(value = "部门经理名称")
    private String managerName;
    @ApiModelProperty(value = "部门经理电话")
    private String phone;
}
