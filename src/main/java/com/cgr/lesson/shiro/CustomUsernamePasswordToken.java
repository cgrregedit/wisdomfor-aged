package com.cgr.lesson.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @ClassName: CustomUsernamePasswordToken
 * TODO:自定义CustomUsernamePasswordToken
 * @Author: cgr
 * @UpdateUser: cgr
 * @Version: 0.0.1
 */
public class CustomUsernamePasswordToken extends UsernamePasswordToken {

    private String token;

    //定义一个构造方法
    public CustomUsernamePasswordToken(String token) {

        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }
}
