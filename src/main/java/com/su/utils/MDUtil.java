package com.su.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * md5工具类
 *
 * @author su
 */
public class MDUtil {

    public static String getMD5(String source) {
        try {
            // 获得实现指定摘要算法的 MessageDigest 对象
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 获得存放哈希值结果的 byte 数组
            byte[] md5Bytes = md.digest(source.getBytes());
            // 转成16进制字符串
            return new BigInteger(1, md5Bytes).toString(16);
        } catch (NoSuchAlgorithmException e) {
            return "00000000000000000000000000000000";
        }
    }

    public static String getMD5(String source, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (salt != null) {
                source = salt + source;
            }
            // 获得存放哈希值结果的 byte 数组
            byte[] md5Bytes = md.digest(source.getBytes());
            // 转成16进制字符串
            String result = new BigInteger(1, md5Bytes).toString(16);
            return result;
        } catch (NoSuchAlgorithmException e) {
            return "00000000000000000000000000000000";
        }
    }


    public static String getSHA(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA");
            byte[] SHABytes = md.digest(str.getBytes());
            String result = new BigInteger(1, SHABytes).toString(16);
            return result;
        } catch (NoSuchAlgorithmException e) {
            return "00000000000000000000000000000000";
        }
    }

    public static void getFileMD5() {
        FileInputStream fis = null;
        try {
            File file = new File("D:/text.txt");
            fis = new FileInputStream(file);
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] buffer = new byte[1024];
            int length = -1;
            while ((length = fis.read(buffer, 0, 1024)) != -1) {
                md.update(buffer, 0, length);
            }
            BigInteger bigInt = new BigInteger(1, md.digest());
            System.out.println("文件MD5: " + bigInt.toString(16));
        } catch (FileNotFoundException e) {
            System.out.println("程序异常: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
