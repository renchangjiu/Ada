package com.su.utils;

public class CommonUtil {

    public static boolean inArray(String[] array, String str) {
        for (String s : array) {
            if (str.equals(s)) {
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        String[] array = new String[]{
                "a", "b", "c", "1",
        };
        String str = 1 + "";
        Log.println(inArray(array, str));
    }

}
