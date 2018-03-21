package com.mygdx.components;

import com.badlogic.ashley.core.Component;

/**
 * Created by Gabriela Ferenczy on 11.01.18.
 */

public class BulletComponent implements Component {
	private int lifeTime;

	public int getLifeTime() {
		return lifeTime;
	}

	public void setLifeTime(int lifeTime) {
		this.lifeTime = lifeTime;
	}

}
