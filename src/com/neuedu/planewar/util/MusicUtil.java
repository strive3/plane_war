package com.neuedu.planewar.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
/**
 * 
 * @author ∂≈œ˛≈Ù
 *
 */
public class MusicUtil extends Thread {
	private Player player;
	private File music;
	private boolean loop;

	public MusicUtil(String musicpath) {
		this.music = new File(musicpath);
	}

	public MusicUtil(String musicpath, boolean loop) {
		this(musicpath);
		this.loop = loop;
	}

	@Override
	public void run() {
		super.run();
		try {
			if (loop) {
				while (true) {
					play();
				}
			} else {
				play();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JavaLayerException e) {
			e.printStackTrace();
		}
	}

	private void play() throws FileNotFoundException, JavaLayerException {
		BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(music));
		player = new Player(buffer);
		player.play();
	}

}