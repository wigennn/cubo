package com.redbyte.platform.authoritycenter;

import com.redbyte.platform.authoritycenter.config.redis.RedisClusterConfigProperties;
import com.redbyte.platform.authoritycenter.core.service.RegisterService;
import com.redbyte.platform.authoritycenter.domain.RegisterUserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
class AuthorityCenterApplicationTests {

    @Autowired
    private RegisterService registerService;
    @Autowired
    private RedisClusterConfigProperties redisClusterConfigProperties;

    @Test
    void contextLoads() throws Exception {
        RegisterUserDTO registerUserDTO = new RegisterUserDTO();

        registerService.save(registerUserDTO);
    }

    @Test
    void testRedisCluster() {
        Set<HostAndPort> nodes = new HashSet<>();
        for (String item : redisClusterConfigProperties.getNodes()) {
            String[] items = item.split(":");
            nodes.add(new HostAndPort(items[0], Integer.parseInt(items[1])));
        }

        JedisCluster jedisCluster = new JedisCluster(nodes);
        jedisCluster.get("wigen");
    }

    @Test
    void testRedis() {
        Jedis jedis = new Jedis("127.0.0.1",6379);
        jedis.get("wigen");
    }


    @Test
    void registerTest() throws Exception {
        RegisterUserDTO registerUserDTO = new RegisterUserDTO();
        registerUserDTO.setUserName("12345678901");
        registerUserDTO.setName("谢飞机");
        registerUserDTO.setPassword("123456");
        registerUserDTO.setPhone("12345678901");
        registerUserDTO.setGender(1);
        registerService.save(registerUserDTO);
    }
}
