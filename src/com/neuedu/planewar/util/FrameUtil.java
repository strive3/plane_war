package com.neuedu.planewar.util;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.neuedu.planewar.constant.Constant;

/**
 * 生成窗口的父类
 * 
 * @author 杜晓鹏
 *
 */
public class FrameUtil extends Frame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5006625865658723521L;
	public static boolean pause;

	// 設置窗口
	public void loadFram() {
		// 設置大小
		this.setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
		// 設置位置
		this.setLocation(100, 100);
		// 設置可視化
		this.setVisible(true);
		// 設置主題
		this.setTitle("飞机大战");
		// 設置是否可關閉
		this.addWindowListener(new WindowAdapter() { // 匿名內部類
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		// 設置可不可以最大化
		this.setResizable(false);
		// 設置位置居中
		this.setLocationRelativeTo(null);
		new FrameThread().start();

	}

	// 使用多線程讓照片 動起來
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

	// 解決圖片閃爍問題，用双缓冲方法解決閃爍問題
	Image backImg = null;

	@Override
	public void update(Graphics g) {
		if (pause) {
			g.drawImage(ImageUtil.get("pause"), Constant.GAME_WIDTH / 2 - 75, Constant.GAME_HEIGHT / 2 - 75, 130, 130,
					null);
			return;
		}
		if (backImg == null) {
			// 如果虛擬圖片不存在，創建一個和窗口一樣大小的圖片
			backImg = createImage(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
		}
		// 獲取虛擬圖片的画笔
		Graphics backg = backImg.getGraphics();
		Color c = backg.getColor();
		backg.setColor(Color.WHITE);
		backg.fillRect(0, 0, Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
		backg.setColor(c);
		// 調用虛擬圖片的paint()方法
		paint(backg);
		g.drawImage(backImg, 0, 0, null);

	}
}
