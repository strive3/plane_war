package com.neuedu.planewar.entity;

import java.awt.Graphics;
import java.awt.Image;

import com.neuedu.planewar.client.PlaneWarClient;
import com.neuedu.planewar.constant.Constant;
import com.neuedu.planewar.interfaces.PlaneWarObject;
import com.neuedu.planewar.util.ImageUtil;

/**
 * 
 * @author ∂≈œ˛≈Ù
 *
 */
public class Loading extends PlaneWarObject {

	public Loading() {

	}

	public Loading(PlaneWarClient pwc) {
		this.pwc = pwc;

		this.width = images[0].getWidth(null);
		this.height = images[0].getHeight(null);
	}

	public static Image[] images = new Image[12];
	static {
		for (int i = 0; i < 9; i++) {
			images[i] = ImageUtil.get("loading0" + (i + 1));
		}
		for (int i = 9; i < 12; i++) {
			images[i] = ImageUtil.get("loading0" + (i + 1));
		}
	}

	private int count;

	@Override
	public void draw(Graphics g) {
		if (count > 11) {
			count = 0;
		}
		g.drawImage(images[count++], Constant.GAME_WIDTH / 2 - this.width / 2, Constant.GAME_HEIGHT - 400, null);
	}

	@Override
	public void move() {

	}

}
