package com.redbyte.platform.cubogateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 *
 * </p>
 *
 * @author wangwq
 */
@Configuration
public class RouterConfiguration {

    @Bean
    public RouteLocator buildRouteLocater(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("path_route", r -> r.path("/baidu")
                        .uri("https://www.baidu.com/"))
                .build();
    }
}
