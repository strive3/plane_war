package com.neuedu.planewar.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import com.neuedu.planewar.client.PlaneWarClient;
import com.neuedu.planewar.constant.Constant;
import com.neuedu.planewar.interfaces.PlaneWarObject;
import com.neuedu.planewar.util.ImageUtil;
/**
 * 
 * @author ������
 *
 */
public class Missile extends PlaneWarObject {
	private int type;// �ӵ����ڷɻ�����ʵ�����ģ�����type�ǵ��õķɻ���type

	public Missile() {
		this.speed = 80;
	}

	/**
	 * �ҷ��ɻ�1���ӵ��Ĺ�����
	 * 
	 * @param x
	 * @param y
	 * @param pwc
	 * @param good
	 */
	public Missile(int x, int y, PlaneWarClient pwc, boolean good) {
		this();
		this.x = x;
		this.y = y;
		this.pwc = pwc;
		this.img = ImageUtil.get("myMissile01");
		this.width = img.getWidth(null);
		this.height = img.getHeight(null);
		this.speed = 40;
		this.good = good;
	}

	/**
	 * ���ݵз��ɻ����������ɶ�Ӧ��ͼƬ �з��ɻ��ӵ��Ĺ�����
	 * 
	 * @param x    �ӵ��ĳ�ʼ ������
	 * @param y    �ӵ��ĳ�ʼ ������
	 * @param type �з��ɻ�������
	 * @param pwc	
	 * @param good �����ӵ��ĺû� ��������ײ���
	 */
	public Missile(int x, int y, int type, PlaneWarClient pwc, boolean good) {
		this();
		this.x = x;
		this.y = y;
		this.pwc = pwc;
		this.type = type;
		this.img = getImgByType();

		this.width = img.getWidth(null);
		this.height = img.getHeight(null);
		this.good = good;
	}

	/**
	 * �ҷ��ɻ��Ķ����ӵ��Ĺ�����
	 * 
	 * @param img  ����һ��img
	 * @param x    �ӵ��ĳ�ʼ ������
	 * @param y    �ӵ��ĳ�ʼ ������
	 * @param pwc  ��pwc�ͻ��˽�����ϵ
	 * @param good �����ӵ��ĺû� ��������ײ���
	 */
	public Missile(String img, int x, int y, PlaneWarClient pwc, boolean good) {
		this();
		this.x = x;
		this.y = y;
		this.pwc = pwc;
		this.img = ImageUtil.get(img);
		this.speed = 80;
		this.width = this.img.getWidth(null);
		this.height = this.img.getHeight(null);
		this.good = good;

	}

	/**
	 * ���ݵл���type��ȡ��Ӧ��ͼƬ
	 * 
	 * @return Image
	 */
	public Image getImgByType() {
		Image image = null;
		switch (type) {
		case 1:
			image = ImageUtil.get("enemMissile01");
			break;
		case 2:
			image = ImageUtil.get("enemMissile02");
			break;
		case 3:
			image = ImageUtil.get("enemMissile03");
			break;

		default:
			break;
		}
		return image;
	}

	@Override
	public void draw(Graphics g) {
		// ���live==false��ʱ�� �Ƴ���ǰ�Ķ��� Ȼ��return��������
		if (!live) {
			pwc.missiles.remove(this);
			return;
		}
		g.drawImage(img, x - this.width / 2, y - this.height, null);
		move();
	}

	/**
	 * ���ݷɻ����������ж��ӵ�
	 */

	@Override
	public void move() {
		switch (type) {
		case 1:
			speed = 20;
			this.y += speed;
			break;
		case 2:
			speed = 20;
			this.y += speed;
			this.x += speed / 2;
			break;
		case 3:
			speed = 20;
			this.y += speed;
			break;
		default:
			this.y -= speed;
			break;
		}
		// ������� �Ƴ��ӵ�
		outOfBounds();
	}

	/**
	 * ��Խ����ӵ� �Ӽ������Ƴ�
	 */
	public void outOfBounds() {
		int bounds = 100;
		if (x < -bounds || x > Constant.GAME_WIDTH + bounds || y < -bounds || y > Constant.GAME_HEIGHT + bounds) {
			// �����ж�����
			this.live = false;
			// �Ƴ�����
			this.pwc.missiles.remove(this);
		}
	}

	/**
	 * �ӵ��������
	 */
	public boolean hitPlane(Plane p) {
		// ��ײ��� ( �����е� intersects �ཻ)
		if (live && p.live && this.getRectangle().intersects(p.getRectangle()) && this.good != p.good) {
			p.blood -= 40;

			if (p.blood <= 0) {
				p.live = false;
				// �ɻ����� ��ը ����������ɵ���
				p.explode();
			}
			this.live = false;
			return true;
		}
		return false;
	}

	/**
	 * ��һ�����Ͻ�����ײ���
	 * 
	 * @param p
	 * @return
	 */
	public boolean hitPlane(List<EnemPlane> p) {
		// ��ײ���
		for (int i = 0; i < p.size(); i++) {
			EnemPlane enemPlane = p.get(i);
			if (hitPlane(enemPlane)) {
				// �����Ҫ�з��ɻ�ֻ�Ǳ������ը ������¼ӷ� �����score��ֵ
				/*
				 * if (enemPlane.live == false) { pwc.myPlane.setScore(pwc.myPlane.getScore() +
				 * 100); }
				 */
				return true;
			}

		}
		return false;
	}
}
