package com.su.utils;

import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;


//等比例缩略图
public class ImageUtil {
    // 最大宽度及高度
    private static final int WIDTH = 640;
    private static final int HEIGHT = 360;

    public static File getZoomImage(File quondamFile) {
        // 目标图片
        String targetPicPath = "E:/test/" + "2.png";
        FileOutputStream out;
        try {
            out = new FileOutputStream(targetPicPath);
            Image image = ImageIO.read(quondamFile);  // 构造image 对象

            // 原图的宽度, 高度, 类型
            int width = image.getWidth(null);
            int height = image.getHeight(null);
            String formatName = getFormatName(quondamFile);

            int rate1 = width / WIDTH; // 宽度及高度的缩放比例
            int rate2 = height / HEIGHT;

            int rate = 0;
            // 判断使用哪种缩放比例
            if (rate1 > rate2) {
                rate = rate1;
            } else {
                rate = rate2;
            }

            // 计算缩略图最终的宽高
            int newWidth = width / rate;
            int newHeight = height / rate;

            BufferedImage bufferedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);

            bufferedImage.getGraphics().drawImage(
                    image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);

            ImageIO.write(bufferedImage, formatName, out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ie) {
            ie.printStackTrace();
        }

        return null;

    }

    // 获得图片的类型
    private static String getFormatName(File file) throws IOException {
        ImageInputStream imageInputStream = ImageIO.createImageInputStream(file);
        Iterator<ImageReader> iter = ImageIO.getImageReaders(imageInputStream);

        String formatName = "";
        if (iter != null && iter.hasNext()) {
            ImageReader reader = iter.next();
            formatName = reader.getFormatName(); // 获得图片的类型
            // reader.setInput(imageInputStream, true);
        }
        return formatName;
    }

//	@Test
//	public void test() {
//		File file = new File("E:/test/1.png");
//		String path = file.getAbsolutePath();
//		int index = path.lastIndexOf("\\\\");
//		PU.println(path);
//	}


}
