package com.neuedu.planewar.util;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.neuedu.planewar.constant.Constant;

/**
 * ���ɴ��ڵĸ���
 * 
 * @author ������
 *
 */
public class FrameUtil extends Frame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5006625865658723521L;
	public static boolean pause;

	// �O�ô���
	public void loadFram() {
		// �O�ô�С
		this.setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
		// �O��λ��
		this.setLocation(100, 100);
		// �O�ÿ�ҕ��
		this.setVisible(true);
		// �O�����}
		this.setTitle("�ɻ���ս");
		// �O���Ƿ���P�]
		this.addWindowListener(new WindowAdapter() { // �����Ȳ��
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		// �O�ÿɲ��������
		this.setResizable(false);
		// �O��λ�þ���
		this.setLocationRelativeTo(null);
		new FrameThread().start();

	}

	// ʹ�öྀ��׌��Ƭ ������
	private class FrameThread extends Thread {
		@Override
		public synchronized void run() {
			while (true) {
				repaint();
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// ��Q�DƬ�W�q���}����˫���巽����Q�W�q���}
	Image backImg = null;

	@Override
	public void update(Graphics g) {
		if (pause) {
			g.drawImage(ImageUtil.get("pause"), Constant.GAME_WIDTH / 2 - 75, Constant.GAME_HEIGHT / 2 - 75, 130, 130,
					null);
			return;
		}
		if (backImg == null) {
			// ���̓�M�DƬ�����ڣ�����һ���ʹ���һ�Ӵ�С�ĈDƬ
			backImg = createImage(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
		}
		// �@ȡ̓�M�DƬ�Ļ���
		Graphics backg = backImg.getGraphics();
		Color c = backg.getColor();
		backg.setColor(Color.WHITE);
		backg.fillRect(0, 0, Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
		backg.setColor(c);
		// �{��̓�M�DƬ��paint()����
		paint(backg);
		g.drawImage(backImg, 0, 0, null);

	}
}
