package com.redbyte.platform.authorityapi.service;

import com.redbyte.platform.common.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author wangwq
 */
@FeignClient("authority-center")
public interface LoginService {

    @PostMapping("/login")
    Response login();
}
