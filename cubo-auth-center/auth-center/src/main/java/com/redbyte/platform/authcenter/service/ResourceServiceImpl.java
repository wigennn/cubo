package com.redbyte.platform.authcenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

/**
 * @author wangwq
 */
@Service
public class ResourceServiceImpl {

    private Map<String, List<String>> resourceRolesMap;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @PostConstruct
    public void initData() {
        redisTemplate.opsForHash().putAll(null, null);
    }
}
