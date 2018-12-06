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
 * @author 杜晓鹏
 *
 */
public class Missile extends PlaneWarObject {
	private int type;// 子弹是在飞机里面实例化的，所以type是调用的飞机的type

	public Missile() {
		this.speed = 80;
	}

	/**
	 * 我方飞机1号子弹的构造器
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
	 * 根据敌方飞机的类型生成对应的图片 敌方飞机子弹的构造器
	 * 
	 * @param x    子弹的初始 横坐标
	 * @param y    子弹的初始 纵坐标
	 * @param type 敌方飞机的类型
	 * @param pwc	
	 * @param good 传入子弹的好坏 用来做碰撞检测
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
	 * 我方飞机的二号子弹的构造器
	 * 
	 * @param img  穿入一个img
	 * @param x    子弹的初始 横坐标
	 * @param y    子弹的初始 纵坐标
	 * @param pwc  与pwc客户端建立联系
	 * @param good 传入子弹的好坏 用来做碰撞检测
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
	 * 根据敌机的type获取相应的图片
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
		// 如果live==false的时候 移除当前的对象 然后return跳出方法
		if (!live) {
			pwc.missiles.remove(this);
			return;
		}
		g.drawImage(img, x - this.width / 2, y - this.height, null);
		move();
	}

	/**
	 * 根据飞机的类型来判断子弹
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
		// 如果出界 移除子弹
		outOfBounds();
	}

	/**
	 * 将越界的子弹 从集合中移除
	 */
	public void outOfBounds() {
		int bounds = 100;
		if (x < -bounds || x > Constant.GAME_WIDTH + bounds || y < -bounds || y > Constant.GAME_HEIGHT + bounds) {
			// 出界判断死亡
			this.live = false;
			// 移除对象
			this.pwc.missiles.remove(this);
		}
	}

	/**
	 * 子弹击打敌人
	 */
	public boolean hitPlane(Plane p) {
		// 碰撞检测 ( 矩形中的 intersects 相交)
		if (live && p.live && this.getRectangle().intersects(p.getRectangle()) && this.good != p.good) {
			p.blood -= 40;

			if (p.blood <= 0) {
				p.live = false;
				// 飞机死亡 爆炸 并且随机生成道具
				p.explode();
			}
			this.live = false;
			return true;
		}
		return false;
	}

	/**
	 * 对一个集合进行碰撞检测
	 * 
	 * @param p
	 * @return
	 */
	public boolean hitPlane(List<EnemPlane> p) {
		// 碰撞检测
		for (int i = 0; i < p.size(); i++) {
			EnemPlane enemPlane = p.get(i);
			if (hitPlane(enemPlane)) {
				// 如果需要敌方飞机只是被射击爆炸 的情况下加分 在这给score赋值
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
