package com.redbyte.platform.cubouser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
public class CuboUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(CuboUserApplication.class, args);
    }

}
