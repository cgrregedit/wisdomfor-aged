package com.cgr.lesson.mapper;

import com.cgr.lesson.entity.SysRole;
import com.cgr.lesson.vo.req.RolePageReqVO;

import java.util.List;

public interface SysRoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    //查询所有的角色
    List<SysRole> selectAll(RolePageReqVO rolePageReqVO);

    //遍历封装角色名称集合
    List<SysRole> getRoleInfoByIds(List<String> ids);
}