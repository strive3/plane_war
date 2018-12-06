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
 * 创建飞机的实体类
 * 
 * @author 杜晓鹏
 *
 */
public class Plane extends PlaneWarObject {
	/**
	 * 定义四个boolean类型控制方向开关
	 */
	boolean left, up, down, right;
	/**
	 * 定义一个 分数
	 */
	public int score;

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * 与PlaneWarClient建立联系，因为要使用PlaneWarClient中的Missile的集合
	 */
	public PlaneWarClient pwc;
	/**
	 * J键开火
	 */
	private boolean fireJ;
	/**
	 * O键开火
	 */
	private boolean fireO;
	/**
	 * 飞机的生命数
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
	 * 生成我方飞机 ，同时能够发子弹
	 * 
	 * @param x 横坐标
	 * @param y 纵坐标
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
	 * // 定义一个计数器 private int count; //定义一个飞机图片的数组 public static Image[] images = {
	 * ImageUtil.get("myPlane01"), ImageUtil.get("myPlane02"), };
	 */
	@Override
	public void draw(Graphics g) {
		/*
		 * if (count > 1) { count = 0; }
		 */
		// 如果飞机有生命的话 画飞机
		if (live) {
			g.drawImage(/* images[count++] */img, x, y, null);
			move(); // 在画飞机的时候调用move（）方法
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
	 * 给missile的集合中添加missile的对象
	 */
	private void fireJ() {
		Missile missile = new Missile(x + this.width / 2, y + this.height / 2, pwc, true);
		pwc.missiles.add(missile);
	}

	private void fireO() {
		Missile missile = new Missile("myMissile02", x + this.width / 2, y, pwc, true);
		pwc.missiles.add(missile);
	}

	public void keyPressed(KeyEvent e) { // 按住键盘的时候
		switch (e.getKeyCode()) { // switch中要传入一个变量
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

	public void keyReleased(KeyEvent e) { // 释放键盘的时候
		switch (e.getKeyCode()) { // switch中要传入一个变量
		case KeyEvent.VK_A: // 当按A的时候 left = false
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
	 * 判断是否吃道具
	 * 
	 * @param item 传入一个道具
	 * @return 返回是否吃到，吃到返回true
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
	 * life的get / set 方法
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
	 * 如果飞机死亡则 爆炸，并且随机生成道具
	 */
	public void explode() {
		Explode explode = new Explode(this.x + this.width / 2, this.y + this.height / 2, this.pwc);
		pwc.explodes.add(explode);
		// 生成道具
		if (new Random().nextInt(100) > 90) {
			pwc.items.add(new Item(pwc, x, y, new Random().nextInt(2) + 1));
		}
	}

}
