package com.redbyte.platform.authoritycenter.controller;

import com.redbyte.platform.common.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangwq
 */
@RestController
public class WelcomeController {

    @RequestMapping("/welcome")
    public Response<String> welcome() {
        return new Response<String>("welcome");
    }
}
