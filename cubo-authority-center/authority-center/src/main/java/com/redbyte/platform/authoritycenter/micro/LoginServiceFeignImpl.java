package com.redbyte.platform.authoritycenter.micro;

import com.redbyte.platform.authorityapi.service.LoginService;
import com.redbyte.platform.common.Response;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangwq
 */
@RestController
public class LoginServiceFeignImpl implements LoginService {

    @Override
    public Response login() {
        return null;
    }
}
