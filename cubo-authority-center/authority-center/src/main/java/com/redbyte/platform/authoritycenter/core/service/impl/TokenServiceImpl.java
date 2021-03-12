package com.redbyte.platform.authoritycenter.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.redbyte.platform.authoritycenter.common.Constants;
import com.redbyte.platform.authoritycenter.config.security.LoginUserDetail;
import com.redbyte.platform.authoritycenter.core.service.TokenService;
import com.redbyte.platform.authoritycenter.util.IdUtils;
import com.redbyte.platform.authoritycenter.util.RedisCache;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *
 * </p>
 *
 * @author wangwq
 */
@Slf4j
@Service
public class TokenServiceImpl implements TokenService {

    @Value("${token.header}")
    private String tokenHeader;
    @Value("${token.secret}")
    private String tokenSecret;
    @Value("${token.expire.time}")
    private String tokenExpireTime;

    @Autowired
    private RedisCache redisCache;

    @Override
    public String createToken(LoginUserDetail loginUserDetail) throws Exception {
        String token = loginUserDetail.getUser().getId() + "-" + IdUtils.randomUUID();

        // 刷新token
        refreshToken(loginUserDetail);

        Map<String, Object> claims = new HashMap<>();
        claims.put(Constants.LOGIN_USER_KEY, token);
        return createToken(claims);
    }

    @Override
    public LoginUserDetail getLoginUser(HttpServletRequest request) throws Exception {
        String token = getToken(request);

        if (StringUtils.isEmpty(token)) return null;

        // 解析token
        Claims claims = parseToken(token);
        String uuid = (String) claims.get(Constants.LOGIN_USER_KEY);
        String userKey = getUserKey(uuid);
        LoginUserDetail loginUserDetail = redisCache.getCacheObject(userKey);

        return loginUserDetail;
    }

    @Override
    public boolean verifyToken(LoginUserDetail loginUserDetail) throws Exception {
        long currentTime = System.currentTimeMillis();
        long expireTime = loginUserDetail.getExpireTime();

        // 如果时间未过期 刷新
        if (expireTime - currentTime < expireTime) {
            refreshToken(loginUserDetail);
            return true;
        }

        return false;
    }

    @Override
    public boolean verifyToken(String token) throws Exception {

        // 解析token
        Claims claims = parseToken(token);
        String uuid = (String) claims.get(Constants.LOGIN_USER_KEY);
        String userKey = getUserKey(uuid);
        LoginUserDetail loginUserDetail = redisCache.getCacheObject(userKey);

        return verifyToken(loginUserDetail);
    }

    @Override
    public void delLoginUser(String loginUserToken) throws Exception {
        if (StringUtils.isEmpty(loginUserToken)) {
            String userKey = getUserKey(loginUserToken);
            redisCache.deleteObject(userKey);
        }
    }

    private String createToken(Map<String, Object> claims) {
        return Jwts.builder().setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, tokenSecret)
                .compact();
    }

    private void refreshToken(LoginUserDetail loginUserDetail) {
        String userKey = getUserKey(loginUserDetail.getToken());
        loginUserDetail.setExpireTime(System.currentTimeMillis() + Long.valueOf(tokenExpireTime));

        redisCache.setCacheObject(userKey, JSON.toJSONString(loginUserDetail), Integer.parseInt(tokenExpireTime), TimeUnit.MILLISECONDS);
    }

    private Claims parseToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(tokenSecret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            log.error(e.getMessage());

        }
        return claims;
    }

    private String getToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        if (!StringUtils.isEmpty(token) && token.startsWith(Constants.TOKEN_PREFIX)) {
            token = token.replace(Constants.TOKEN_PREFIX, "");
        }

        return token;
    }

    private String getUserKey(String uuid) {
        return Constants.LOGIN_USER_KEY + uuid;
    }
}
