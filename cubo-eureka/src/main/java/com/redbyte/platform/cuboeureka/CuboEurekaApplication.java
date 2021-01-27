package com.redbyte.platform.cuboeureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class CuboEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CuboEurekaApplication.class, args);
    }

}
