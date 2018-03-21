package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

/**
 * Made by Tommy to start the game without the Android Studio Run Project
 * Option.
 * 
 * @author Tommy
 *
 */
public class Main {

	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "First Try at making a game.";
		cfg.width = 800;
		cfg.height = 600;

		new LwjglApplication(new MyGdxGame(), cfg);
	}

}
