package com.mygdx.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Color;

/**
 * Created by Gabriela Ferenczy on 14.12.17.
 */

public class CollisionComponent implements Component {

	private String name;
	private Color color;
	private float radius;
	private int group;
	private int mask;
	private boolean collision = false;

	public void setRadius(float f) {
		this.radius = f;
	}

	public float getRadius() {
		return this.radius;
	}

	public void setColor(Color c) {
		this.color = c;
	}

	public Color getColor() {
		return this.color;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setGroup(int group) {
		this.group = group;
	}

	public int getGroup() {
		return this.group;
	}

	public void setMask(int mask) {
		this.mask = mask;
	}

	public int getMask() {
		return this.mask;
	}

	public void setCollision(boolean bool) {
		this.collision = bool;
	}

	public boolean getCollision() {
		return this.collision;
	}
}
