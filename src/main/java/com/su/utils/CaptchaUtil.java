package com.su.utils;

import com.su.pojo.Captcha;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;
import java.util.Random;


/**
 * 生成随机数字或字母串，以图像方式显示，用于人工识别，使程序很难识别。
 * 减小系统被程序自动攻击的可能性。
 * 生成的图形颜色由红、黑、蓝、紫4中随机组合而成，数字或字母垂直方向位置在
 * 一定范围内也是随机的，减少被程序自动识别的几率。
 * 由于数字的0，1，2易和字母的o，l,z混淆，使人眼难以识别，因此不生成数字和字母的混合串。
 * 生成的串字母统一用小写，串的最大长度为16。
 */

public class CaptchaUtil {

    //字符的高度和宽度，单位为像素
    private int wordHeight = 10;
    private int wordWidth = 15;

    //字符大小
    private int fontSize = 16;

    //垂直方向起始位置
    private final int initypos = 5;

    //要生成的字符个数，由工厂方法得到
    private int charCount = 0;

    //颜色数组，绘制字串时随机选择一个
    private static final Color[] CHAR_COLOR = {Color.WHITE};

    //随机数生成器
    private Random r = new Random();

    /**
     * 生成图像的格式常量，JPEG格式,生成为文件时扩展名为.jpg；
     * 输出到页面时需要设置MIME type 为image/jpeg
     */
    private static String GRAPHIC_JPEG = "JPEG";

    /**
     * 生成图像的格式常量，PNG格式,生成为文件时扩展名为.png；
     * 输出到页面时需要设置MIME type 为image/png
     */
    private static String GRAPHIC_PNG = "PNG";


    private CaptchaUtil(int charCount) {
        this.charCount = charCount;
    }

    /**
     * 随机生成一个数字串，并以图像方式绘制，绘制结果输出到流out中
     *
     * @param graphicFormat 设置生成的图像格式，值为GRAPHIC_JPEG或GRAPHIC_PNG
     * @param out           图像结果输出流
     * @return 随机生成的串的值
     * @throws IOException
     */

    public String drawNumber(String graphicFormat, OutputStream out) throws IOException {
        // 随机生成的串的值
        String charValue = "";
        /*charValue = randNumber();*/
        charValue = randAlphaStr(4);
        return draw(charValue, graphicFormat, out);
    }


    /**
     * 随机生成一个字母串，并以图像方式绘制，绘制结果输出到流out中
     *
     * @param graphicFormat 设置生成的图像格式，值为GRAPHIC_JPEG或GRAPHIC_PNG
     * @param out           图像结果输出流
     * @return 随机生成的串的值
     * @throws IOException
     */

    public String drawAlpha(String graphicFormat, OutputStream out) throws IOException {
        //  随机生成的串的值
        String charValue = "";
        charValue = randAlphaStr(4);
        return draw(charValue, graphicFormat, out);
    }

    // 给定范围获得随机颜色
    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    /**
     * 以图像方式绘制字符串，绘制结果输出到流out中
     *
     * @param charValue     要绘制的字符串
     * @param graphicFormat 设置生成的图像格式，值为GRAPHIC_JPEG或GRAPHIC_PNG
     * @param out           图像结果输出流
     * @return 随机生成的串的值
     * @throws IOException
     */

    private String draw(String charValue, String graphicFormat, OutputStream out) throws IOException {
        int width = (charCount + 2) * wordWidth;
        int height = wordHeight * 3;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 创建一个随机数生成器类。
        Random random = new Random();
        // 获取图形上下文
        Graphics g = image.getGraphics();
        // 设定背景色
        g.setColor(getColor(100));
        g.fillRect(0, 0, width, height);
        // 设定字体
        g.setFont(new Font("宋体", Font.BOLD, 18));
        // 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.setColor(getColor(25));
            g.drawLine(x, y, x + xl, y + yl);
        }
        // 绘制charValue,每个字符颜色随机
        for (int i = 0; i < charCount; i++) {
            String c = charValue.substring(i, i + 1);
            Color color = CHAR_COLOR[randomInt(0, CHAR_COLOR.length)];
            g.setColor(color);
            int xpos = (i + 1) * wordWidth;
            // 垂直方向上随机
            int ypos = randomInt(initypos + wordHeight, initypos + wordHeight * 2);
            g.drawString(c, xpos, ypos);
        }

        g.dispose();
        image.flush();
        // 输出到流
        ImageIO.write(image, graphicFormat, out);
        return charValue;
    }

    /*** 随机返回一种颜色,透明度0~255 0表示全透
     * @return 随机返回一种颜色
     * @param alpha 透明度0~255 0表示全透
     */
    private Color getColor(int alpha) {
        int R = (int) (Math.random() * 255);
        int G = (int) (Math.random() * 255);
        int B = (int) (Math.random() * 255);
        return new Color(R, G, B, alpha);
    }

    private String drawInputstr(int num, String graphicFormat, OutputStream out) throws IOException {
        String charValue = randAlphaStr(num);
        int width = (charCount + 2) * wordWidth;
        int height = wordHeight * 3;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        // 创建一个随机数生成器类。
        Random random = new Random();
        // 获取图形上下文
        Graphics g = image.getGraphics();
        // 设定背景色
        g.setColor(getColor(80));
        g.fillRect(0, 0, width, height);
        //设置干扰点
        CreateRandomPoint(width, height, 50, g, 255);
        // 设定字体
        g.setFont(new Font("宋体", Font.BOLD, 18));
        // 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 135; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.setColor(getColor(200));
            g.drawLine(x, y, x + xl, y + yl);
        }
        // 绘制charValue,每个字符颜色随机
        for (int i = 0; i < charCount; i++) {
            String c = charValue.substring(i, i + 1);
            Color color = CHAR_COLOR[randomInt(0, CHAR_COLOR.length)];
            g.setColor(color);
            int xpos = (i + 1) * wordWidth;
            // 垂直方向上随机
            int ypos = randomInt(initypos + wordHeight, initypos + wordHeight * 2);
            g.drawString(c, xpos, ypos);
        }

        g.dispose();
        image.flush();
        // 输出到流
        ImageIO.write(image, graphicFormat, out);
        return charValue;
    }

    // 生成随机数字串
    protected String randNumber() {
        StringBuilder charValue = new StringBuilder();
        for (int i = 0; i < charCount; i++) {
            charValue.append(String.valueOf(randomInt(0, 10)));
        }
        return charValue.toString();
    }


    // 生成随机字母串
    private String randAlpha() {
        StringBuilder charValue = new StringBuilder();
        for (int i = 0; i < charCount; i++) {
            char c = (char) (randomInt(0, 26) + 'a');
            charValue.append(String.valueOf(c));
        }
        return charValue.toString();
    }

    // 生成随机字符串
    private String randAlphaStr(int num) {
        StringBuilder charValue = new StringBuilder();
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        for (int i = 0; i < num; i++) {
            int number = random.nextInt(62);
            charValue.append(str.charAt(number));
        }
        return charValue.toString();
    }


    /**
     * 返回[from,to)之间的一个随机整数
     *
     * @param from 起始值
     * @param to   结束值
     * @return [from, to)之间的一个随机整数
     */
    private int randomInt(int from, int to) {
        return from + r.nextInt(to - from);
    }

    /**
     * 随机产生干扰点
     *
     * @param alpha 透明度0~255 0表示全透
     */
    private void CreateRandomPoint(int width, int height, int many, Graphics g, int alpha) {  // 随机产生干扰点
        Random random = new Random();
        for (int i = 0; i < many; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            g.setColor(getColor(alpha));
            g.drawOval(x, y, random.nextInt(3), random.nextInt(3));
        }
    }

    public static Captcha getCaptcha() {
        try {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            String value = new CaptchaUtil(4).drawInputstr(4, CaptchaUtil.GRAPHIC_PNG, output);

            // BASE64Encoder encoder = new BASE64Encoder();
            // String imageBase64 = encoder.encode(output.toByteArray());// 返回Base64编码过的字节数组字符串
            // String imageBase64 = Base64.en
            byte[] bytes = Base64.getEncoder().encode(output.toByteArray());
            String imageBase64 = new String(bytes);


            return new Captcha(UUIDUtil.uuid(), imageBase64, value);
        } catch (IOException e) {
            return null;
        }
    }


    public static void main(String[] args) throws Exception {
        Log.println(getCaptcha());
    }


}