package com.cgr.lesson.service.impl;

import com.cgr.lesson.constants.Constant;
import com.cgr.lesson.entity.SysDept;
import com.cgr.lesson.entity.SysRole;
import com.cgr.lesson.entity.SysUser;
import com.cgr.lesson.exception.BusinessException;
import com.cgr.lesson.exception.code.BaseResponseCode;
import com.cgr.lesson.mapper.SysDeptMapper;
import com.cgr.lesson.mapper.SysUserMapper;
import com.cgr.lesson.service.*;
import com.cgr.lesson.utils.JwtTokenUtil;
import com.cgr.lesson.utils.PageUtil;
import com.cgr.lesson.utils.PasswordUtils;
import com.cgr.lesson.utils.TokenSettings;
import com.cgr.lesson.vo.req.*;
import com.cgr.lesson.vo.resp.LoginRespVO;
import com.cgr.lesson.vo.resp.PageVO;
import com.cgr.lesson.vo.resp.UserOwnRoleRespVO;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 00:05 2020-05-01
 * @ Description： UserService的实现类
 * @ Modified By：
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private TokenSettings tokenSettings;

    @Autowired
    private PermissionService permissionService;

    @Override
    public LoginRespVO login(LoginReqVO vo) {
        //通过用户名查询用户信息
        //如果查询存在用户
        //就比较它密码是否一样
        SysUser userInfoByName = sysUserMapper.getUserInfoByName(vo.getUsername());
        if(userInfoByName==null){
            throw new BusinessException(BaseResponseCode.ACCOUNT_ERROR);
        }
        if(userInfoByName.getStatus()==2){
            throw new BusinessException(BaseResponseCode.ACCOUNT_LOCK_TIP);
        }
        if(!PasswordUtils.matches(userInfoByName.getSalt(),vo.getPassword(),userInfoByName.getPassword())){
            throw new BusinessException(BaseResponseCode.ACCOUNT_PASSWORD_ERROR);
        }
        LoginRespVO loginRespVO=new LoginRespVO();
        loginRespVO.setPhone(userInfoByName.getPhone());
        loginRespVO.setUsername(userInfoByName.getUsername());
        loginRespVO.setId(userInfoByName.getId());
        Map<String, Object> claims=new HashMap<>();
        claims.put(Constant.JWT_ROLES_KEY,getRolesByUserId(userInfoByName.getId()));
        claims.put(Constant.JWT_PERMISSIONS_KEY,getPermissionsByUserId(userInfoByName.getId()));
        claims.put(Constant.JWT_USER_NAME,userInfoByName.getUsername());
        String accessToken=JwtTokenUtil.getAccessToken(userInfoByName.getId(),claims);
        String refreshToken;
        if(vo.getType().equals("1")){
            refreshToken=JwtTokenUtil.getRefreshToken(userInfoByName.getId(),claims);
        }else {
            refreshToken=JwtTokenUtil.getRefreshAppToken(userInfoByName.getId(),claims);
        }
        loginRespVO.setAccessToken(accessToken);
        loginRespVO.setRefreshToken(refreshToken);
        return loginRespVO;
    }


    @Override
    public void logout(String accessToken, String refreshToken) {
        if (StringUtils.isEmpty(accessToken) || StringUtils.isEmpty(refreshToken)) {
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }
        Subject subject = SecurityUtils.getSubject();
        log.info("subject.getPrincipals()={}", subject.getPrincipals());
        if (subject.isAuthenticated()) {
            subject.logout();
        }
        String userId = JwtTokenUtil.getUserId(accessToken);
        /**
             * 把token 加入黑名单 禁止再登录
             */
        redisService.set(Constant.JWT_ACCESS_TOKEN_BLACKLIST + accessToken, userId, JwtTokenUtil.getRemainingTime(accessToken), TimeUnit.MILLISECONDS);
        /**
             * 把 refreshToken 加入黑名单 禁止再拿来刷新token
             */
        redisService.set(Constant.JWT_REFRESH_TOKEN_BLACKLIST + refreshToken, userId, JwtTokenUtil.getRemainingTime(refreshToken), TimeUnit.MILLISECONDS);
    }


    /**
     * @Description: 分页查询用户信息
     * @Param:
     * @return:
     * @Author:cgr
     * @Date: 2020-05-01
     */
    @Override
    public PageVO<SysUser> pageInfo(UserPageReqVO userPageReqVO) {
        PageHelper.startPage(userPageReqVO.getPageNum(), userPageReqVO.getPageSize());
        List<SysUser> sysUsers = sysUserMapper.selectAll(userPageReqVO);
        //获取用所属部门名称
        for (SysUser sysUser : sysUsers) {
            SysDept sysDept = sysDeptMapper.selectByPrimaryKey(sysUser.getDeptId());
            if (sysDept != null) {
                sysUser.setDeptName(sysDept.getName());
            }
        }

        return PageUtil.getPageVO(sysUsers);
    }

    private List<String> getRolesByUserId(String userId) {
        return  roleService.getRoleNames(userId);
    }

    private Set<String> getPermissionsByUserId(String userId){
        return  permissionService.getPermissionsByUserId(userId);
    }

    /**
     * @Description:新增用户
     * @Param:
     * @return:
     * @Author:cgr
     * @Date: 2020-05-19
     */
    @Override
    public void addUser(UserAddReqVO vo) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(vo, sysUser);
        sysUser.setSalt(PasswordUtils.getSalt());
        String encode = PasswordUtils.encode(vo.getPassword(), sysUser.getSalt());
        sysUser.setPassword(encode);
        sysUser.setId(UUID.randomUUID().toString());
        sysUser.setCreateTime(new Date());
        int i = sysUserMapper.insertSelective(sysUser);
        if (i != 1) {
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
    }

    @Override
    public UserOwnRoleRespVO getUserOwnRole(String userId) {
        SysUser sysUser=sysUserMapper.selectByPrimaryKey(userId);
        List<String> roleIdsByUserId = userRoleService.getRoleIdsByUserId(userId);
        List<SysRole> list = roleService.selectAllRoles();
        UserOwnRoleRespVO vo = new UserOwnRoleRespVO();
        vo.setId(sysUser.getId());
        vo.setUserName(sysUser.getUsername());
        vo.setAllRole(list);
        vo.setOwnRoles(roleIdsByUserId);
        return vo;
    }

    @Override
    public void setUserOwnRole(UserOwnRoleReqVO vo) {
        userRoleService.addUserRoleInfo(vo);
        /**
             * 标记用户 要主动去刷新
             */
        redisService.set(Constant.JWT_REFRESH_KEY + vo.getUserId(), vo.getUserId(), tokenSettings.getAccessTokenExpireTime().toMillis(), TimeUnit.MILLISECONDS);
        /**
         * 清楚用户授权数据缓存
         */
        redisService.delete(Constant.IDENTIFY_CACHE_KEY+vo.getUserId());
    }

    @Override
    public String refreshToken(String refreshToken) {
        //它是否过期
        //它是否被加如了黑名
        if (!JwtTokenUtil.validateToken(refreshToken) || redisService.hasKey(Constant.JWT_REFRESH_TOKEN_BLACKLIST + refreshToken)) {
            throw new BusinessException(BaseResponseCode.TOKEN_ERROR);
        }
        String userId = JwtTokenUtil.getUserId(refreshToken);
        log.info("userId={}", userId);
        Map<String, Object> claims = null;
        if (redisService.hasKey(Constant.JWT_REFRESH_KEY + userId)) {
            claims = new HashMap<>();
            claims.put(Constant.JWT_ROLES_KEY, getRolesByUserId(userId));
            claims.put(Constant.JWT_PERMISSIONS_KEY, getPermissionsByUserId(userId));
        }
        String newAccessToken = JwtTokenUtil.refreshToken(refreshToken, claims);
        return newAccessToken;
    }

    @Override
    public void updateUserInfo(UserUpdateReqVO vo, String operationId) {
        //检验用户是否存在
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(vo.getId());
        if (null == sysUser) {
            log.error("传入的id：{}不合法", vo.getId());
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }
        //复制类
        BeanUtils.copyProperties(vo, sysUser);
        //更新时间赋值
        sysUser.setUpdateTime(new Date());
        //密码
        if (!StringUtils.isEmpty(vo.getPassword())) {
            String newPassword = PasswordUtils.encode(vo.getPassword(), sysUser.getSalt());
            sysUser.setPassword(newPassword);
        } else {
            sysUser.setPassword(null);
        }
        //更新人
        sysUser.setUpdateId(operationId);
        //数据后台更新
        int count = sysUserMapper.updateByPrimaryKeySelective(sysUser);
        if (count != 1) {
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
        //用户状态有改变，签发的token都要失效
        if (vo.getStatus() == 2) {
            redisService.set(Constant.ACCOUNT_LOCK_KEY + sysUser.getId(), sysUser.getId());
        } else {
            redisService.delete(Constant.ACCOUNT_LOCK_KEY + sysUser.getId());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletedUsers(List<String> userIds, String operationId) {
        SysUser sysUser = new SysUser();
        sysUser.setUpdateId(operationId);
        sysUser.setUpdateTime(new Date());
        sysUser.setDeleted(0);
        int i = sysUserMapper.deletedUsers(sysUser, userIds);
        if (i == 0) {
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
        /**
             * 标记用户id 已删除
             * 因为我们是以签发token 的形式保持用户登录状态的
             * 有可能签发了多次toekn 所以在用户删除的时候
             * 要把userId标记起来 过期时间为refreahToekn 的过期时间
             * 避免它可以通过刷新toekn来继续保持登录
             */
        for (String userId : userIds) {
            redisService.set(Constant.DELETED_USER_KEY + userId, userId, tokenSettings.getRefreshTokenExpireAppTime().toMillis(), TimeUnit.MILLISECONDS);
            /**
             * 清楚用户授权数据缓存
             */
            redisService.delete(Constant.IDENTIFY_CACHE_KEY+userId);
        }
    }

    //根据ID获取用户信息
    @Override
    public SysUser detailInfo(String userId) {
        return sysUserMapper.selectByPrimaryKey(userId);
    }

    @Override
    public void userUpdateDetailInfo(UserUpdateDetailInfoReqVO vo, String userId) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(vo, sysUser);
        sysUser.setId(userId);
        sysUser.setUpdateTime(new Date());
        sysUser.setUpdateId(userId);
        int count = sysUserMapper.updateByPrimaryKeySelective(sysUser);
        if (count != 1) {
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
    }

    //修改密码
    @Override
    public void updatePwd(UserUpdatePwdReqVO vo, String accessToken, String refreshToken) {
        String userId=JwtTokenUtil.getUserId(accessToken);
        //校验旧密码
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        if(sysUser==null){
            throw new BusinessException(BaseResponseCode.TOKEN_ERROR);
        }
        if(!PasswordUtils.matches(sysUser.getSalt(),vo.getOldPwd(),sysUser.getPassword())){
            throw new BusinessException(BaseResponseCode.OLD_PASSWORD_ERROR);
        }
        //保存新密码
        sysUser.setUpdateTime(new Date());
        sysUser.setUpdateId(userId);
        sysUser.setPassword(PasswordUtils.encode(vo.getNewPwd(),sysUser.getSalt()));
        int i = sysUserMapper.updateByPrimaryKeySelective(sysUser);
        if(i!=1){
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
        /**
         * 把token 加入黑名单 禁止再访问我们的系统资源
         */
        redisService.set(Constant.JWT_ACCESS_TOKEN_BLACKLIST+accessToken,userId,JwtTokenUtil.getRemainingTime(accessToken), TimeUnit.MILLISECONDS);
        /**
         * 把 refreshToken 加入黑名单 禁止再拿来刷新token
         */
        redisService.set(Constant.JWT_REFRESH_TOKEN_BLACKLIST+refreshToken,userId,JwtTokenUtil.getRemainingTime(refreshToken),TimeUnit.MILLISECONDS);
        /**
         * 清楚用户授权数据缓存
         */
        redisService.delete(Constant.IDENTIFY_CACHE_KEY+userId);
    }


}
