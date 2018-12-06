package com.neuedu.planewar.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import javax.imageio.ImageIO;

/**
 * 用来获取游戏中的所有资源（声音、音频、图片等）
 * 
 * @author 杜晓鹏
 *
 */
public class GameUtil {
	/**
	 * 加载properties文件 用来修改项目中，经常要改的内容（对外可见）
	 * 
	 * @param imgPath
	 * 
	 */
	public static Properties prop = new Properties();
	static {
		try {
			prop.load(GameUtil.class.getClassLoader().getResourceAsStream("planegame.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getProperty(String key) {

		return prop.getProperty(key);
	}

	public static int getIntProperty(String key) {

		return Integer.parseInt(prop.getProperty(key));
	}

	// 使用io獲取圖片
	public static Image getImage(String imgPath) {
		URL url = FrameUtil.class.getClassLoader().getResource(imgPath);
		BufferedImage img = null;
		try {
			img = ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}
}
