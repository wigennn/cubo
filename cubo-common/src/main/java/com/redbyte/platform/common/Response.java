package com.redbyte.platform.common;

import java.util.HashMap;

/**
 * <p>
 *
 * </p>
 *
 * @author wangwq
 */
public class Response<T> extends HashMap<String, Object> {
    private static final long serialVersionUID = 5793462573209575823L;

    private static final String CODE_TAG = "code";
    private static final String MSG_TAG = "msg";
    private static final String DATA_TAG = "data";

    private String code;
    private String msg;
    private T data;

    public Response() {
        super.put(CODE_TAG, ResponseCode.OK);
    }

    public Response(T data) {
        super.put(CODE_TAG, ResponseCode.OK);
        super.put(DATA_TAG, data);
    }

    public Response(String code, Object msg) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
    }

    public Response(String code, String msg, T data) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
        super.put(DATA_TAG, data);
    }

    public static Response ok() {
        return new Response();
    }

    public static <T> Response ok(T data) {
        return new Response(data);
    }

    public static Response error(Object msg) {
        return new Response(ResponseCode.INTERNAL_SERVER, msg);
    }

    public static Response error(String code, String msg) {
        return new Response(code, msg);
    }
}
