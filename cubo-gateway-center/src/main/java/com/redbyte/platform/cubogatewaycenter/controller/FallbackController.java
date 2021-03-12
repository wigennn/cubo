package com.redbyte.platform.cubogatewaycenter.controller;

import com.redbyte.platform.common.Response;
import com.redbyte.platform.common.ResponseCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * </p>
 *
 * @author wangwq
 */
@RestController
public class FallbackController {

    @GetMapping("/fallback")
    public Response fallback() {
        return Response.error(ResponseCode.BAD_REQUEST, "服务暂时不可用");
    }
}
