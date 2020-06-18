package com.cgr.lesson.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 14:36 2020-05-14
 * @ Description： 接收前端角色编辑VO
 * @ Modified By：
 */
@Data
public class RoleUpdateReqVO {
    @ApiModelProperty(value = "角色id")
    @NotBlank(message = "角色 id 不能为空")
    private String id;

    @ApiModelProperty(value = "角色名称")
    private String name;

    @ApiModelProperty(value = "角色描述")
    private String description;

    @ApiModelProperty(value = "状态(1:正常0:弃用)")
    private Integer status;
    @ApiModelProperty(value = "所拥有的菜单权限")
    private List<String> permissions;
}
