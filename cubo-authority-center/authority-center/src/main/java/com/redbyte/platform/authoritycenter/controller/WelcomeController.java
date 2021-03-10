package com.redbyte.platform.authoritycenter.controller;

import com.redbyte.platform.common.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wangwq
 */
@Slf4j
@RestController
public class WelcomeController {

    @RequestMapping("/welcome")
    public Response<String> welcome(HttpServletRequest request) {
        log.info("请求ip: {}, port: {}", request.getRemoteHost(), request.getRemotePort());
        return new Response<String>("welcome");
    }

    @RequestMapping("/loginTest")
    public Response<String> loginTest() {
        return new Response<>("loginTest");
    }
}
