package com.cgr.lesson.service.impl;

import com.cgr.lesson.entity.SysUserRole;
import com.cgr.lesson.exception.BusinessException;
import com.cgr.lesson.exception.code.BaseResponseCode;
import com.cgr.lesson.mapper.SysUserRoleMapper;
import com.cgr.lesson.service.UserRoleService;
import com.cgr.lesson.vo.req.UserOwnRoleReqVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 22:50 2020-05-19
 * @ Description：用户角色
 * @ Modified By：
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public List<String> getRoleIdsByUserId(String userId) {
        return sysUserRoleMapper.getRoleIdsByUserId(userId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addUserRoleInfo(UserOwnRoleReqVO vo) {
        sysUserRoleMapper.removeByUserId(vo.getUserId());
        if (vo.getRoleIds() == null || vo.getRoleIds().isEmpty()) {
            return;
        }
        Date createTime = new Date();
        List<SysUserRole> list = new ArrayList<>();
        for (String roleId : vo.getRoleIds()) {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setId(UUID.randomUUID().toString());
            sysUserRole.setCreateTime(createTime);
            sysUserRole.setUserId(vo.getUserId());
            sysUserRole.setRoleId(roleId);
            list.add(sysUserRole);
        }
        int count = sysUserRoleMapper.batchInsertUserRole(list);
        if (count == 0) {
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
    }

    //根据角色id集合获取用户id集合
    @Override
    public List<String> getUserIdsByRoleIds(List<String> roleIds) {
        return sysUserRoleMapper.getUserIdsByRoleIds(roleIds);
    }
}
