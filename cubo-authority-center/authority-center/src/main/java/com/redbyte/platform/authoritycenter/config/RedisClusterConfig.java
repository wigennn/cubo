package com.redbyte.platform.authoritycenter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author wangwq
 */
@Configuration
@ConditionalOnClass(JedisCluster.class)
public class RedisClusterConfig extends CachingConfigurerSupport {

    @Autowired
    private RedisClusterConfigProperties redisClusterConfigProperties;

    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(8);
        config.setMaxWaitMillis(-1);
        return config;
    }

    @Bean
    public JedisConnectionFactory redisConnectionFactory() {
        RedisClusterConfiguration configuration = new RedisClusterConfiguration(redisClusterConfigProperties.getNodes());
        configuration.setMaxRedirects(redisClusterConfigProperties.getMaxRedirects());

        JedisConnectionFactory factory = new JedisConnectionFactory(configuration, jedisPoolConfig());
        return factory;
    }

    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        StringRedisTemplate redisTemplate = new StringRedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        return redisTemplate;
    }
}
