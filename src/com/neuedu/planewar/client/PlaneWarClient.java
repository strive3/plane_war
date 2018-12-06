package com.neuedu.planewar.client;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

import com.neuedu.planewar.constant.Constant;
import com.neuedu.planewar.entity.EnemPlane;
import com.neuedu.planewar.entity.Explode;
import com.neuedu.planewar.entity.Item;
import com.neuedu.planewar.entity.Loading;
import com.neuedu.planewar.entity.Missile;
import com.neuedu.planewar.entity.Plane;
import com.neuedu.planewar.entity.Scores;
import com.neuedu.planewar.util.FrameUtil;
import com.neuedu.planewar.util.ImageUtil;
import com.neuedu.planewar.util.MusicUtil;

/**
 * 
 * @author 杜晓鹏
 *
 */
public class PlaneWarClient extends FrameUtil {
	private static final long serialVersionUID = -2692752434035957763L;
	/**
	 * 创建一个我方飞机
	 */
	public Plane myPlane = new Plane(this, Constant.GAME_WIDTH / 2 - 70, Constant.GAME_HEIGHT - 200, true); // 此处的this传入的是PlaneWarClient对象
	/**
	 * 创建n个子弹
	 */
	public ArrayList<Missile> missiles = new ArrayList<>();
	/**
	 * 创建敌方飞机
	 */
	public ArrayList<EnemPlane> enemPlanes = new ArrayList<>();
	/**
	 * 爆炸
	 */
	public ArrayList<Explode> explodes = new ArrayList<>();
	/**
	 * 道具
	 */
	public ArrayList<Item> items = new ArrayList<>();
	/**
	 * 创建分数
	 */
	public Scores scores = new Scores(this, 650, 250);
	/**
	 * 加载环
	 */
	public Loading loading = new Loading(this);
	
	public static boolean start;

	@Override
	public void loadFram() {
		super.loadFram();
		// 图标
		this.setIconImage(ImageUtil.get("icon01"));
		// 使用键盘作为输入设备，需要键盘监听器
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) { // 按住键盘的时候
				myPlane.keyPressed(e); // 调用飞机的keyPressed方法
			}

			@Override
			public void keyReleased(KeyEvent e) { // 释放键盘的时候
				myPlane.keyReleased(e);
				if (KeyEvent.VK_P == e.getKeyCode()) {
					FrameUtil.pause = !FrameUtil.pause;
				}
				/*
				 * if (KeyEvent.VK_SPACE == e.getKeyCode()) { FrameUtil.pause = false; }
				 */
			}
		});
		// 鼠标监听
//		addMouseListener(l);//处理鼠标的操作事件
//      addMouseMotionListener(l);//处理鼠标的滑动事件
		/**
		 * 添加一个鼠标监听用来开始游戏
		 */
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getX() > Constant.GAME_WIDTH / 2 - 100 && e.getX() < Constant.GAME_WIDTH / 2 + 100
						&& e.getY() > Constant.GAME_HEIGHT / 2 + 100 && e.getY() < Constant.GAME_HEIGHT / 2 + 150) {
					PlaneWarClient.start = true;
					/**
					 * 启动音乐
					 */
					new MusicUtil("src/sounds/Byte.mp3", false).start();
				}
			}
		});
		/**
		 * 给敌机的集合中添加敌机
		 */
		for (int i = 0; i < 5; i++) {
			EnemPlane enemPlane = new EnemPlane(this, 100 + 70 * i, 100 + 70 * i, 1);
			enemPlanes.add(enemPlane); // 添加第一种类型的飞机
		}
		for (int i = 0; i < 5; i++) {
			EnemPlane enemPlane = new EnemPlane(this, 50 + 70 * i, 50 + 70 * i, 2);
			enemPlanes.add(enemPlane); // 添加第二种类型的飞机
		}

	}

	private int time = -6680;

	@Override
	public void paint(Graphics g) {
		// 刚开始处于菜单界面
		if (!start) {
			g.drawImage(ImageUtil.get("menu"), 0, 0, null);
			g.drawImage(ImageUtil.get("button"), Constant.GAME_WIDTH / 2 - 100, Constant.GAME_HEIGHT / 2 + 100, null);
			return;
		}
		// 背景的移动速度
		if (time < 0) {
			time += 5;
		}
		if (time >= -6480) {
			g.drawImage(ImageUtil.get("background"), 0, time, 700, 7680, null);
			// 血条
			g.drawImage(ImageUtil.get("hp01"), 5, Constant.GAME_HEIGHT - 50, null);
			g.drawImage(ImageUtil.get("hp02"), 5, Constant.GAME_HEIGHT - 50, myPlane.blood, 37, null);
			// 我方飞机的生命数
			for (int i = 1; i <= myPlane.getLife(); i++) {
				g.drawImage(ImageUtil.get("life"), 630 - (i - 1) * 50, 930, 50, 50, null);
			}
			// 画飞机 调用飞机的draw方法 调用loadFrame时 自动调用paint（）
			myPlane.draw(g);
			// 画分数
			scores.draw(g);
			/*
			 * 画子弹 通过增强for循环来将集合中的一个个子弹画出来 增强for循环 只能用于遍历，循环过程中不能添加或删除被循环容器 for (Missile
			 * missile : missiles) { missile.draw(g); }
			 */
			for (int i = 0; i < missiles.size(); i++) {
				Missile missile = missiles.get(i);
				missile.draw(g);
				missile.hitPlane(enemPlanes);
				missile.hitPlane(myPlane);
			}
			// 画爆炸效果
			for (int j = 0; j < explodes.size(); j++) {
				explodes.get(j).draw(g);
			}
			// 画道具
			for (int j = 0; j < items.size(); j++) {
				items.get(j).draw(g);
				myPlane.eatItem(items.get(j));
			}
			// 如果敌机集合中的数量少于5则随机生成一个敌机
			if (time < -20 && enemPlanes.size() < 5 && new Random().nextInt(100) > 90) {
				enemPlanes.add(new EnemPlane(this));
			}
			// 画敌机
			for (int i = 0; i < enemPlanes.size(); i++) {
				EnemPlane enemPlane = enemPlanes.get(i);
				enemPlane.draw(g);
			}

		}

		if (time < -6480) {
			g.drawImage(ImageUtil.get("bg_start"), 0, 0, null);
			loading.draw(g);

		}
		// 当time==-10时 生成boss
		if (time == -10) {
			enemPlanes.add(new EnemPlane(this, Constant.GAME_WIDTH - 192, 100, 3));
		}
		// 如果敌机的在time>-10的情况下 敌机的数量 等于0时 victory
		if (enemPlanes.size() == 0 && time > -10) {
			g.drawImage(ImageUtil.get("victory"), -57, Constant.GAME_HEIGHT / 2 - 200, 800, 250, null);
        //	FrameUtil.pause = true;
		}
		// 如果我方失败
		if (myPlane.live == false && myPlane.getLife() == 0) {
			g.drawImage(ImageUtil.get("gameover"), 50, Constant.GAME_HEIGHT / 2 - 100, 600, 120, null);
		}
	}

	public static void main(String[] args) {
		new PlaneWarClient().loadFram();

	}
}
