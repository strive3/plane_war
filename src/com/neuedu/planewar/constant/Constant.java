package com.neuedu.planewar.constant;

import com.neuedu.planewar.util.GameUtil;
/**
 * 
 * @author ������
 *
 */
public class Constant {
	/**
	 * ���ڿ��
	 */
	public static final int GAME_WIDTH = GameUtil.getIntProperty("GAME_WIDTH");
	/**
	 * ���ڸ߶�
	 */
	public static final int GAME_HEIGHT = GameUtil.getIntProperty("GAME_HEIGHT");
	/**
	 * ���ͼƬ·����ǰ׺
	 */
	public static final String IMAGE_PATH = GameUtil.getProperty("IMAGE_PATH");
}
