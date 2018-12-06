package com.neuedu.planewar.util;

import java.util.Map;

import com.neuedu.planewar.constant.Constant;

import java.awt.Image;
import java.util.HashMap;

/**
 * ��ʼ����Ŀ�����е�ͼƬ��Դ
 * 
 * @author ������
 *
 */
public final class ImageUtil {
	/**
	 * װ����ͼƬ������
	 */
	public static Map<String, Image> imgsMap = new HashMap<>();
	/**
	 * ��̬��ʼ��imgsMap
	 */
	static {
		// �������icon
		imgsMap.put("icon01", GameUtil.getImage(Constant.IMAGE_PATH + "icon/gamehall_game_loading.png"));
		// �˵�
		imgsMap.put("menu", GameUtil.getImage(Constant.IMAGE_PATH + "background/menu.png"));
		// ��ʼ��ť
		imgsMap.put("button", GameUtil.getImage(Constant.IMAGE_PATH + "background/button.png"));
		// ��ʼ����
		imgsMap.put("bg_start", GameUtil.getImage(Constant.IMAGE_PATH + "background/bg1_start.png"));
		// ��ͼ
		imgsMap.put("background", GameUtil.getImage(Constant.IMAGE_PATH + "background/background.png"));
		// Ѫ��
		imgsMap.put("hp01", GameUtil.getImage(Constant.IMAGE_PATH + "background/hp01.png"));
		imgsMap.put("hp02", GameUtil.getImage(Constant.IMAGE_PATH + "background/hp02.png"));
		imgsMap.put("enemyhp01", GameUtil.getImage(Constant.IMAGE_PATH + "background/enemyhp01.png"));
		imgsMap.put("bosshp01", GameUtil.getImage(Constant.IMAGE_PATH + "background/bosshp01.png"));
		// ��ͣ
		imgsMap.put("pause", GameUtil.getImage(Constant.IMAGE_PATH + "background/pause.png"));
		// gameover
		imgsMap.put("gameover", GameUtil.getImage(Constant.IMAGE_PATH + "background/gameover.png"));
		// ������
		imgsMap.put("life", GameUtil.getImage(Constant.IMAGE_PATH + "background/life.png"));
		// ʤ��
		imgsMap.put("victory", GameUtil.getImage(Constant.IMAGE_PATH + "background/victory.png"));
		// �ҷ��ɻ�
		imgsMap.put("myPlane01", GameUtil.getImage(Constant.IMAGE_PATH + "plane/myplane/my.png"));
		// imgsMap.put("myPlane02", GameUtil.getImage(Constant.IMAGE_PATH +
		// "plane/myplane/a4-5-bai.png"));
		// �з��ɻ�
		imgsMap.put("enemPlane01", GameUtil.getImage(Constant.IMAGE_PATH + "plane/enemyplane/a3-2.png"));
		imgsMap.put("enemPlane02", GameUtil.getImage(Constant.IMAGE_PATH + "plane/enemyplane/a3-3.png"));
		// boos
		imgsMap.put("boss", GameUtil.getImage(Constant.IMAGE_PATH + "plane/boss/boss.png"));
		// �ҷ��ӵ�
		imgsMap.put("myMissile01", GameUtil.getImage(Constant.IMAGE_PATH + "missile/myplane/my01.png"));
		imgsMap.put("myMissile02", GameUtil.getImage(Constant.IMAGE_PATH + "missile/myplane/my02.png"));
		// �з��ӵ�
		imgsMap.put("enemMissile01", GameUtil.getImage(Constant.IMAGE_PATH + "missile/enemyplane/enemy01.png"));
		imgsMap.put("enemMissile02", GameUtil.getImage(Constant.IMAGE_PATH + "missile/enemyplane/enemy02.png"));
		imgsMap.put("enemMissile03", GameUtil.getImage(Constant.IMAGE_PATH + "missile/enemyplane/enemy03.png"));
		// ��ը
		imgsMap.put("boom01", GameUtil.getImage(Constant.IMAGE_PATH + "explode/Boom01.png"));
		imgsMap.put("boom02", GameUtil.getImage(Constant.IMAGE_PATH + "explode/Boom02.png"));
		// ���� ��
		imgsMap.put("heart", GameUtil.getImage(Constant.IMAGE_PATH + "item/heart01.png"));
		// ���� bomb
		imgsMap.put("bomb", GameUtil.getImage(Constant.IMAGE_PATH + "item/bomb.png"));
		// ����
		for (int i = 0; i <= 9; i++) {
			imgsMap.put("score0" + i, GameUtil.getImage(Constant.IMAGE_PATH + "scores/scores_0" + i + ".png"));

		}
		// ���ػ�
		for (int i = 1; i <= 9; i++) {
			imgsMap.put("loading0" + i, GameUtil.getImage(Constant.IMAGE_PATH + "loading/loading00" + i + ".png"));

		}
		for (int i = 10; i <= 12; i++) {
			imgsMap.put("loading0" + i, GameUtil.getImage(Constant.IMAGE_PATH + "loading/loading0" + i + ".png"));

		}
	}

	// ˽�л��ղεĹ����� �޷���������
	private ImageUtil() {

	}

	/**
	 * ��ȡMap�����е�image����
	 * 
	 * @param imageName img�����key
	 * @return img����
	 */
	public static Image get(String imageName) {
		return ImageUtil.imgsMap.get(imageName);
	}

	/*public static void main(String[] args) {
		System.out.println(ImageUtil.get("icon01"));
	}*/

}
