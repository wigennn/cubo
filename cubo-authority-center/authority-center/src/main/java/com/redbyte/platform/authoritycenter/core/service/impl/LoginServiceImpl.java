package com.redbyte.platform.authoritycenter.core.service.impl;

import com.redbyte.platform.authoritycenter.config.security.LoginUserDetail;
import com.redbyte.platform.authoritycenter.core.service.LoginService;
import com.redbyte.platform.authoritycenter.core.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * </p>
 *
 * @author wangwq
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @Override
    public String login(String userName, String password) throws Exception {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
        LoginUserDetail userDetail = (LoginUserDetail) authentication.getPrincipal();

        // 记录登陆信息

        return tokenService.createToken(userDetail);
    }
}
