package com.cgr.lesson.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 23:54 2020-04-30
 * @ Description：返回用户登录信息
 * @ Modified By：
 */
@Data
public class LoginRespVO {
    @ApiModelProperty(value = "正常的业务token")
    private String accessToken;
    @ApiModelProperty(value = "刷新token")
    private String refreshToken;
    @ApiModelProperty(value = "用户id")
    private String id;
    @ApiModelProperty(value = "手机号")
    private String phone;
    @ApiModelProperty(value = "账号")
    private String username;
}
