package com.redbyte.platform.authoritycenter.config.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

/**
 * @author wangwq
 */
@Configuration
public class RedisClusterConfig extends CachingConfigurerSupport {

    @Autowired
    private RedisClusterConfigProperties redisClusterConfigProperties;

    @Bean
    public RedisClusterConfiguration redisClusterConfiguration() {
        RedisClusterConfiguration configuration = new RedisClusterConfiguration(redisClusterConfigProperties.getNodes());
        configuration.setMaxRedirects(redisClusterConfigProperties.getMaxAttempts());
        return configuration;
    }

    @Bean
    public JedisCluster jedisCluster() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        Set<HostAndPort> nodes = new HashSet<>();
        for (String item : redisClusterConfigProperties.getNodes()) {
            String[] items = item.split(":");
            nodes.add(new HostAndPort(items[0], Integer.parseInt(items[1])));
        }
        return new JedisCluster(nodes, jedisPoolConfig);
    }

}
