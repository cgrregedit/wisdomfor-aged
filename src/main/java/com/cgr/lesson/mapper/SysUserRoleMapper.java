package com.cgr.lesson.mapper;

import com.cgr.lesson.entity.SysUserRole;

import java.util.List;

public interface SysUserRoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);

    SysUserRole selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysUserRole record);

    int updateByPrimaryKey(SysUserRole record);

    List<String> getRoleIdsByUserId(String userId);

    //根据用户id 删除和该用户关联的角色关联表数据
    int removeByUserId(String userId);

    //批量插入用户和角色关联数据
    int batchInsertUserRole(List<SysUserRole> list);

    //根据角色ID集合获取用户ID集合
    List<String> getUserIdsByRoleIds(List<String> roleIds);

    //通过角色id 获取跟该角色关联的用户id
    List<String> getInfoByUserIdByRoleId(String roleId);

    //根据角色id 删除该角色关联菜单权限所有数据
    int removeByRoleId(String roleId);

}