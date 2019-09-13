package com.su.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 对 spring-boot-starter-data-redis 的简单封装
 */
@Component
public class RedisOperator {

    // private final RedisTemplate redis;

    private final StringRedisTemplate redis;

    @Autowired
    public RedisOperator(StringRedisTemplate redis) {
        this.redis = redis;
    }

    public void expire(String key, long timeout, TimeUnit timeUnit) {
        redis.expire(key, timeout, timeUnit);
    }

    public String get(Object key) {
        return redis.opsForValue().get(key);
    }

    public void set(String key, String value) {
        redis.opsForValue().set(key, value);
    }

    public boolean delete(String key) {
        return redis.delete(key);
    }

    public boolean exists(Object key) {
        String value = redis.opsForValue().get(key);
        return value != null;
    }


}
