package com.redbyte.platform.authoritycenter;

import com.redbyte.platform.authoritycenter.core.service.RegisterService;
import com.redbyte.platform.authoritycenter.domain.RegisterUserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SpringBootTest
class AuthorityCenterApplicationTests {

    @Autowired
    private RegisterService registerService;

    @Test
    void contextLoads() throws Exception {
        RegisterUserDTO registerUserDTO = new RegisterUserDTO();

        registerService.save(registerUserDTO);
    }

    @Test
    void registerUserTest() {
        try {
            RegisterUserDTO registerUserDTO = new RegisterUserDTO();
            registerUserDTO.setUserName("13151559017");
            registerUserDTO.setName("王伟庆");
            registerUserDTO.setEmail("wigen96@163.com");
            registerUserDTO.setPassword("123456");
            registerUserDTO.setPhone("13151559017");

            registerService.save(registerUserDTO);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void testRedis() {
        JedisShardInfo jedis = new JedisShardInfo("139.196.205.76", 7001);
        ShardedJedis shardedJedis = new ShardedJedis(Arrays.asList(jedis));

        System.out.println(shardedJedis.get("wigen"));
    }

    @Test
    void testRedisCluster() {
        Set<HostAndPort> set = new HashSet<>();
        set.add(new HostAndPort("139.196.205.76", 7001));
        set.add(new HostAndPort("139.196.205.76", 7002));
        set.add(new HostAndPort("139.196.205.76", 7003));
        set.add(new HostAndPort("139.196.205.76", 7004));
        set.add(new HostAndPort("139.196.205.76", 7005));
        set.add(new HostAndPort("139.196.205.76", 7006));
        JedisCluster jedisCluster = new JedisCluster(set);
        jedisCluster.set("wigen", "wigen123");
        System.out.println(jedisCluster.get("wigen"));
    }

}
