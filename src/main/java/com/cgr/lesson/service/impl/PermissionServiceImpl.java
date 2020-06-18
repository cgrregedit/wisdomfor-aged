package com.cgr.lesson.service.impl;

import com.cgr.lesson.constants.Constant;
import com.cgr.lesson.entity.SysPermission;
import com.cgr.lesson.exception.BusinessException;
import com.cgr.lesson.exception.code.BaseResponseCode;
import com.cgr.lesson.mapper.SysPermissionMapper;
import com.cgr.lesson.service.PermissionService;
import com.cgr.lesson.service.RedisService;
import com.cgr.lesson.service.RolePermissionService;
import com.cgr.lesson.service.UserRoleService;
import com.cgr.lesson.utils.TokenSettings;
import com.cgr.lesson.vo.req.PermissionUpdateReqVO;
import com.cgr.lesson.vo.resp.PermissionAddReqVO;
import com.cgr.lesson.vo.resp.PermissionRespNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 15:55 2020-05-12
 * @ Description：
 * @ Modified By：
 */
@Service
@Slf4j
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private TokenSettings tokenSettings;


    @Override
    public List<SysPermission> selectAll() {
        List<SysPermission> result = sysPermissionMapper.selectAll();
        if (!result.isEmpty()) {
            for (SysPermission sysPermission : result) {
                SysPermission
                        parent = sysPermissionMapper.selectByPrimaryKey(sysPermission.getPid());
                if (parent != null) {
                    sysPermission.setPidName(parent.getName());
                }
            }
        }
        return result;

    }

    @Override
    public List<PermissionRespNode> selectAllMenuByTree() {
        List<SysPermission> list = selectAll();
        List<PermissionRespNode> result = new ArrayList<>();
        //新增顶级目录是为了方便添加一级目录
        PermissionRespNode respNode = new PermissionRespNode();
        respNode.setId("0");
        respNode.setTitle("默认顶级菜单");
        respNode.setSpread(true);
        respNode.setChildren(getTree(list, true));
        result.add(respNode);
        return result;
    }


    /**
     * 新增一个参数type  =true查到目录和菜单    =false查询到目录、菜单、按钮
     * type=true 递归遍历到菜单
     * type=false 递归遍历到按钮
     *
     * @param all
     * @param
     * @return java.util.List<com.cgr.lesson.vo.resp.PermissionRespNodeVO>
     * @throws
     * @Author:
     * @UpdateUser:
     * @Version: 0.0.1
     */
    private List<PermissionRespNode> getTree(List<SysPermission> all, boolean type) {
        List<PermissionRespNode> list = new ArrayList<>();
        if (all == null || all.isEmpty()) {
            return list;
        }
        for (SysPermission sysPermission : all) {
            if (sysPermission.getPid().equals("0")) {
                PermissionRespNode permissionRespNode = new PermissionRespNode();
                BeanUtils.copyProperties(sysPermission, permissionRespNode);
                permissionRespNode.setTitle(sysPermission.getName());
                if (type) {
                    permissionRespNode.setChildren(getChildExcBtn(sysPermission.getId(), all));
                } else {
                    permissionRespNode.setChildren(getChildAll(sysPermission.getId(), all));
                }
                list.add(permissionRespNode);
            }
        }
        return list;
    }


    /**
     * @Description:
     * @Param:
     * @return:
     * @Author:cgr
     * @Date: 2020-05-14
     */
    private List<PermissionRespNode> getChildAll(String id, List<SysPermission> all) {
        List<PermissionRespNode> list = new ArrayList<>();
        for (SysPermission sysPermission : all) {
            if (sysPermission.getPid().equals(id)) {
                PermissionRespNode permissionRespNode = new PermissionRespNode();
                BeanUtils.copyProperties(sysPermission, permissionRespNode);
                permissionRespNode.setTitle(sysPermission.getName());
                permissionRespNode.setChildren(getChildAll(sysPermission.getId(), all));
                list.add(permissionRespNode);
            }
        }
        return list;
    }

    /**
     * 递归遍历所有数据
     *
     * @param id
     * @param all
     * @return java.util.List<com.cgr.lesson.vo.resp.PermissionRespNode>
     * @throws
     * @Author:
     * @UpdateUser:
     * @Version: 0.0.1
     */
    private List<PermissionRespNode> getChildExcBtn(String id, List<SysPermission> all) {
        List<PermissionRespNode> list = new ArrayList<>();
        for (SysPermission sysPermission : all) {
            if (sysPermission.getPid().equals(id) && sysPermission.getType() != 3) {
                PermissionRespNode permissionRespNode = new PermissionRespNode();
                BeanUtils.copyProperties(sysPermission, permissionRespNode);
                permissionRespNode.setTitle(sysPermission.getName());
                permissionRespNode.setChildren(getChildExcBtn(sysPermission.getId(), all));
                list.add(permissionRespNode);
            }
        }
        return list;
    }

    @Override
    public SysPermission addPermission(PermissionAddReqVO permissionAddReqVO) {
        SysPermission sysPermission = new SysPermission();
        BeanUtils.copyProperties(permissionAddReqVO, sysPermission);
        verifyForm(sysPermission);
        sysPermission.setId(UUID.randomUUID().toString());
        sysPermission.setCreateTime(new Date());
        int count = sysPermissionMapper.insertSelective(sysPermission);
        if (count != 1) {
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
        return sysPermission;
    }


    /**
     * - 操作后的菜单类型是目录的时候 父级必须为目录
     * - 操作后的菜单类型是菜单的时候，父类必须为目录类型
     * - 操作后的菜单类型是按钮的时候 父类必须为菜单类型
     *
     * @param sysPermission
     * @return void
     * @throws
     * @Author:
     * @UpdateUser:
     * @Version: 0.0.1
     */
    private void verifyForm(SysPermission sysPermission) {
        SysPermission parent = sysPermissionMapper.selectByPrimaryKey(sysPermission.getPid());
        switch (sysPermission.getType()) {
            case 1:
                if (parent != null) {
                    if (parent.getType() != 1) {
                        throw new BusinessException(BaseResponseCode.OPERATION_MENU_PERMISSION_CATALOG_ERROR);
                    }
                } else if (!sysPermission.getPid().equals("0")) {
                    throw new BusinessException(BaseResponseCode.OPERATION_MENU_PERMISSION_CATALOG_ERROR);
                }
                break;
            case 2:
                if (parent == null || parent.getType() != 1) {
                    throw new BusinessException(BaseResponseCode.OPERATION_MENU_PERMISSION_MENU_ERROR);
                }
                if (StringUtils.isEmpty(sysPermission.getUrl())) {
                    throw new BusinessException(BaseResponseCode.OPERATION_MENU_PERMISSION_URL_NOT_NULL);
                }
                break;
            case 3:
                if (parent == null || parent.getType() != 2) {
                    throw new BusinessException(BaseResponseCode.OPERATION_MENU_PERMISSION_BTN_ERROR);
                }
                if (StringUtils.isEmpty(sysPermission.getPerms())) {
                    throw new BusinessException(BaseResponseCode.OPERATION_MENU_PERMISSION_URL_PERMS_NULL);
                }
                if (StringUtils.isEmpty(sysPermission.getUrl())) {
                    throw new BusinessException(BaseResponseCode.OPERATION_MENU_PERMISSION_URL_NOT_NULL);
                }
                if (StringUtils.isEmpty(sysPermission.getMethod())) {
                    throw new BusinessException(BaseResponseCode.OPERATION_MENU_PERMISSION_URL_METHOD_NULL);
                }
                if (StringUtils.isEmpty(sysPermission.getCode())) {
                    throw new BusinessException(BaseResponseCode.OPERATION_MENU_PERMISSION_URL_CODE_NULL);
                }
                break;
        }
    }

    @Override
    public List<PermissionRespNode> permissionTreeList(String userId) {
        return getTree(selectAll(), true);
    }

    @Override
    public List<PermissionRespNode> selectAllByTree() {
        List<SysPermission> list = selectAll();
        return getTree(list, false);
    }


    //编辑菜单
    @Override
    public void updatePermission(PermissionUpdateReqVO vo) {
        //校验数据
        SysPermission update = new SysPermission();
        BeanUtils.copyProperties(vo, update);
        verifyForm(update);
        SysPermission sysPermission = sysPermissionMapper.selectByPrimaryKey(vo.getId());
        if (sysPermission == null) {
            log.info("传入的id在数据库中不存在");
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }
        if (!sysPermission.getPid().equals(vo.getPid()) || sysPermission.getStatus() != vo.getStatus()) {
            //所属菜单发生了变化或者权限状态发生了变化要校验该权限是否存在子集
            List<SysPermission> sysPermissions = sysPermissionMapper.selectChild(vo.getId());
            if (!sysPermissions.isEmpty()) {
                throw new
                        BusinessException(BaseResponseCode.OPERATION_MENU_PERMISSION_UPDATE);
            }
        }
        update.setUpdateTime(new Date());
        int i = sysPermissionMapper.updateByPrimaryKeySelective(update);
        if (i != 1) {
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
        //判断授权标识符是否发生了变化(权限标识符发生了变化，或者权限状态发生了变化)
        if (!sysPermission.getPerms().equals(vo.getPerms()) || sysPermission.getStatus() != vo.getStatus(
        )) {
            List<String> roleIdsByPermissionId = rolePermissionService.getRoleIdsByPermissionId(vo.getId());
            if (!roleIdsByPermissionId.isEmpty()) {
                List<String> userIdsByRoleIds = userRoleService.getUserIdsByRoleIds(roleIdsByPermissionId);
                if (!userIdsByRoleIds.isEmpty()) {
                    for (String userId : userIdsByRoleIds) {
                        redisService.set(Constant.JWT_REFRESH_KEY + userId, userId, tokenSettings.getAccessTokenExpireTime().toMillis(), TimeUnit.MILLISECONDS);
                        /**
                                     * 清楚用户授权数据缓存
                                     */
                        redisService.delete(Constant.IDENTIFY_CACHE_KEY + userId);
                    }
                }
            }
        }
    }

    //删除菜单
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deletedPermission(String permissionId) {
        //判断是否有子集关联
        List<SysPermission> sysPermissions = sysPermissionMapper.selectChild(permissionId);
        if (!sysPermissions.isEmpty()) {
            throw new BusinessException(BaseResponseCode.ROLE_PERMISSION_RELATION);
        }
        //解除相关角色和该菜单权限的关联
        rolePermissionService.removeByPermissionId(permissionId);
        //更新权限数据
        SysPermission sysPermission = new SysPermission();
        sysPermission.setUpdateTime(new Date());
        sysPermission.setDeleted(0);
        sysPermission.setId(permissionId);
        int i = sysPermissionMapper.updateByPrimaryKeySelective(sysPermission);
        if (i != 1) {
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
        //判断授权标识符是否发生了变化
        List<String> roleIdsByPermissionId = rolePermissionService.getRoleIdsByPermissionId(permissionId);
        if (!roleIdsByPermissionId.isEmpty()) {
            List<String> userIdsByRoleIds = userRoleService.getUserIdsByRoleIds(roleIdsByPermissionId);
            if (!userIdsByRoleIds.isEmpty()) {
                for (String userId : userIdsByRoleIds) {
                    redisService.set(Constant.JWT_REFRESH_KEY + userId, userId, tokenSettings.getAccessTokenExpireTime().toMillis(), TimeUnit.MILLISECONDS);
                    /**
                     * 清楚用户授权数据缓存
                     */
                    redisService.delete(Constant.IDENTIFY_CACHE_KEY + userId);
                }
            }
        }
    }

    @Override
    public Set<String> getPermissionsByUserId(String userId) {
        List<SysPermission> list = getPermission(userId);
        Set<String> permissions = new HashSet<>();
        if (null == list || list.isEmpty()) {
            return null;
        }
        for (SysPermission sysPermission : list) {
            if (!StringUtils.isEmpty(sysPermission.getPerms())) {
                permissions.add(sysPermission.getPerms());
            }

        }
        return permissions;
    }

    @Override
    public List<SysPermission> getPermission(String userId) {
        List<String> roleIds = userRoleService.getRoleIdsByUserId(userId);
        if (roleIds.isEmpty()) {
            return null;
        }
        List<String> permissionIds = rolePermissionService.getPermissionIdsByRoles(roleIds);
        if (permissionIds.isEmpty()) {
            return null;
        }
        List<SysPermission> result = sysPermissionMapper.selectInfoByIds(permissionIds);
        return result;
    }

}
