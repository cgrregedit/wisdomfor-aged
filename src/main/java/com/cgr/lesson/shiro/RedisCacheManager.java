package com.cgr.lesson.shiro;

import com.cgr.lesson.service.RedisService;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName: RedisCacheManager
 * 实现 CacheManager接口的 getCache 方法
 * @Author: cgr
 * @UpdateUser: cgr
 * @Version: 0.0.1
 */
public class RedisCacheManager implements CacheManager {
    @Autowired
    private RedisService redisService;
    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        return new RedisCache<>(s,redisService);
    }
}
