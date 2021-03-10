package com.redbyte.platform.cubogatewaycenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class CuboGatewayCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(CuboGatewayCenterApplication.class, args);
    }

}
