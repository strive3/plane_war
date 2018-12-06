package com.neuedu.planewar.interfaces;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import com.neuedu.planewar.client.PlaneWarClient;

/**
 * �ɻ���ս��Ŀ������Ԫ�ض���ĸ���</br>
 * ������Ŀ�б�Ҫ������
 * @author ������
 *
 */
public abstract class PlaneWarObject implements Drawable,Moveable{
	/**
	 * ͼƬ����
	 */
	public Image img;
	public PlaneWarClient pwc;
	public int width;
	public int height;
	public boolean live = true;
	public boolean good;
	public int blood;
	/**
	 * x����
	 */
	public int x;
	/**
	 * y����
	 */
	public int y;
	/**
	 * Ԫ���ƶ����ٶ�
	 */
	public double speed;
	
	@Override
	public void draw(Graphics g) {
		g.drawImage(img, x, y, null);
		move();
	}
	/**
	 * ��ȡ��ǰ�������ڵľ���
	 */
	public Rectangle getRectangle() {
		return new Rectangle(x, y, width, height);
	}
}
