package com.cgr.lesson.mapper;

import com.cgr.lesson.entity.SysRolePermission;

import java.util.List;

public interface SysRolePermissionMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysRolePermission record);

    int insertSelective(SysRolePermission record);

    SysRolePermission selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysRolePermission record);

    int updateByPrimaryKey(SysRolePermission record);

    int removeByRoleId(String roleId);

    //批量插入角色
    int batchRolePermission(List<SysRolePermission> list);

    //根据菜单权限id获取关联的角色ID集合
    List<String> getRoleIdsByPermissionId(String permissionId);

    //删除
    int removeByPermissionId(String permissionId);

    //根据角色id获取该角色关联的菜单id集合
    List<String> getPermissionIdsByRoleId(String roleId);

    List<String> getPermissionIdsByRoles(List<String> roleIds);
}