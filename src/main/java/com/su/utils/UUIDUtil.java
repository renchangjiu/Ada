package com.su.utils;

import java.util.UUID;

/**
 * 随机生成id
 *
 * @author su
 */
public class UUIDUtil {

    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }

}
