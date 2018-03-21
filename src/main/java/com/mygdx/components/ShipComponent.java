package com.mygdx.components;

import com.badlogic.ashley.core.Component;

/**
 * Created by Gabriela Ferenczy on 23.11.17.
 */

public class ShipComponent implements Component {

	private int keys[] = new int[5];
	private int bulletCounter = 0;

	public void setKeys(int up, int down, int left, int right, int draw) {
		keys[0] = up;
		keys[1] = left;
		keys[2] = right;
		keys[3] = down;
		keys[4] = draw;
	}

	public int getKeys(int i) {
		return keys[i];
	}

	public int getBulletCounter() {
		return bulletCounter;
	}

	public void setBulletCounter(int bulletCounter) {
		this.bulletCounter = bulletCounter;
	}
}
