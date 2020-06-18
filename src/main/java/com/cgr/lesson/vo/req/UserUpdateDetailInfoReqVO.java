package com.cgr.lesson.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 11:32 2020-05-14
 * @ Description：更新自己的用户信息接收类
 * @ Modified By：
 */
@Data
public class UserUpdateDetailInfoReqVO {
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "性别(1.男 2.女)")
    private Integer sex;
    @ApiModelProperty(value = "真实名称")
    private String realName;
    @ApiModelProperty(value = "手机号")
    private String phone;
    @ApiModelProperty(value = "账户状态(1.正常 2.锁定 )")
    private Integer status;
}
