package com.redbyte.platform.authoritycenter.micro;

import com.redbyte.platform.authorityapi.service.TokenApi;
import com.redbyte.platform.authoritycenter.core.service.TokenService;
import com.redbyte.platform.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * </p>
 *
 * @author wangwq
 */
@RestController
public class TokenApiImpl implements TokenApi {

    @Autowired
    private TokenService tokenService;

    @Override
    public Response<Boolean> verifyToken(String token) {
        try {
            return Response.ok(tokenService.verifyToken(token));
        } catch (Exception e) {
            return Response.error(e.getMessage());
        }
    }
}
