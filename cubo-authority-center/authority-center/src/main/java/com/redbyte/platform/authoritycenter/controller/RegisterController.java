package com.redbyte.platform.authoritycenter.controller;

import com.redbyte.platform.authoritycenter.core.entity.User;
import com.redbyte.platform.common.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * </p>
 *
 * @author wangwq
 */
@RestController
@RequestMapping("/register")
public class RegisterController {

    @RequestMapping("/save")
    public Response<User> save() {

        return new Response<>();
    }
}
