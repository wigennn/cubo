package com.redbyte.platform.authorityapi.service;

import com.redbyte.platform.common.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * <p>
 *
 * </p>
 *
 * @author wangwq
 */
@FeignClient("authority-center")
public interface TokenApi {

    @PostMapping("/verifyToken")
    Response<Boolean> verifyToken(String token);
}
