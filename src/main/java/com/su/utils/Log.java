package com.su.utils;

public final class Log {

    public static void log(Object o) {
        System.out.println(o);
    }

    /**
     * 控制台打印三行标记
     */
    public static void makeMark() {
        System.out.println("============================================================================");
        System.out.println("============================================================================");
        System.out.println("============================================================================");
    }

    /**
     * 控制台打印指定行数的标记
     *
     * @param lines 打印的行数
     */
    public static void makeMark(int lines) {
        for (int i = 0; i < lines; i++) {
            System.out.println("============================================================================");
        }
    }


    public static void println(Object o) {
        System.out.println(o);
    }

    public static void print(Object o) {
        System.out.print(o);
    }

    public static void println() {
        System.out.println();
    }


}
