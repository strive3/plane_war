package com.neuedu.planewar.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import com.neuedu.planewar.client.PlaneWarClient;
import com.neuedu.planewar.constant.Constant;
import com.neuedu.planewar.util.ImageUtil;

/**
 * 创建飞机的实体类
 * 
 * @author 杜晓鹏
 *
 */
public class EnemPlane extends Plane {
	private int type;
	private Random random = new Random();
	public int startblood;

	public EnemPlane() {
		this.good = false;
	}

	/**
	 * 生成随机位置，随机类型的飞机
	 * 
	 * @param pwc 与pwc建立联系
	 */
	public EnemPlane(PlaneWarClient pwc) {
		this();
		this.pwc = pwc;
		this.type = random.nextInt(2) + 1;
		this.x = random.nextInt(Constant.GAME_WIDTH) - 200;
		this.y = random.nextInt(Constant.GAME_HEIGHT / 2);
		this.img = getImageByType();
		this.speed = getSpeedByType();
		this.blood = getBloodAndScoreByType();

		this.width = img.getWidth(null);
		this.height = img.getHeight(null);
	}

	/**
	 * 生成敌方飞机
	 * 
	 * @param pwc     与pwc建立联系
	 * @param x       横坐标
	 * @param y       纵坐标
	 * @param type    飞机的类型
	 * @param imgName 传入的飞机对应的map集合中的key
	 */
	public EnemPlane(PlaneWarClient pwc, int x, int y, int type) {
		this();
		this.pwc = pwc;
		this.type = type;
		this.x = x;
		this.y = y;
		this.img = getImageByType();
		this.speed = getSpeedByType();
		this.blood = getBloodAndScoreByType();

		this.width = img.getWidth(null);
		this.height = img.getHeight(null);
	}

	/**
	 * 根据类型初始化速度
	 * 
	 * @return
	 */
	private int getSpeedByType() {
		return 1 + 2 * type;
	}

	/**
	 * 根据类型初始化血量 分数
	 * 
	 * @return blood
	 */
	private int getBloodAndScoreByType() {
		switch (type) {
		case 1:
			this.blood = 160;
			this.startblood = 160;
			this.score = 200;
			break;
		case 2:
			this.blood = 400;
			this.startblood = 400;
			this.score = 500;
			break;
		case 3:
			this.blood = 20000;
			this.startblood = 20000;
			this.score = 10000;
			break;

		default:
			break;
		}
		return blood;
	}

	/**
	 * 根据类型初始化img
	 */
	private Image getImageByType() {
		Image image = null;
		switch (type) {
		case 1:
			image = ImageUtil.get("enemPlane01");
			break;
		case 2:
			image = ImageUtil.get("enemPlane02");
			break;
		case 3:
			image = ImageUtil.get("boss");
			break;

		default:
			break;
		}
		return image;
	}

	@Override
	public void draw(Graphics g) {
		if (!live) {
			// 当敌机的live为false时 ， 我方飞机的得分 加上赋值给敌方飞机的分数
			pwc.myPlane.setScore(pwc.myPlane.getScore() + this.score);
			pwc.enemPlanes.remove(this);
			return;
		}
		g.drawImage(img, x, y, null);
		if (type < 3) {
			g.drawImage(ImageUtil.get("enemyhp01"), x, y + this.height,
					(int) (((double) 90 / this.startblood) * this.blood), 10, null);
		}
		if (type == 3) {
			g.drawImage(ImageUtil.get("bosshp01"), 150, 62, 400, 15, null);
			g.drawImage(ImageUtil.get("hp02"), 150, 55, (int) (((double) 400 / this.startblood) * this.blood), 30,
					null);
		}
		move(); // 在画飞机的时候调用move（）方法
	}

	/**
	 * 根据他们的类型，move的轨迹不同，并且发子弹的概率不同
	 */
	@Override
	public void move() {
		int i = random.nextInt(1000) + 1;
		switch (type) {
		case 1:
			move1();
			if (i >= 993)
				fire();
			break;
		case 2:
			move2();
			if (i > 990)
				fire();
			break;
		case 3:
			if (i > 800)
				fire();
			move3();
			break;

		default:
			break;
		}
	}

	private void move1() {
		if (left) {
			x += speed;
		} else {
			x -= speed;
		}
		if (x >= Constant.GAME_WIDTH - ImageUtil.get("enemPlane01").getWidth(null)) {
			left = false;
		}
		if (x <= 10) {
			left = true;
		}
	}

	private void move3() {
		if (left) {
			x += speed;
		} else {
			x -= speed;
		}
		if (x >= Constant.GAME_WIDTH - ImageUtil.get("boss").getWidth(null)) {
			left = false;
		}
		if (x <= 10) {
			left = true;
		}
	}

	private void move2() {
		if (down) {
			x += speed;
			y += speed;
		} else {
			x -= speed;
			y -= speed;
		}
		if (x >= Constant.GAME_WIDTH - ImageUtil.get("enemPlane01").getWidth(null)) {
			down = false;
		}
		if (x <= 10) {
			down = true;
		}
	}

	/**
	 * 概率发子弹
	 */
	public void fire() {
		Missile missile = new Missile(x + this.width / 2, y + this.height / 2, type, pwc, false);
		pwc.missiles.add(missile);
	}

}
