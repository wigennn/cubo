package com.redbyte.platform.authoritycenter.controller;

import com.redbyte.platform.authoritycenter.core.entity.User;
import com.redbyte.platform.authoritycenter.core.service.RegisterService;
import com.redbyte.platform.authoritycenter.domain.RegisterUserDTO;
import com.redbyte.platform.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private RegisterService registerService;

    @RequestMapping("/save")
    public Response<User> save(RegisterUserDTO registerUserDTO) {
        try {
            registerService.save(registerUserDTO);
        } catch (Exception e) {
            return Response.error(e.getMessage());
        }
        return new Response<>();
    }
}
