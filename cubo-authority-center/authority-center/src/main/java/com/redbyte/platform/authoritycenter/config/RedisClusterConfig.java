package com.redbyte.platform.authoritycenter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

/**
 * @author wangwq
 */
@Configuration
//@ConditionalOnClass(JedisCluster.class)
public class RedisClusterConfig extends CachingConfigurerSupport {

    @Autowired
    private RedisClusterConfigProperties redisClusterConfigProperties;

    @Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;
    @Value("${spring.redis.jedis.pool.min-idle}")
    private int minIdle;
    @Value("${spring.redis.jedis.pool.max-wait}")
    private long maxWait;

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

/*    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMinIdle(minIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWait);
        return jedisPoolConfig;
    }*/

    @Bean
    public JedisConnectionFactory jedisConnectionFactory(RedisClusterConfiguration clusterConfiguration) {
        JedisConnectionFactory factory = new JedisConnectionFactory(clusterConfiguration);
        return factory;
    }

    @Bean
    public RedisTemplate redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
        StringRedisTemplate redisTemplate = new StringRedisTemplate(jedisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
