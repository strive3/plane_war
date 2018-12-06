package com.neuedu.planewar.entity;

import java.awt.Graphics;
import java.awt.Image;

import com.neuedu.planewar.client.PlaneWarClient;
import com.neuedu.planewar.util.ImageUtil;
/**
 * 
 * @author 杜晓鹏
 *
 */
public class Explode extends Plane {

	public Explode() {

	}

	/**
	 * 将继承父类的x，y，pwc进行初始化
	 * 
	 * @param x   传入飞机的横坐标
	 * @param y   传入飞机的纵坐标
	 * @param pwc 与pwc客户端建立联系
	 */
	public Explode(int x, int y, PlaneWarClient pwc) {
		this.x = x;
		this.y = y;
		this.pwc = pwc;
		this.width = images[0].getWidth(null);
		this.height = images[0].getHeight(null);
	}

	/**
	 * 定义一个计数器</br>
	 * 用来控制爆炸的图片数组
	 */
	private int count01 = 0;
	private Image[] images = { ImageUtil.get("boom01"), ImageUtil.get("boom02") };
	/**
	 * 定义一个计数器用来控制爆炸时间
	 */
	private int count02 = 0;

	@Override
	public void draw(Graphics g) {
		if (!this.live) {
			return;
		}

		if (count01 > 1) {
			count01 = 0;
		}
		g.drawImage(images[count01++], x - this.width / 2, y - this.height / 2, null);

		// 因为是每50毫秒 图片重新repaint一次，所以这个定义的计数器，让它repaint6次后 删除
		count02++;
		if (count02 >= 6) {
			this.live = false;
			pwc.explodes.remove(this);
		}
	}
}
