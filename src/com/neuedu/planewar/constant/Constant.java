package com.neuedu.planewar.constant;

import com.neuedu.planewar.util.GameUtil;
/**
 * 
 * @author 杜晓鹏
 *
 */
public class Constant {
	/**
	 * 窗口宽度
	 */
	public static final int GAME_WIDTH = GameUtil.getIntProperty("GAME_WIDTH");
	/**
	 * 窗口高度
	 */
	public static final int GAME_HEIGHT = GameUtil.getIntProperty("GAME_HEIGHT");
	/**
	 * 存放图片路径的前缀
	 */
	public static final String IMAGE_PATH = GameUtil.getProperty("IMAGE_PATH");
}
