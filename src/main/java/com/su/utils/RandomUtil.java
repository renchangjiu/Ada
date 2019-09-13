package com.su.utils;

import java.util.Random;

public class RandomUtil {

    /**
     * 产生count 位的随机整数
     *
     * @param count 随机数位数
     * @return 随机数
     */
    public static String getRandomNumber(int count) {
        StringBuilder sb = new StringBuilder();
        String str = "0123456789";
        Random r = new Random();
        for (int i = 0; i < count; i++) {
            int num = r.nextInt(str.length());
            sb.append(str.charAt(num));
            str = str.replace((str.charAt(num) + ""), "");
        }
        return sb.toString();
    }

    /**
     * 产生count 位的随机整数+26个英文字母
     *
     * @param count 随机数位数
     * @return 随机数
     */
    public static String getRandomChar(int count) {
        StringBuilder sb = new StringBuilder();
        String chars = "0123456789abcdefghijklmnopqrstuvwxyz";
        int charsLength = chars.length();
        Random r = new Random();
        for (int i = 0; i < count; i++) {
            int num = r.nextInt(charsLength);
            sb.append(chars.charAt(num));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Log.println(getRandomChar(12));
    }

}
