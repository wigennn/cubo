package com.redbyte.platform.authoritycenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
@MapperScan(basePackages = {"com.redbyte.platform.authoritycenter.core.dao"})
public class AuthorityCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorityCenterApplication.class, args);
    }

    @Bean("loginThreadPool")
    public ThreadPoolExecutor loginThreadPool() {
        return new ThreadPoolExecutor(5, 5, 60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(100));
    }

}
