package com.redbyte.platform.authoritycenter.util;

import java.util.UUID;

/**
 * <p>
 *
 * </p>
 *
 * @author wangwq
 */
public class IdUtils {

    public static String randomUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
