package com.cgr.lesson.shiro;

import com.alibaba.fastjson.JSON;
import com.cgr.lesson.service.RedisService;
import com.cgr.lesson.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: RedisCache
 * RedisCache<K, V> 实现 shiro Cache<K, V> 缓存接口，并重写 Cache<K, V> get、put、remove、clear、size、keys、values等方法，
 * 这些方法都是 shiro 在对缓存的一些操作，就是当 shiro 操作缓存的时候都会调用相应的方法，我们只需重写这些相应的方法就可以把 shiro
 * 的缓存信息存入到 redis 了。这就是一个优秀的开源框架所具备的扩展性，它提供了一个cacheManager 缓存管理器 我们只需重新这个管理器即可。
 * @Author: cgr
 * @UpdateUser: cgr
 * @Version: 0.0.1
 */
@Slf4j
public class RedisCache<K,V> implements Cache<K,V> {
    private final static String PREFIX = "shiro-cache:";
    private String cacheKey;
    private long expire = 24;
    private RedisService redisService;
    public RedisCache(String name,RedisService redisService){
        this.cacheKey=PREFIX+name+":";
        this.redisService=redisService;
    }
    @Override
    public V get(K key) throws CacheException {
        log.info("Shiro从缓存中获取数据KEY值[{}]",key);
        if (key == null) {
            return null;
        }
        try {
            String redisCacheKey = getRedisCacheKey(key);
            Object rawValue = redisService.get(redisCacheKey);
            if (rawValue == null) {
                return null;
            }
            SimpleAuthorizationInfo simpleAuthenticationInfo= JSON.parseObject(rawValue.toString(),SimpleAuthorizationInfo.class);
            V value = (V) simpleAuthenticationInfo;
            return value;
        } catch (Exception e) {
            throw new CacheException(e);
        }
    }

    @Override
    public V put(K key, V value) throws CacheException {
        log.info("put key [{}]",key);
        if (key == null) {
            log.warn("Saving a null key is meaningless, return value directly without call Redis.");
            return value;
        }
        try {
            String redisCacheKey = getRedisCacheKey(key);
            redisService.set(redisCacheKey, value != null ? value : null, expire, TimeUnit.HOURS);
            return value;
        } catch (Exception e) {
            throw new CacheException(e);
        }
    }

    @Override
    public V remove(K key) throws CacheException {
        log.info("remove key [{}]",key);
        if (key == null) {
            return null;
        }
        try {
            String redisCacheKey = getRedisCacheKey(key);
            Object rawValue = redisService.get(redisCacheKey);
            V previous = (V) rawValue;
            redisService.delete(redisCacheKey);
            return previous;
        } catch (Exception e) {
            throw new CacheException(e);
        }
    }

    @Override
    public void clear() throws CacheException {
        log.debug("clear cache");
        Set<String> keys = null;
        try {
            keys = redisService.keys(this.cacheKey + "*");
        } catch (Exception e) {
            log.error("get keys error", e);
        }
        if (keys == null || keys.size() == 0) {
            return;
        }
        for (String key: keys) {
            redisService.delete(key);
        }
    }

    @Override
    public int size() {
        int result = 0;
        try {
            result = redisService.keys(this.cacheKey + "*").size();
        } catch (Exception e) {
            log.error("get keys error", e);
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Set<K> keys() {
        Set<String> keys = null;
        try {
            keys = redisService.keys(this.cacheKey + "*");
        } catch (Exception e) {
            log.error("get keys error", e);
            return Collections.emptySet();
        }
        if (CollectionUtils.isEmpty(keys)) {
            return Collections.emptySet();
        }
        Set<K> convertedKeys = new HashSet<>();
        for (String key:keys) {
            try {
                convertedKeys.add((K) key);
            } catch (Exception e) {
                log.error("deserialize keys error", e);
            }
        }
        return convertedKeys;
    }

    @Override
    public Collection<V> values() {
        Set<String> keys = null;
        try {
            keys = redisService.keys(this.cacheKey + "*");
        } catch (Exception e) {
            log.error("get values error", e);
            return Collections.emptySet();
        }
        if (CollectionUtils.isEmpty(keys)) {
            return Collections.emptySet();
        }
        List<V> values = new ArrayList<V>(keys.size());
        for (String key : keys) {
            V value = null;
            try {
                value = (V) redisService.get(key);
            } catch (Exception e) {
                log.error("deserialize values= error", e);
            }
            if (value != null) {
                values.add(value);
            }
        }
        return Collections.unmodifiableList(values);
    }
    private String getRedisCacheKey(K key) {
        if(null==key){
            return null;
        }else {
            return this.cacheKey+ JwtTokenUtil.getUserId(key.toString());
        }
    }
}
