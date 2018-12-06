package com.neuedu.planewar.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.neuedu.planewar.client.PlaneWarClient;
import com.neuedu.planewar.constant.Constant;
import com.neuedu.planewar.interfaces.PlaneWarObject;
import com.neuedu.planewar.util.ImageUtil;

/**
 * �����ɻ���ʵ����
 * 
 * @author ������
 *
 */
public class Plane extends PlaneWarObject {
	/**
	 * �����ĸ�boolean���Ϳ��Ʒ��򿪹�
	 */
	boolean left, up, down, right;
	/**
	 * ����һ�� ����
	 */
	public int score;

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * ��PlaneWarClient������ϵ����ΪҪʹ��PlaneWarClient�е�Missile�ļ���
	 */
	public PlaneWarClient pwc;
	/**
	 * J������
	 */
	private boolean fireJ;
	/**
	 * O������
	 */
	private boolean fireO;
	/**
	 * �ɻ���������
	 */
	private int life;

	public Plane() {
		this.speed = 20;
		this.blood = 200;   
		this.life = 2;
	}

	public Plane(Image img, int x, int y) {
		this();
		this.img = img;
		this.x = x;
		this.y = y;

		this.width = img.getWidth(null);
		this.height = img.getHeight(null);
	}

	public Plane(String imgName, int x, int y) {
		this();
		this.img = ImageUtil.get(imgName);
		this.x = x;
		this.y = y;

		this.width = img.getWidth(null);
		this.height = img.getHeight(null);
	}

	/**
	 * �����ҷ��ɻ� ��ͬʱ�ܹ����ӵ�
	 * 
	 * @param x ������
	 * @param y ������
	 */
	public Plane(PlaneWarClient pwc, int x, int y, boolean good) {
		this();
		this.pwc = pwc;
		this.img = ImageUtil.get("myPlane01");
		this.x = x;
		this.y = y;
		this.good = good;
		this.width = img.getWidth(null);
		this.height = img.getHeight(null);
	}

	/*
	 * // ����һ�������� private int count; //����һ���ɻ�ͼƬ������ public static Image[] images = {
	 * ImageUtil.get("myPlane01"), ImageUtil.get("myPlane02"), };
	 */
	@Override
	public void draw(Graphics g) {
		/*
		 * if (count > 1) { count = 0; }
		 */
		// ����ɻ��������Ļ� ���ɻ�
		if (live) {
			g.drawImage(/* images[count++] */img, x, y, null);
			move(); // �ڻ��ɻ���ʱ�����move��������
		}
		if (!live && this.getLife() > 0) {
			this.setLife(this.getLife() - 1);
			this.live = true;
			this.blood = 200;
			this.x = 300;
			this.y = 800;
			return;
		}

	}

	@Override
	public void move() {
		if (fireJ) {
			fireJ();
		}
		if (fireO) {
			fireO();
		}
		if (left) {
			this.x -= speed;
		}
		if (down) {
			this.y += speed;
		}
		if (right) {
			this.x += speed;
		}
		if (up) {
			this.y -= speed;
		}
		if (x <= 0)
			x = 0;
		if (y <= 0)
			y = 0;
		if (x >= Constant.GAME_WIDTH - width) {
			x = Constant.GAME_WIDTH - width;
		}
		if (y >= Constant.GAME_HEIGHT - height) {
			y = Constant.GAME_HEIGHT - height;
		}
	}

	/**
	 * ��missile�ļ��������missile�Ķ���
	 */
	private void fireJ() {
		Missile missile = new Missile(x + this.width / 2, y + this.height / 2, pwc, true);
		pwc.missiles.add(missile);
	}

	private void fireO() {
		Missile missile = new Missile("myMissile02", x + this.width / 2, y, pwc, true);
		pwc.missiles.add(missile);
	}

	public void keyPressed(KeyEvent e) { // ��ס���̵�ʱ��
		switch (e.getKeyCode()) { // switch��Ҫ����һ������
		case KeyEvent.VK_A:
			left = true;
			break;
		case KeyEvent.VK_W:
			up = true;
			break;
		case KeyEvent.VK_D:
			right = true;
			break;
		case KeyEvent.VK_S:
			down = true;
			break;
		case KeyEvent.VK_J:
			fireJ = true;
			break;
		case KeyEvent.VK_O:
			fireO = true;
			break;
		default:
			break;
		}
	}

	public void keyReleased(KeyEvent e) { // �ͷż��̵�ʱ��
		switch (e.getKeyCode()) { // switch��Ҫ����һ������
		case KeyEvent.VK_A: // ����A��ʱ�� left = false
			left = false;
			break;
		case KeyEvent.VK_W:
			up = false;
			break;
		case KeyEvent.VK_D:
			right = false;
			break;
		case KeyEvent.VK_S:
			down = false;
			break;
		case KeyEvent.VK_J:
			fireJ = false;
			break;
		case KeyEvent.VK_O:
			fireO = false;
			break;

		default:
			break;
		}
	}

	public void mouseMoved(MouseEvent e) {
		this.x = e.getX();
		this.y = e.getY();
	}

	/**
	 * �ж��Ƿ�Ե���
	 * 
	 * @param item ����һ������
	 * @return �����Ƿ�Ե����Ե�����true
	 */
	public boolean eatItem(Item item) {
		if (live && item.live && this.getRectangle().intersects(item.getRectangle())) {
			item.live = false;
			pwc.items.remove(item);
			if (item.getType() == 1) {
				this.setLife(this.getLife() + 1);
			}
			if (item.getType() == 2) {
				for (int i = 0; i < pwc.enemPlanes.size(); i++) {
					pwc.enemPlanes.get(i).live = false;
					pwc.enemPlanes.get(i).explode();
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * life��get / set ����
	 * 
	 * @return
	 */
	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	/**
	 * ����ɻ������� ��ը������������ɵ���
	 */
	public void explode() {
		Explode explode = new Explode(this.x + this.width / 2, this.y + this.height / 2, this.pwc);
		pwc.explodes.add(explode);
		// ���ɵ���
		if (new Random().nextInt(100) > 90) {
			pwc.items.add(new Item(pwc, x, y, new Random().nextInt(2) + 1));
		}
	}

}
