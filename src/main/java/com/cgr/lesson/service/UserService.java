package com.cgr.lesson.service;

import com.cgr.lesson.entity.SysUser;
import com.cgr.lesson.vo.req.*;
import com.cgr.lesson.vo.resp.LoginRespVO;
import com.cgr.lesson.vo.resp.PageVO;
import com.cgr.lesson.vo.resp.UserInfoRespVO;
import com.cgr.lesson.vo.resp.UserOwnRoleRespVO;

import java.util.List;

public interface UserService {

    /**
    * @Description: 用户登录类
    * @Param:
    * @return:
    * @Author:cgr
    * @Date: 2020-05-01
    */
    LoginRespVO login(LoginReqVO loginReqVO);

    /**
    * @Description: 用户登出业务
    * @Param:
    * @return:
    * @Author:cgr
    * @Date: 2020-05-01
    */
    void logout(String accessToken, String refreshToken);

    /**
     * @Description: 分页查询
     * @Param:
     * @return:
     * @Author:cgr
     * @Date: 2020-05-01
     */
     PageVO<SysUser> pageInfo(UserPageReqVO userPageReqVO);



     /**
     * @Description: 新增用户
     * @Param:
     * @return:
     * @Author:cgr
     * @Date: 2020-05-19
     */
     void addUser(UserAddReqVO vo);



     /**
     * @Description: 获取用户角色
     * @Param:
     * @return:
     * @Author:cgr
     * @Date: 2020-05-19
     */
     UserOwnRoleRespVO getUserOwnRole(String userId);

     //设置用户角色
     void setUserOwnRole(UserOwnRoleReqVO vo);

    //jwt刷新
     String refreshToken(String refreshToken);

     //编辑用户信息
     void updateUserInfo(UserUpdateReqVO vo,String operationId);

     //删除
     void deletedUsers(List<String> userIds, String operationId);

     //根据ID获取用户信息
     SysUser detailInfo(String userId);

    //个人用户编辑信息接口
    void userUpdateDetailInfo(UserUpdateDetailInfoReqVO vo,String userId);

    //修改密码
    void updatePwd(UserUpdatePwdReqVO vo,String accessToken, String refreshToken);

}
