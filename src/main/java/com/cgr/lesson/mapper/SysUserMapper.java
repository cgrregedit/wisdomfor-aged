package com.cgr.lesson.mapper;

import com.cgr.lesson.entity.SysUser;
import com.cgr.lesson.vo.req.UserPageReqVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    SysUser getUserInfoByName(String username);

    //查询所有用户
    List<SysUser> selectAll(UserPageReqVO userPageReqVO);

    //删除、批量删除
    int deletedUsers(@Param("sysUser") SysUser sysUser,@Param("list") List<String> list);

    //根据部门id集合查找用户
    List<SysUser> selectUserInfoByDeptIds (List<String> deptIds);
}