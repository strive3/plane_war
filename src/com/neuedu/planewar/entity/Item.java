package com.neuedu.planewar.entity;

import java.awt.Image;
import java.util.Random;

import com.neuedu.planewar.client.PlaneWarClient;
import com.neuedu.planewar.constant.Constant;
import com.neuedu.planewar.interfaces.PlaneWarObject;
import com.neuedu.planewar.util.ImageUtil;

/**
 * Ŀǰ�����ֵ��� һ��Ϊheart һ�ֱ�bomb �Ե�heart��ʱ��ǰ����������1���Ե�bomb��ʱ�� �����зɻ���ը
 * 
 * @author ������
 *
 */
public class Item extends PlaneWarObject {
	private int type;
	private double theta;
	private Random random = new Random();

	public Item() {

	}

	public Item(PlaneWarClient pwc, int x, int y, int type) {
		this.pwc = pwc;
		this.y = y;
		this.x = x;
		this.speed = 10;
		this.type = type;
		this.img = getImgByType(type);
		this.theta = random.nextDouble() * 2 * Math.PI;

		this.width = img.getWidth(null);
		this.height = img.getHeight(null);
	}

	/**
	 * ͨ�����������ƴ����ͼƬ
	 * 
	 * @param type
	 * @return
	 */
	private Image getImgByType(int type) {
		Image image = null;
		switch (type) {
		case 1:
			image = ImageUtil.get("heart");
			break;
		case 2:
			image = ImageUtil.get("bomb");
			break;

		default:
			break;
		}
		return image;
	}

	/**
	 * ���ߵ��˶��켣
	 */
	@Override
	public void move() {
		x = (int) (x + speed * Math.cos(theta));
		y = (int) (y + speed * Math.sin(theta));

		// ���ײ���߽磬�����ȥ
		if (x >= Constant.GAME_WIDTH - this.width ) {
			theta = Math.PI - theta;
			x -= 10;
		}
		if (x <= 0) {
			theta = Math.PI - theta;
			x += 10;
		}
		if (y <= 30) {
			theta = -theta;
			y += 10;
		}
		if (y >= Constant.GAME_HEIGHT - this.height) {
			theta = -theta;
			y -= 10;
		}
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
