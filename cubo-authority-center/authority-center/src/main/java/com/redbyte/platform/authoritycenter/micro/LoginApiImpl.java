package com.redbyte.platform.authoritycenter.micro;

import com.redbyte.platform.authorityapi.service.LoginApi;
import com.redbyte.platform.common.Response;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangwq
 */
@RestController
public class LoginApiImpl implements LoginApi {

    @Override
    public Response login() {
        return null;
    }
}
