package com.cgr.lesson.vo.resp;

import com.cgr.lesson.entity.SysRole;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


/** 
* @Description: 用户角色相应前端VO
* @Param:
* @return:  
* @Author:cgr 
* @Date: 2020-05-19 
*/
@Data
public class UserOwnRoleRespVO {

    @ApiModelProperty(value = "用户ID")
    private String id;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "拥有角色集合")
    private List<String> ownRoles;

    @ApiModelProperty(value = "所有角色列表")
    private List<SysRole> allRole;
}
