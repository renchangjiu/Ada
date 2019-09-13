package com.su.utils;


public class StringUtil {

    public static String htmlSpecialCharsEncode(String str) {
        str = str.replaceAll("&", "&amp;");
        str = str.replaceAll("<", "&lt;");
        str = str.replaceAll(">", "&gt;");
        return str;
    }


    public static boolean isEmpty(String str) {
        boolean res = true;
        // if (str != null && !str.trim().equals("")) {
        if (str != null && !"".equals(str.trim())) {
            res = false;
        }
        return res;
    }

    public static boolean isBlank(String str) {
        boolean res = true;
        // if (str != null && !str.equals("")) {
        if (str != null && !"".equals(str.trim())) {
            if (str.replaceAll("\\s", "").length() > 0) {
                res = false;
            }
        }
        return res;
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
