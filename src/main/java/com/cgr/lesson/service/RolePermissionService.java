package com.cgr.lesson.service;

import com.cgr.lesson.vo.req.RolePermissionOperationReqVO;
import net.bytebuddy.agent.builder.AgentBuilder;

import java.util.List;

public interface RolePermissionService {
    //角色权限增加
    void addRolePermission(RolePermissionOperationReqVO rolePermissionOperationReqVO);

    //根据菜单权限ID获取角色ID的集合
    List<String> getRoleIdsByPermissionId(String permissionId);

    //根据菜单id删除角色权限
    int removeByPermissionId(String permissionId);

    //根据角色id获取该角色的菜单id集合
    List<String>  getPermissionIdsByRoleId(String roleId);

    //根据角色id删除所有和该角色关联的菜单权限的数据
    int removeByRoleId(String roleId);

    List<String> getPermissionIdsByRoles(List<String> roleIds);

}
