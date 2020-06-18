package com.cgr.lesson.shiro;

import com.cgr.lesson.constants.Constant;
import com.cgr.lesson.exception.BusinessException;
import com.cgr.lesson.exception.code.BaseResponseCode;
import com.cgr.lesson.service.RedisService;
import com.cgr.lesson.utils.JwtTokenUtil;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;
import java.util.prefs.BackingStoreException;

/**
 * @ClassName: CustomHashedCredentialsMatcher
 * TODO:类文件简单描述
 * @Author:
 * @UpdateUser:
 * @Version: 0.0.1
 */
public class CustomHashedCredentialsMatcher extends HashedCredentialsMatcher {
    @Autowired
    private RedisService redisService;
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        CustomUsernamePasswordToken customUsernamePasswordToken= (CustomUsernamePasswordToken) token;
        String accessToken= (String) customUsernamePasswordToken.getPrincipal();
        String userId= JwtTokenUtil.getUserId(accessToken);
        /**
         * 判断用户是否被锁定
         */
        if(redisService.hasKey(Constant.ACCOUNT_LOCK_KEY+userId)){
            throw new BusinessException(BaseResponseCode.ACCOUNT_LOCK);
        }
        /**
         * 判断用户是否被删除
         */
        if(redisService.hasKey(Constant.DELETED_USER_KEY+userId)){
            throw new BusinessException(BaseResponseCode.ACCOUNT_HAS_DELETED_ERROR);
        }

        /**
         * 判断token 是否主动登出
         */
        if(redisService.hasKey(Constant.JWT_ACCESS_TOKEN_BLACKLIST+accessToken)){
            throw new BusinessException(BaseResponseCode.TOKEN_ERROR);
        }
        /**
         * 判断token是否通过校验
         */
        if(!JwtTokenUtil.validateToken(accessToken)){
            throw new BusinessException(BaseResponseCode.TOKEN_PAST_DUE);
        }
        /**
         * 判断这个登录用户是否要主动去刷新
         *
         * 如果 key=Constant.JWT_REFRESH_KEY+userId大于accessToken说明是在 accessToken不是重新生成的
         * 这样就要判断它是否刷新过了/或者是否是新生成的token
         */
//        if(redisService.hasKey(Constant.JWT_REFRESH_KEY+userId)&&redisService.getExpire(Constant.JWT_REFRESH_KEY+userId, TimeUnit.MILLISECONDS)>JwtTokenUtil.getRemainingTime(accessToken)){
//            /**
//             * 是否存在刷新的标识
//             */
//            if(!redisService.hasKey(Constant.JWT_REFRESH_IDENTIFICATION+accessToken)){
//                throw new BusinessException(BaseResponseCode.TOKEN_PAST_DUE);
//            }
//        }
        if(redisService.hasKey(Constant.JWT_REFRESH_KEY+userId)){
            /**
             * 通过剩余的过期时间比较如果token的剩余过期时间大与标记key的剩余过期时间
             * 就说明这个tokne是在这个标记key之后生成的
             */
            if(redisService.getExpire(Constant.JWT_REFRESH_KEY+userId, TimeUnit.MILLISECONDS)>JwtTokenUtil.getRemainingTime(accessToken)){
                throw new BusinessException(BaseResponseCode.TOKEN_PAST_DUE);
            }
        }
        return true;
    }
}
