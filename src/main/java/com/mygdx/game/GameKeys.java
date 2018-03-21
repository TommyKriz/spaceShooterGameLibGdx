package com.mygdx.game;

/**
 * Created by Gabriela Ferenczy on 23.11.17.
 */

public class GameKeys {

	private static final int NUM_KEYS = 13;

	private static boolean keys[] = new boolean[NUM_KEYS];
	private static boolean pKeys[] = new boolean[NUM_KEYS];

	// Player01
	public static final int UP = 0;
	public static final int LEFT = 1;
	public static final int RIGHT = 2;
	public static final int DOWN = 3;
	public static final int SPACE = 4;

	// Player02
	public static final int UP_2 = 5;
	public static final int LEFT_2 = 6;
	public static final int RIGHT_2 = 7;
	public static final int DOWN_2 = 8;
	public static final int SPACE_2 = 9;

	// Keys for visual
	public static final int TEXTURE = 10;
	public static final int DEBUG = 11;

	public static void update() {
		for (int i = 0; i < NUM_KEYS; i++) {
			pKeys[i] = keys[i];
		}
	}

	public static boolean getKey(int k) {
		return keys[k];
	}

	public static void setKey(int k, boolean b) {
		keys[k] = b;
	}

	public static boolean isPressed(int k) {
		return keys[k];
	}

	public static boolean isDown(int k) {
		return keys[k] && !pKeys[k];
	}
}
