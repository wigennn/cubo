package com.redbyte.platform.common;

/**
 * <p>
 *
 * </p>
 *
 * @author wangwq
 */
public interface ResponseCode {

    // message
    String CONTINUE = "100";
    String SWITCHING_PROTOCOLS = "101";
    String PROCESSING = "102";

    // success
    String OK = "200";

    // redirect
    String MULTI_CHOICES = "300";
    String REDIRECT = "302";

    // 请求错误
    String BAD_REQUEST = "400";
    String NOT_FOUND = "404";

    // 服务器错误
    String INTERNAL_SERVER = "500";

}
