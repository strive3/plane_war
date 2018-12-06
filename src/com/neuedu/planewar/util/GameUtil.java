package com.neuedu.planewar.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import javax.imageio.ImageIO;

/**
 * ������ȡ��Ϸ�е�������Դ����������Ƶ��ͼƬ�ȣ�
 * 
 * @author ������
 *
 */
public class GameUtil {
	/**
	 * ����properties�ļ� �����޸���Ŀ�У�����Ҫ�ĵ����ݣ�����ɼ���
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

	// ʹ��io�@ȡ�DƬ
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
