package com.cgr.lesson.service;

import com.cgr.lesson.vo.req.UserOwnRoleReqVO;

import java.util.List;

public interface UserRoleService {
    //根据用户id获取角色集合
    List<String> getRoleIdsByUserId(String userId);

    //新增用户角色
    void addUserRoleInfo(UserOwnRoleReqVO vo);

    //根据角色id集合获取用户id集合
    List<String> getUserIdsByRoleIds(List<String> roleIds);
}
