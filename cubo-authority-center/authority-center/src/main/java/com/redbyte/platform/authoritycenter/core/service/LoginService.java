package com.redbyte.platform.authoritycenter.core.service;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *
 * </p>
 *
 * @author wangwq
 */
public interface LoginService {

    String login(String userName, String password, HttpServletRequest request) throws Exception;
}
