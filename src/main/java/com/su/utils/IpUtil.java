package com.su.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取请求 的ip
 *
 * @author su
 */
public class IpUtil {
    /**
     * 获取访问用户的客户端IP
     */
    public static String getIp(final HttpServletRequest request) {
        String ipString = request.getHeader("x-forwarded-for");
        if (ipString == null || ipString.trim().length() == 0 || "unknown".equalsIgnoreCase(ipString)) {
            ipString = request.getHeader("Proxy-Client-IP");
        }
        if (ipString == null || ipString.trim().length() == 0 || "unknown".equalsIgnoreCase(ipString)) {
            ipString = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipString == null || ipString.trim().length() == 0 || "unknown".equalsIgnoreCase(ipString)) {
            ipString = request.getRemoteAddr();
        }

        // 多个路由时，取第一个非unknown的ip  
        final String[] arr = ipString.split(",");
        for (final String str : arr) {
            if (!"unknown".equalsIgnoreCase(str)) {
                ipString = str;
                break;
            }
        }
        return ipString;
    }
}
