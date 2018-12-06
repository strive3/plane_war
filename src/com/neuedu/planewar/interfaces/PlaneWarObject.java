package com.neuedu.planewar.interfaces;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import com.neuedu.planewar.client.PlaneWarClient;

/**
 * 飞机大战项目中所有元素对象的父类</br>
 * 具有项目中必要的属性
 * @author 杜晓鹏
 *
 */
public abstract class PlaneWarObject implements Drawable,Moveable{
	/**
	 * 图片对象
	 */
	public Image img;
	public PlaneWarClient pwc;
	public int width;
	public int height;
	public boolean live = true;
	public boolean good;
	public int blood;
	/**
	 * x坐标
	 */
	public int x;
	/**
	 * y坐标
	 */
	public int y;
	/**
	 * 元素移动的速度
	 */
	public double speed;
	
	@Override
	public void draw(Graphics g) {
		g.drawImage(img, x, y, null);
		move();
	}
	/**
	 * 获取当前对象所在的矩形
	 */
	public Rectangle getRectangle() {
		return new Rectangle(x, y, width, height);
	}
}
