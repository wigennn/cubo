package com.redbyte.platform.cubogatewaycenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan({"com.redbyte.platform.authorityapi.service"})
public class CuboGatewayCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(CuboGatewayCenterApplication.class, args);
    }
}
