package com.cgr.lesson.mapper;

import com.cgr.lesson.entity.SysPermission;

import java.util.List;

public interface SysPermissionMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysPermission record);

    int insertSelective(SysPermission record);

    SysPermission selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysPermission record);

    int updateByPrimaryKey(SysPermission record);

    //查询所有的菜单
    List<SysPermission> selectAll();

    //查询关联的子类
    List<SysPermission> selectChild(String pid);

    List<SysPermission> selectInfoByIds (List<String> ids);

}