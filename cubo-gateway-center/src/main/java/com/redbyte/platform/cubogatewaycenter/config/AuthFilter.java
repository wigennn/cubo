package com.redbyte.platform.cubogatewaycenter.config;

import com.redbyte.platform.authorityapi.service.TokenApi;
import com.redbyte.platform.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


/**
 * <p>
 * 认证
 * </p>
 *
 * @author wangwq
 */
@Component
public class AuthFilter implements GlobalFilter {

    @Autowired
    private TokenApi tokenApi;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getHeaders().getFirst("token");

        // 校验token
        Response<Boolean> tokenResp = tokenApi.verifyToken(token);
        if (tokenResp.getData()) {
            return chain.filter(exchange);
        }

        ServerHttpResponse response = exchange.getResponse();
        Response data = new Response("401", "非法请求");
        DataBuffer dataBuffer = response.bufferFactory().wrap(data.toString().getBytes());
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");

        return response.writeWith(Mono.just(dataBuffer));
    }
}
