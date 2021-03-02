package com.redbyte.platform.authoritycenter.api;

import com.redbyte.platform.authorityapi.service.LoginService;
import com.redbyte.platform.authoritycenter.core.service.IUserService;
import com.redbyte.platform.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangwq
 */
@RestController
public class LoginServiceImpl implements LoginService {

    @Autowired
    private IUserService userService;

    @Override
    public Response login() {
        return null;
    }
}
