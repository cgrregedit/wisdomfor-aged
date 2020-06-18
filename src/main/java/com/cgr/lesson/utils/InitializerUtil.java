package com.cgr.lesson.utils;

import org.springframework.stereotype.Component;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 23:27 2020-04-30
 * @ Description： 创建初始化配置代理类
 * @ Modified By：
 */
@Component
public class InitializerUtil {
    private TokenSettings tokenSettings;
    public InitializerUtil(TokenSettings tokenSettings){
        JwtTokenUtil.setTokenSettings(tokenSettings);
    }
}
