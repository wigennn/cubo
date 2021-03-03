package com.redbyte.platform.authoritycenter.core.service;

import com.redbyte.platform.authoritycenter.config.security.LoginUserDetail;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *
 * </p>
 *
 * @author wangwq
 */
public interface TokenService {

    LoginUserDetail getLoginUser(HttpServletRequest request) throws Exception;

    String createToken(LoginUserDetail loginUserDetail) throws Exception;

    boolean verifyToken(LoginUserDetail loginUserDetail) throws Exception;
}
