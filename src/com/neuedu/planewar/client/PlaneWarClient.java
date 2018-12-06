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
 * @author ������
 *
 */
public class PlaneWarClient extends FrameUtil {
	private static final long serialVersionUID = -2692752434035957763L;
	/**
	 * ����һ���ҷ��ɻ�
	 */
	public Plane myPlane = new Plane(this, Constant.GAME_WIDTH / 2 - 70, Constant.GAME_HEIGHT - 200, true); // �˴���this�������PlaneWarClient����
	/**
	 * ����n���ӵ�
	 */
	public ArrayList<Missile> missiles = new ArrayList<>();
	/**
	 * �����з��ɻ�
	 */
	public ArrayList<EnemPlane> enemPlanes = new ArrayList<>();
	/**
	 * ��ը
	 */
	public ArrayList<Explode> explodes = new ArrayList<>();
	/**
	 * ����
	 */
	public ArrayList<Item> items = new ArrayList<>();
	/**
	 * ��������
	 */
	public Scores scores = new Scores(this, 650, 250);
	/**
	 * ���ػ�
	 */
	public Loading loading = new Loading(this);
	
	public static boolean start;

	@Override
	public void loadFram() {
		super.loadFram();
		// ͼ��
		this.setIconImage(ImageUtil.get("icon01"));
		// ʹ�ü�����Ϊ�����豸����Ҫ���̼�����
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) { // ��ס���̵�ʱ��
				myPlane.keyPressed(e); // ���÷ɻ���keyPressed����
			}

			@Override
			public void keyReleased(KeyEvent e) { // �ͷż��̵�ʱ��
				myPlane.keyReleased(e);
				if (KeyEvent.VK_P == e.getKeyCode()) {
					FrameUtil.pause = !FrameUtil.pause;
				}
				/*
				 * if (KeyEvent.VK_SPACE == e.getKeyCode()) { FrameUtil.pause = false; }
				 */
			}
		});
		// ������
//		addMouseListener(l);//�������Ĳ����¼�
//      addMouseMotionListener(l);//�������Ļ����¼�
		/**
		 * ���һ��������������ʼ��Ϸ
		 */
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getX() > Constant.GAME_WIDTH / 2 - 100 && e.getX() < Constant.GAME_WIDTH / 2 + 100
						&& e.getY() > Constant.GAME_HEIGHT / 2 + 100 && e.getY() < Constant.GAME_HEIGHT / 2 + 150) {
					PlaneWarClient.start = true;
					/**
					 * ��������
					 */
					new MusicUtil("src/sounds/Byte.mp3", false).start();
				}
			}
		});
		/**
		 * ���л��ļ�������ӵл�
		 */
		for (int i = 0; i < 5; i++) {
			EnemPlane enemPlane = new EnemPlane(this, 100 + 70 * i, 100 + 70 * i, 1);
			enemPlanes.add(enemPlane); // ��ӵ�һ�����͵ķɻ�
		}
		for (int i = 0; i < 5; i++) {
			EnemPlane enemPlane = new EnemPlane(this, 50 + 70 * i, 50 + 70 * i, 2);
			enemPlanes.add(enemPlane); // ��ӵڶ������͵ķɻ�
		}

	}

	private int time = -6680;

	@Override
	public void paint(Graphics g) {
		// �տ�ʼ���ڲ˵�����
		if (!start) {
			g.drawImage(ImageUtil.get("menu"), 0, 0, null);
			g.drawImage(ImageUtil.get("button"), Constant.GAME_WIDTH / 2 - 100, Constant.GAME_HEIGHT / 2 + 100, null);
			return;
		}
		// �������ƶ��ٶ�
		if (time < 0) {
			time += 5;
		}
		if (time >= -6480) {
			g.drawImage(ImageUtil.get("background"), 0, time, 700, 7680, null);
			// Ѫ��
			g.drawImage(ImageUtil.get("hp01"), 5, Constant.GAME_HEIGHT - 50, null);
			g.drawImage(ImageUtil.get("hp02"), 5, Constant.GAME_HEIGHT - 50, myPlane.blood, 37, null);
			// �ҷ��ɻ���������
			for (int i = 1; i <= myPlane.getLife(); i++) {
				g.drawImage(ImageUtil.get("life"), 630 - (i - 1) * 50, 930, 50, 50, null);
			}
			// ���ɻ� ���÷ɻ���draw���� ����loadFrameʱ �Զ�����paint����
			myPlane.draw(g);
			// ������
			scores.draw(g);
			/*
			 * ���ӵ� ͨ����ǿforѭ�����������е�һ�����ӵ������� ��ǿforѭ�� ֻ�����ڱ�����ѭ�������в�����ӻ�ɾ����ѭ������ for (Missile
			 * missile : missiles) { missile.draw(g); }
			 */
			for (int i = 0; i < missiles.size(); i++) {
				Missile missile = missiles.get(i);
				missile.draw(g);
				missile.hitPlane(enemPlanes);
				missile.hitPlane(myPlane);
			}
			// ����ըЧ��
			for (int j = 0; j < explodes.size(); j++) {
				explodes.get(j).draw(g);
			}
			// ������
			for (int j = 0; j < items.size(); j++) {
				items.get(j).draw(g);
				myPlane.eatItem(items.get(j));
			}
			// ����л������е���������5���������һ���л�
			if (time < -20 && enemPlanes.size() < 5 && new Random().nextInt(100) > 90) {
				enemPlanes.add(new EnemPlane(this));
			}
			// ���л�
			for (int i = 0; i < enemPlanes.size(); i++) {
				EnemPlane enemPlane = enemPlanes.get(i);
				enemPlane.draw(g);
			}

		}

		if (time < -6480) {
			g.drawImage(ImageUtil.get("bg_start"), 0, 0, null);
			loading.draw(g);

		}
		// ��time==-10ʱ ����boss
		if (time == -10) {
			enemPlanes.add(new EnemPlane(this, Constant.GAME_WIDTH - 192, 100, 3));
		}
		// ����л�����time>-10������� �л������� ����0ʱ victory
		if (enemPlanes.size() == 0 && time > -10) {
			g.drawImage(ImageUtil.get("victory"), -57, Constant.GAME_HEIGHT / 2 - 200, 800, 250, null);
        //	FrameUtil.pause = true;
		}
		// ����ҷ�ʧ��
		if (myPlane.live == false && myPlane.getLife() == 0) {
			g.drawImage(ImageUtil.get("gameover"), 50, Constant.GAME_HEIGHT / 2 - 100, 600, 120, null);
		}
	}

	public static void main(String[] args) {
		new PlaneWarClient().loadFram();

	}
}
