package com.redbyte.platform.authoritycenter.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author wangwq
 */
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate redisTemplate;

    public void set(String key, Object val) {
        redisTemplate.opsForValue().set(key, val);
    }
}
