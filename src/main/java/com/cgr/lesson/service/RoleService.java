package com.cgr.lesson.service;

import com.cgr.lesson.entity.SysRole;
import com.cgr.lesson.vo.req.RoleAddReqVO;
import com.cgr.lesson.vo.req.RolePageReqVO;
import com.cgr.lesson.vo.req.RoleUpdateReqVO;
import com.cgr.lesson.vo.resp.PageVO;

import java.util.List;

public interface RoleService {
    //分页查询角色
    PageVO<SysRole> pageInfo(RolePageReqVO rolePageReqVO);

    //增加角色
    SysRole addRole(RoleAddReqVO roleAddReqVO);

    //查询所有角色
    List<SysRole> selectAllRoles();

    //查询角色详情
    SysRole detailInfo(String id);

    //编辑角色
    void  updateRole(RoleUpdateReqVO vo);

    //删除角色
    void deletedRole(String id);

    List<String> getRoleNames(String userId);
    List<SysRole> getRoleInfoByUserId(String userId);

}
