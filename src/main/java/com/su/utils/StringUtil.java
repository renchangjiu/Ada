package com.su.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @author su
 * @date 2019/10/16 11:04
 */
public class StringUtil extends StringUtils {

    public static String htmlSpecialCharsEncode(String str) {
        str = str.replaceAll("&", "&amp;");
        str = str.replaceAll("<", "&lt;");
        str = str.replaceAll(">", "&gt;");
        return str;
    }


    public static boolean allIsNotEmpty(String... args) {
        boolean res = true;
        for (String str : args) {
            if (isEmpty(str)) {
                res = false;
                break;
            }
        }
        return res;
    }


    public static boolean allIsNotBlank(String... args) {
        boolean res = true;
        for (String arg : args) {
            if (isBlank(arg)) {
                res = false;
                break;
            }
        }
        return res;
    }


}
