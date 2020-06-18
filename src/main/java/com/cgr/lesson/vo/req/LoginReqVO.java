package com.cgr.lesson.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 23:49 2020-04-30
 * @ Description：用于接受登录信息
 * @ Modified By：
 */
@Data
public class LoginReqVO {
    @ApiModelProperty(value = "账号")
    private String username;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "登录类型(1-pc  2-app)")
    @NotBlank(message = "登录类型不能为空！")
    private String type;
}
