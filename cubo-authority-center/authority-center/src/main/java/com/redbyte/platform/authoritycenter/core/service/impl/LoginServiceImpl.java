package com.redbyte.platform.authoritycenter.core.service.impl;

import com.redbyte.platform.authoritycenter.config.security.LoginUserDetail;
import com.redbyte.platform.authoritycenter.core.entity.LoginLog;
import com.redbyte.platform.authoritycenter.core.entity.User;
import com.redbyte.platform.authoritycenter.core.service.ILoginLogService;
import com.redbyte.platform.authoritycenter.core.service.IUserService;
import com.redbyte.platform.authoritycenter.core.service.LoginService;
import com.redbyte.platform.authoritycenter.core.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadPoolExecutor;

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
    @Autowired
    private ILoginLogService loginLogService;
    @Autowired
    private ThreadPoolExecutor loginThreadPool;
    @Autowired
    private IUserService userService;

    @Override
    public String login(String userName, String password, HttpServletRequest request) throws Exception {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
        LoginUserDetail userDetail = (LoginUserDetail) authentication.getPrincipal();

        // 记录登陆信息
        loginThreadPool.execute(() -> recordLoginLog(userName, request));

        return tokenService.createToken(userDetail);
    }

    private void recordLoginLog(String userName, HttpServletRequest request) {
        User user = null;

        try {
            user = userService.findByUserName(userName);
            if (user == null) return;
        } catch (Exception e) {
            e.printStackTrace();
        }

        LoginLog loginLog = new LoginLog();
        loginLog.setUserId(user.getId());
        loginLog.setIpAddress(request.getRemoteHost());
        loginLog.setUserName(userName);
        loginLog.setOperationRecord("登陆系统");
        loginLogService.save(loginLog);
    }
}
