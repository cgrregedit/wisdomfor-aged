package com.cgr.lesson.service.impl;

import com.cgr.lesson.entity.SysRolePermission;
import com.cgr.lesson.exception.BusinessException;
import com.cgr.lesson.exception.code.BaseResponseCode;
import com.cgr.lesson.mapper.SysRolePermissionMapper;
import com.cgr.lesson.service.RolePermissionService;
import com.cgr.lesson.vo.req.RolePermissionOperationReqVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 14:12 2020-05-14
 * @ Description：角色权限
 * @ Modified By：
 */
@Service
public class RolePermissionServiceImpl implements RolePermissionService {
    @Autowired
    private SysRolePermissionMapper sysRolePermissionMapper;
    @Override
    public void addRolePermission(RolePermissionOperationReqVO rolePermissionOperationReqVO) {
        //删除原有的角色权限
        sysRolePermissionMapper.removeByRoleId(rolePermissionOperationReqVO.getRoldId());
        //判断
        if (rolePermissionOperationReqVO.getPermissionIds()==null||rolePermissionOperationReqVO.getPermissionIds().isEmpty()){
            return;
        }
        Date createTime=new Date();
        List<SysRolePermission> list=new ArrayList<>();
        for (String permissionId:rolePermissionOperationReqVO.getPermissionIds()){
            SysRolePermission sysRolePermission=new SysRolePermission();
            sysRolePermission.setId(UUID.randomUUID().toString());
            sysRolePermission.setCreateTime(createTime);
            sysRolePermission.setPermissionId(permissionId);
            sysRolePermission.setRoleId(rolePermissionOperationReqVO.getRoldId());
            list.add(sysRolePermission);
        }

        int count=sysRolePermissionMapper.batchRolePermission(list);
        if (count==0){
            throw  new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
    }

    //根据菜单ID获取对应的角色ID集合
    @Override
    public List<String> getRoleIdsByPermissionId(String permissionId) {
        return sysRolePermissionMapper.getRoleIdsByPermissionId(permissionId);
    }

    //根据菜单id删除角色权限
    @Override
    public int removeByPermissionId(String permissionId) {
        return sysRolePermissionMapper.removeByPermissionId(permissionId);
    }

    //根据角色id获取该角色的菜单id集合
    @Override
    public List<String> getPermissionIdsByRoleId(String roleId) {
        return sysRolePermissionMapper.getPermissionIdsByRoleId(roleId);
    }

    //根据角色id删除所有和该角色关联的菜单权限的数据
    @Override
    public int removeByRoleId(String roleId) {
        return sysRolePermissionMapper.removeByRoleId(roleId);
    }

    @Override
    public List<String> getPermissionIdsByRoles(List<String> roleIds) {
        return sysRolePermissionMapper.getPermissionIdsByRoles(roleIds);
    }
}
