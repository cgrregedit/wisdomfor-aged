package com.cgr.lesson.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 23:19 2020-04-30
 * @ Description：创建jwt配置读取类
 * @ Modified By：
 */
@Component
@Data
@ConfigurationProperties(prefix = "jwt")
public class TokenSettings {
    private String secretKey;
    private Duration accessTokenExpireTime;   //访问令牌过期时间
    private Duration refreshTokenExpireTime;   //PC刷新令牌过期时间
    private Duration refreshTokenExpireAppTime;  //APPC刷新令牌过期时间
    private String issuer;  //签发人
}
