package com.redbyte.platform.cubogatewaycenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@SpringBootApplication
public class CuboGatewayCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(CuboGatewayCenterApplication.class, args);
    }

    /**
     * 路由
     * @return
     */
    @Bean
    public RouteLocator routeLocator() {
        return null;
    }

    /**
     * 限流
     */

    /**
     * 熔断
     */
}
