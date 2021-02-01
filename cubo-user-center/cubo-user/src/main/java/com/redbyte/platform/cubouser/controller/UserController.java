package com.redbyte.platform.cubouser.controller;

import com.redbyte.platform.common.Response;
import com.redbyte.platform.cubouserapi.dto.UserInfoDTO;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/micro/user")
public class UserController {

    @GetMapping("/getToken")
    public Response<String> getToken(String userName, String password) {
        return Response.ok("token");
    }

    @GetMapping("/verifyToken")
    public Response<Boolean> verifyToken(String token) {
        return Response.ok(Boolean.FALSE);
    }

    @GetMapping("/getUserInfo")
    public Response<UserInfoDTO> getUserInfo(String uid) {
        return Response.ok(new UserInfoDTO());
    }
}
