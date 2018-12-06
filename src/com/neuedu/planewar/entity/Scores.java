package com.neuedu.planewar.entity;

import java.awt.Graphics;
import java.awt.Image;

import com.neuedu.planewar.client.PlaneWarClient;
import com.neuedu.planewar.interfaces.PlaneWarObject;
import com.neuedu.planewar.util.ImageUtil;
/**
 * 
 * @author 杜晓鹏
 *
 */
public class Scores extends PlaneWarObject {

	/**
	 * 初始化分数的数组
	 */
	public static Image[] images = new Image[10];
	static {
		for (int i = 0; i < 10; i++) {
			images[i] = ImageUtil.get("score0" + i);
		}
	}

	/**
	 * 空参的构造器
	 */
	public Scores() {

	}

	/**
	 * 
	 * @param pwc 与pwc建立联系
	 * @param x   横坐标
	 * @param y   纵坐标
	 */
	public Scores(PlaneWarClient pwc, int x, int y) {
		this.pwc = pwc;
		this.x = x;
		this.y = y;
	}

	@Override
	public void draw(Graphics g) {
		for (int i = 0; i < 6; i++) {
			// 取到我方飞机的分数，然后挨个将其每个位的数取出来赋值给a
			int a = (pwc.myPlane.getScore() % (int) Math.pow(10, i + 1)) / (int) Math.pow(10, i);
			// 取出来的a 与数组中的图片相对应
			g.drawImage(images[a], x - i * 30, y, 25, 32, null);
		}
	}

	/**
	 * 因为不移动 所以不需要重写
	 */
	@Override
	public void move() {

	}

}
