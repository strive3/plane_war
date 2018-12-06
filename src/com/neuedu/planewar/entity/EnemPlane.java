package com.neuedu.planewar.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import com.neuedu.planewar.client.PlaneWarClient;
import com.neuedu.planewar.constant.Constant;
import com.neuedu.planewar.util.ImageUtil;

/**
 * �����ɻ���ʵ����
 * 
 * @author ������
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
	 * �������λ�ã�������͵ķɻ�
	 * 
	 * @param pwc ��pwc������ϵ
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
	 * ���ɵз��ɻ�
	 * 
	 * @param pwc     ��pwc������ϵ
	 * @param x       ������
	 * @param y       ������
	 * @param type    �ɻ�������
	 * @param imgName ����ķɻ���Ӧ��map�����е�key
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
	 * �������ͳ�ʼ���ٶ�
	 * 
	 * @return
	 */
	private int getSpeedByType() {
		return 1 + 2 * type;
	}

	/**
	 * �������ͳ�ʼ��Ѫ�� ����
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
	 * �������ͳ�ʼ��img
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
			// ���л���liveΪfalseʱ �� �ҷ��ɻ��ĵ÷� ���ϸ�ֵ���з��ɻ��ķ���
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
		move(); // �ڻ��ɻ���ʱ�����move��������
	}

	/**
	 * �������ǵ����ͣ�move�Ĺ켣��ͬ�����ҷ��ӵ��ĸ��ʲ�ͬ
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
	 * ���ʷ��ӵ�
	 */
	public void fire() {
		Missile missile = new Missile(x + this.width / 2, y + this.height / 2, type, pwc, false);
		pwc.missiles.add(missile);
	}

}
