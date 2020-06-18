package com.cgr.lesson.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 14:36 2020-05-14
 * @ Description： 接收前端新增用户表单VO
 * @ Modified By：
 */
@Data
public class UserAddReqVO {

    @ApiModelProperty(value = "账号")
    @NotBlank(message = "账号不能为空")
    private String username;

    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空")
    private String password;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "所属部门")
    @NotBlank(message = "所属部门不能为空")
    private String deptId;

    @ApiModelProperty(value = "创建来源(1.web 2.app )")
    private String createWhere;

    @ApiModelProperty(value = "账户状态(1.正常 2.锁定 )")
    private Integer status;
}
