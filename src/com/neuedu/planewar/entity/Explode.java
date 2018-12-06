package com.neuedu.planewar.entity;

import java.awt.Graphics;
import java.awt.Image;

import com.neuedu.planewar.client.PlaneWarClient;
import com.neuedu.planewar.util.ImageUtil;
/**
 * 
 * @author ������
 *
 */
public class Explode extends Plane {

	public Explode() {

	}

	/**
	 * ���̳и����x��y��pwc���г�ʼ��
	 * 
	 * @param x   ����ɻ��ĺ�����
	 * @param y   ����ɻ���������
	 * @param pwc ��pwc�ͻ��˽�����ϵ
	 */
	public Explode(int x, int y, PlaneWarClient pwc) {
		this.x = x;
		this.y = y;
		this.pwc = pwc;
		this.width = images[0].getWidth(null);
		this.height = images[0].getHeight(null);
	}

	/**
	 * ����һ��������</br>
	 * �������Ʊ�ը��ͼƬ����
	 */
	private int count01 = 0;
	private Image[] images = { ImageUtil.get("boom01"), ImageUtil.get("boom02") };
	/**
	 * ����һ���������������Ʊ�ըʱ��
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

		// ��Ϊ��ÿ50���� ͼƬ����repaintһ�Σ������������ļ�����������repaint6�κ� ɾ��
		count02++;
		if (count02 >= 6) {
			this.live = false;
			pwc.explodes.remove(this);
		}
	}
}
