package com.cgr.lesson.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;


/**
* @Description:  接收用户角色表单
* @Param:
* @return:
* @Author:cgr
* @Date: 2020-05-19
*/
@Data
public class UserOwnRoleReqVO {
    @ApiModelProperty(value = "用户id")
    @NotBlank(message = "用户id不能为空")
    private String userId;
    @ApiModelProperty("赋予用户的角色id集合")
    private List<String> roleIds;


}
