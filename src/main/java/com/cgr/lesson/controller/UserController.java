package com.cgr.lesson.controller;

import com.cgr.lesson.aop.annotation.MyLog;
import com.cgr.lesson.constants.Constant;
import com.cgr.lesson.entity.SysUser;
import com.cgr.lesson.service.UserService;
import com.cgr.lesson.utils.DataResult;
import com.cgr.lesson.utils.JwtTokenUtil;
import com.cgr.lesson.vo.req.*;
import com.cgr.lesson.vo.resp.LoginRespVO;
import com.cgr.lesson.vo.resp.PageVO;
import com.cgr.lesson.vo.resp.UserInfoRespVO;
import com.cgr.lesson.vo.resp.UserOwnRoleRespVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 01:16 2020-05-01
 * @ Description：用户模块
 * @ Modified By：
 */

@RestController
@Slf4j
@RequestMapping("/api")
@Api(tags = "用户模块")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/user/login")
    @ApiOperation(value = "用户登录接口")
    @MyLog(title = "用户模块", action = "用户登录接口")
    public DataResult<LoginRespVO> login(@RequestBody LoginReqVO loginReqVO) {
        DataResult<LoginRespVO> result = DataResult.success();
        result.setData(userService.login(loginReqVO));
        return result;
    }

    @GetMapping("/user/logout")
    @ApiOperation(value = "用户登出接口")
    public DataResult logout(HttpServletRequest request) {
        try {
            String accessToken = request.getHeader(Constant.ACCESS_TOKEN);
            String refreshToken = request.getHeader(Constant.REFRESH_TOKEN);
            userService.logout(accessToken, refreshToken);
        } catch (Exception e) {
            log.error("logout error{}", e);
        }
        return DataResult.success();
    }

    @PostMapping("/users")
    @ApiOperation(value = "分页获取用户列表接口")
    @MyLog(title = "用户模块", action = "分页获取用户列表接口")
    @RequiresPermissions("sys:user:list")
    public DataResult<PageVO<SysUser>> pageInfo(@RequestBody UserPageReqVO userPageReqVO) {
        DataResult<PageVO<SysUser>> result = DataResult.success();
        result.setData(userService.pageInfo(userPageReqVO));
        return result;
    }

    @PostMapping("/user")
    @ApiOperation(value = "新增用户接口")
    @MyLog(title = "用户模块", action = "新增用户接口")
    public DataResult addUser(@RequestBody @Valid UserAddReqVO vo) {
        userService.addUser(vo);
        return DataResult.success();
    }

    @GetMapping("/user/roles/{userId}")
    @ApiOperation(value = "赋予角色-获取用户拥有角色接口")
    @MyLog(title = "用户模块", action = "赋予角色-获取用户拥有角色接口")
    public DataResult<UserOwnRoleRespVO> getUserOwnRole(@PathVariable("userId") String userId) {
        DataResult<UserOwnRoleRespVO> result = DataResult.success();
        result.setData(userService.getUserOwnRole(userId));
        return result;
    }

    @PutMapping("/user/roles")
    @ApiOperation(value = "保存用户拥有的角色信息接口")
    @MyLog(title = "用户模块", action = "保存用户拥有的角色信息接口")
    public DataResult saveUserOwnRole(@RequestBody @Valid UserOwnRoleReqVO vo) {
        DataResult result = DataResult.success();
        userService.setUserOwnRole(vo);
        return result;
    }


    @GetMapping("/user/token")
    @ApiOperation(value = "jwt token 刷新接口")
    @MyLog(title = "用户模块", action = "jwt token 刷新接口")
    public DataResult<String> refreshToken(HttpServletRequest request) {
        String refreshToken = request.getHeader(Constant.REFRESH_TOKEN);
        String newAccessToken = userService.refreshToken(refreshToken);
        DataResult result = DataResult.success();
        result.setData(newAccessToken);
        return result;
    }

    @PutMapping("/user")
    @ApiOperation(value = "编辑用户信息接口")
    @MyLog(title = "用户模块", action = "更新用户信息接口")
    public DataResult updateUserInfo(@RequestBody @Valid UserUpdateReqVO vo, HttpServletRequest httpServletRequest) {
        String operationId = JwtTokenUtil.getUserId(httpServletRequest.getHeader(Constant.ACCESS_TOKEN));
        userService.updateUserInfo(vo, operationId);
        return DataResult.success();
    }

    @DeleteMapping("/user")
    @ApiOperation(value = "删除用户接口")
    @MyLog(title = "用户模块", action = "删除用户接口")
    public DataResult deletedUser(@RequestBody @ApiParam(value = "用户id集合") List<String> userIds,
                                  HttpServletRequest request) {
        String operationId = JwtTokenUtil.getUserId(request.getHeader(Constant.ACCESS_TOKEN));
        userService.deletedUsers(userIds, operationId);
        return DataResult.success();
    }

    @GetMapping("/user")
    @ApiOperation(value = "获取用户详情")
    @MyLog(title = "用户模块", action = "获取用户详情")
    public DataResult<SysUser> youSelfInfo(HttpServletRequest request) {
        String access_Token = request.getHeader(Constant.ACCESS_TOKEN);
        String userId = JwtTokenUtil.getUserId(access_Token);
        DataResult<SysUser> dataResult = DataResult.success();
        dataResult.setData(userService.detailInfo(userId));
        return dataResult;
    }

    @PutMapping("/user/info")
    @ApiOperation(value = "更新用户信息接口")
    @MyLog(title = "用户模块", action = "个人用户更新用户信息接口")
    public DataResult updateUserInfoById(@RequestBody @Valid UserUpdateDetailInfoReqVO vo,
                                         HttpServletRequest request) {
        String userId = JwtTokenUtil.getUserId(request.getHeader(Constant.ACCESS_TOKEN));
        userService.userUpdateDetailInfo(vo, userId);
        return DataResult.success();
    }

    @PutMapping("/user/pwd")
    @ApiOperation(value = "修改个人密码接口")
    public DataResult updatePwd(@RequestBody @Valid UserUpdatePwdReqVO vo,HttpServletRequest request){
        String accessToken=request.getHeader(Constant.ACCESS_TOKEN);
        String refresgToken=request.getHeader(Constant.REFRESH_TOKEN);
        userService.updatePwd(vo,accessToken,refresgToken);
        DataResult result=DataResult.success();
        return result;
    }

    @GetMapping("/user/{id}")
    @ApiOperation(value = "根据ID获取用户详情")
    @MyLog(title = "用户模块", action = "根据ID获取用户详情")
    public DataResult<SysUser> getSysUserById(@PathVariable("id") String id){
        DataResult<SysUser> dataResult = DataResult.success();
        dataResult.setData(userService.detailInfo(id));
        return dataResult;
    }

}
