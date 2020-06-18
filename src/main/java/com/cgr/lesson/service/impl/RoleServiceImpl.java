package com.cgr.lesson.service.impl;

import com.cgr.lesson.constants.Constant;
import com.cgr.lesson.entity.SysRole;
import com.cgr.lesson.entity.SysRolePermission;
import com.cgr.lesson.exception.BusinessException;
import com.cgr.lesson.exception.code.BaseResponseCode;
import com.cgr.lesson.mapper.SysRoleMapper;
import com.cgr.lesson.mapper.SysRolePermissionMapper;
import com.cgr.lesson.mapper.SysUserRoleMapper;
import com.cgr.lesson.service.*;
import com.cgr.lesson.utils.PageUtil;
import com.cgr.lesson.utils.TokenSettings;
import com.cgr.lesson.vo.req.RoleAddReqVO;
import com.cgr.lesson.vo.req.RolePageReqVO;
import com.cgr.lesson.vo.req.RolePermissionOperationReqVO;
import com.cgr.lesson.vo.req.RoleUpdateReqVO;
import com.cgr.lesson.vo.resp.PageVO;
import com.cgr.lesson.vo.resp.PermissionRespNode;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 11:46 2020-05-14
 * @ Description：
 * @ Modified By：
 */
@Service
@Slf4j
public class RoleServiceImpl implements RoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private RolePermissionService rolePermissionService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private TokenSettings tokenSettings;


    @Override
    public PageVO<SysRole> pageInfo(RolePageReqVO rolePageReqVO) {
        PageHelper.startPage(rolePageReqVO.getPageNum(), rolePageReqVO.getPageSize());
        List<SysRole> list = sysRoleMapper.selectAll(rolePageReqVO);
        return PageUtil.getPageVO(list);
    }

    @Override
    public SysRole addRole(RoleAddReqVO roleAddReqVO) {
        //增加sys_role表
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(roleAddReqVO, sysRole);
        sysRole.setId(UUID.randomUUID().toString());
        sysRole.setCreateTime(new Date());
        int count = sysRoleMapper.insertSelective(sysRole);
        if (count != 1) {
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
        //添加角色权限
        if (null != roleAddReqVO.getPermissions() && !roleAddReqVO.getPermissions().isEmpty()) {
            RolePermissionOperationReqVO rolePermissionOperationReqVO = new RolePermissionOperationReqVO();
            rolePermissionOperationReqVO.setRoldId(sysRole.getId());
            rolePermissionOperationReqVO.setPermissionIds(roleAddReqVO.getPermissions());
            rolePermissionService.addRolePermission(rolePermissionOperationReqVO);
        }

        return sysRole;
    }

    @Override
    public List<SysRole> selectAllRoles() {
        return sysRoleMapper.selectAll(new RolePageReqVO());
    }

    //查询角色详情
    @Override
    public SysRole detailInfo(String id) {
        SysRole sysRole = sysRoleMapper.selectByPrimaryKey(id);
        if (sysRole == null) {
            log.error("传入 的 id:{}不合法", id);
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }
        List<PermissionRespNode> permissionRespNodes = permissionService.selectAllByTree();
        Set<String> checkList = new HashSet<>(rolePermissionService.getPermissionIdsByRoleId(sysRole.getId()));
        setChecked(permissionRespNodes, checkList);
        sysRole.setPermissionRespNodes(permissionRespNodes);
        return sysRole;
    }

    private void setChecked(List<PermissionRespNode> list, Set<String> checkList) {
        for (PermissionRespNode node : list) {
            /**
                   * 子集选中从它往上到跟目录都被选中，父级选中从它到它所有的叶子节点都会被选中
                   * 这样我们就直接遍历最底层及就可以了
                   */
            if (checkList.contains(node.getId()) &&
                    (node.getChildren() == null || node.getChildren().isEmpty())) {
                node.setChecked(true);
            }
            setChecked((List<PermissionRespNode>) node.getChildren(), checkList);
        }
    }

    //编辑角色
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateRole(RoleUpdateReqVO vo) {
        //保存角色基本信息
        SysRole sysRole = sysRoleMapper.selectByPrimaryKey(vo.getId());
        if (null == sysRole) {
            log.error("传入 的 id:{}不合法", vo.getId());
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }
        BeanUtils.copyProperties(vo, sysRole);
        sysRole.setUpdateTime(new Date());
        int count = sysRoleMapper.updateByPrimaryKeySelective(sysRole);
        if (count != 1) {
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
        //修改该角色和菜单权限关联数据
        RolePermissionOperationReqVO reqVO = new RolePermissionOperationReqVO();
        reqVO.setRoldId(vo.getId());
        reqVO.setPermissionIds(vo.getPermissions());
        rolePermissionService.addRolePermission(reqVO);
        //标记关联用户
        // List<String> userIdsBtRoleId = userRoleService.getUserIdsByRoleIds(vo.getId());
        List<String> userIdsBtRoleId = sysUserRoleMapper.getInfoByUserIdByRoleId(vo.getId());

        if (!userIdsBtRoleId.isEmpty()) {
            for (String userId :
                    userIdsBtRoleId) {
                /**
                 * 标记用户 在用户认证的时候判断这个是否主动刷过
                 */
                redisService.set(Constant.JWT_REFRESH_KEY + userId, userId, tokenSettings.getAccessTokenExpireTime().toMillis(), TimeUnit.MILLISECONDS);

                /**
                 * 清楚用户授权数据缓存
                 */
                redisService.delete(Constant.IDENTIFY_CACHE_KEY + userId);
            }
        }
    }

    //删除角色
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deletedRole(String id) {
        SysRole sysRole = new SysRole();
        sysRole.setId(id);
        sysRole.setUpdateTime(new Date());
        sysRole.setDeleted(0);
        int count = sysRoleMapper.updateByPrimaryKeySelective(sysRole);
        if (count != 1) {
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
        List<String> userIds = sysUserRoleMapper.getInfoByUserIdByRoleId(id);
        sysUserRoleMapper.removeByRoleId(id);
        rolePermissionService.removeByRoleId(id);
     /**
     * 刪除角色后 要主动去刷新跟該角色有关联用户的token
     * 因为用户所拥有的菜单权限是通过角色去关联的
     * 所以要把跟这个角色关联的用户 都要重新刷新token
     */
        if (!userIds.isEmpty()) {
            for (String userId : userIds) {
                redisService.set(Constant.JWT_REFRESH_KEY + userId, userId, tokenSettings.getAccessTokenExpireTime().toMillis(), TimeUnit.MILLISECONDS);
                /**
                 * 清楚用户授权数据缓存
                 */
                redisService.delete(Constant.IDENTIFY_CACHE_KEY+userId);
            }
        }
    }

    @Override
    public List<String> getRoleNames(String userId) {
        List<SysRole> sysRoles=getRoleInfoByUserId(userId);
        if (null==sysRoles||sysRoles.isEmpty()){
            return null;
        }
        List<String> list=new ArrayList<>();
        for (SysRole sysRole:sysRoles){
            list.add(sysRole.getName());
        }
        return list;
    }

    @Override
    public List<SysRole> getRoleInfoByUserId(String userId) {
        List<String> roleIds=userRoleService.getRoleIdsByUserId(userId);
        if (roleIds.isEmpty()){
            return null;
        }
        return sysRoleMapper.getRoleInfoByIds(roleIds);
    }
}
