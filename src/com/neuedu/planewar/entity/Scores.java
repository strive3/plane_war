package com.neuedu.planewar.entity;

import java.awt.Graphics;
import java.awt.Image;

import com.neuedu.planewar.client.PlaneWarClient;
import com.neuedu.planewar.interfaces.PlaneWarObject;
import com.neuedu.planewar.util.ImageUtil;
/**
 * 
 * @author ������
 *
 */
public class Scores extends PlaneWarObject {

	/**
	 * ��ʼ������������
	 */
	public static Image[] images = new Image[10];
	static {
		for (int i = 0; i < 10; i++) {
			images[i] = ImageUtil.get("score0" + i);
		}
	}

	/**
	 * �ղεĹ�����
	 */
	public Scores() {

	}

	/**
	 * 
	 * @param pwc ��pwc������ϵ
	 * @param x   ������
	 * @param y   ������
	 */
	public Scores(PlaneWarClient pwc, int x, int y) {
		this.pwc = pwc;
		this.x = x;
		this.y = y;
	}

	@Override
	public void draw(Graphics g) {
		for (int i = 0; i < 6; i++) {
			// ȡ���ҷ��ɻ��ķ�����Ȼ�󰤸�����ÿ��λ����ȡ������ֵ��a
			int a = (pwc.myPlane.getScore() % (int) Math.pow(10, i + 1)) / (int) Math.pow(10, i);
			// ȡ������a �������е�ͼƬ���Ӧ
			g.drawImage(images[a], x - i * 30, y, 25, 32, null);
		}
	}

	/**
	 * ��Ϊ���ƶ� ���Բ���Ҫ��д
	 */
	@Override
	public void move() {

	}

}
