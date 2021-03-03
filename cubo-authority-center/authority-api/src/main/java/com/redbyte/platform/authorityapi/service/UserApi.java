package com.redbyte.platform.authorityapi.service;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author wangwq
 */
@FeignClient("authority-center")
public interface UserApi {
}
