package com.redbyte.platform.authoritycenter.controller;

import com.redbyte.platform.authoritycenter.core.service.LoginService;
import com.redbyte.platform.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *
 * </p>
 *
 * @author wangwq
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping("/loginCheck")
    public Response login(String userName, String password, HttpServletRequest request) {

        try {
            String token = loginService.login(userName, password, request);
            return Response.ok(token);
        } catch (Exception e) {
            return Response.error(e.getMessage());
        }
    }
}
