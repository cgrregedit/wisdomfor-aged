package com.cgr.lesson.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 13:46 2020-05-14
 * @ Description：接收角色和菜单权限关联数据VO
 * @ Modified By：
 */
@Data
public class RolePermissionOperationReqVO {
    @ApiModelProperty(value = "角色ID")
    @NotBlank(message = "角色ID不能为空")
    private String roldId;

    @ApiModelProperty(value = "菜单权限集合")
    @NotEmpty(message = "菜单权限集合不能为空")
    private List<String> permissionIds;
}
