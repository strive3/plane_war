package com.neuedu.planewar.util;

import java.util.Map;

import com.neuedu.planewar.constant.Constant;

import java.awt.Image;
import java.util.HashMap;

/**
 * 初始化项目中所有的图片资源
 * 
 * @author 杜晓鹏
 *
 */
public final class ImageUtil {
	/**
	 * 装所有图片的容器
	 */
	public static Map<String, Image> imgsMap = new HashMap<>();
	/**
	 * 静态初始化imgsMap
	 */
	static {
		// 主界面的icon
		imgsMap.put("icon01", GameUtil.getImage(Constant.IMAGE_PATH + "icon/gamehall_game_loading.png"));
		// 菜单
		imgsMap.put("menu", GameUtil.getImage(Constant.IMAGE_PATH + "background/menu.png"));
		// 开始按钮
		imgsMap.put("button", GameUtil.getImage(Constant.IMAGE_PATH + "background/button.png"));
		// 开始界面
		imgsMap.put("bg_start", GameUtil.getImage(Constant.IMAGE_PATH + "background/bg1_start.png"));
		// 地图
		imgsMap.put("background", GameUtil.getImage(Constant.IMAGE_PATH + "background/background.png"));
		// 血条
		imgsMap.put("hp01", GameUtil.getImage(Constant.IMAGE_PATH + "background/hp01.png"));
		imgsMap.put("hp02", GameUtil.getImage(Constant.IMAGE_PATH + "background/hp02.png"));
		imgsMap.put("enemyhp01", GameUtil.getImage(Constant.IMAGE_PATH + "background/enemyhp01.png"));
		imgsMap.put("bosshp01", GameUtil.getImage(Constant.IMAGE_PATH + "background/bosshp01.png"));
		// 暂停
		imgsMap.put("pause", GameUtil.getImage(Constant.IMAGE_PATH + "background/pause.png"));
		// gameover
		imgsMap.put("gameover", GameUtil.getImage(Constant.IMAGE_PATH + "background/gameover.png"));
		// 生命数
		imgsMap.put("life", GameUtil.getImage(Constant.IMAGE_PATH + "background/life.png"));
		// 胜利
		imgsMap.put("victory", GameUtil.getImage(Constant.IMAGE_PATH + "background/victory.png"));
		// 我方飞机
		imgsMap.put("myPlane01", GameUtil.getImage(Constant.IMAGE_PATH + "plane/myplane/my.png"));
		// imgsMap.put("myPlane02", GameUtil.getImage(Constant.IMAGE_PATH +
		// "plane/myplane/a4-5-bai.png"));
		// 敌方飞机
		imgsMap.put("enemPlane01", GameUtil.getImage(Constant.IMAGE_PATH + "plane/enemyplane/a3-2.png"));
		imgsMap.put("enemPlane02", GameUtil.getImage(Constant.IMAGE_PATH + "plane/enemyplane/a3-3.png"));
		// boos
		imgsMap.put("boss", GameUtil.getImage(Constant.IMAGE_PATH + "plane/boss/boss.png"));
		// 我方子弹
		imgsMap.put("myMissile01", GameUtil.getImage(Constant.IMAGE_PATH + "missile/myplane/my01.png"));
		imgsMap.put("myMissile02", GameUtil.getImage(Constant.IMAGE_PATH + "missile/myplane/my02.png"));
		// 敌方子弹
		imgsMap.put("enemMissile01", GameUtil.getImage(Constant.IMAGE_PATH + "missile/enemyplane/enemy01.png"));
		imgsMap.put("enemMissile02", GameUtil.getImage(Constant.IMAGE_PATH + "missile/enemyplane/enemy02.png"));
		imgsMap.put("enemMissile03", GameUtil.getImage(Constant.IMAGE_PATH + "missile/enemyplane/enemy03.png"));
		// 爆炸
		imgsMap.put("boom01", GameUtil.getImage(Constant.IMAGE_PATH + "explode/Boom01.png"));
		imgsMap.put("boom02", GameUtil.getImage(Constant.IMAGE_PATH + "explode/Boom02.png"));
		// 道具 心
		imgsMap.put("heart", GameUtil.getImage(Constant.IMAGE_PATH + "item/heart01.png"));
		// 道具 bomb
		imgsMap.put("bomb", GameUtil.getImage(Constant.IMAGE_PATH + "item/bomb.png"));
		// 分数
		for (int i = 0; i <= 9; i++) {
			imgsMap.put("score0" + i, GameUtil.getImage(Constant.IMAGE_PATH + "scores/scores_0" + i + ".png"));

		}
		// 加载环
		for (int i = 1; i <= 9; i++) {
			imgsMap.put("loading0" + i, GameUtil.getImage(Constant.IMAGE_PATH + "loading/loading00" + i + ".png"));

		}
		for (int i = 10; i <= 12; i++) {
			imgsMap.put("loading0" + i, GameUtil.getImage(Constant.IMAGE_PATH + "loading/loading0" + i + ".png"));

		}
	}

	// 私有化空参的构造器 无法创建对象
	private ImageUtil() {

	}

	/**
	 * 获取Map集合中的image对象
	 * 
	 * @param imageName img对象的key
	 * @return img对象
	 */
	public static Image get(String imageName) {
		return ImageUtil.imgsMap.get(imageName);
	}

	/*public static void main(String[] args) {
		System.out.println(ImageUtil.get("icon01"));
	}*/

}
