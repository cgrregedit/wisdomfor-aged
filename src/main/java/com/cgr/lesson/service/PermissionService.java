package com.cgr.lesson.service;

import com.cgr.lesson.entity.SysPermission;
import com.cgr.lesson.vo.req.PermissionUpdateReqVO;
import com.cgr.lesson.vo.resp.PermissionAddReqVO;
import com.cgr.lesson.vo.resp.PermissionRespNode;

import java.util.List;
import java.util.Set;

public interface PermissionService {
    List<SysPermission> selectAll();

    List<PermissionRespNode> selectAllMenuByTree();

    SysPermission addPermission(PermissionAddReqVO permissionAddReqVO);

    List<PermissionRespNode> permissionTreeList(String userId);

    //查询菜单权限表数据（树形结构直到按钮）
    List<PermissionRespNode> selectAllByTree();

    //编辑菜单
    void updatePermission(PermissionUpdateReqVO vo);

    //删除菜单
    void deletedPermission(String permissionId);

    Set<String> getPermissionsByUserId(String userId);
    List<SysPermission> getPermission(String userId);
}
