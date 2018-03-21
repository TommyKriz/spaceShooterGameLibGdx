package com.mygdx.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Gabriela Ferenczy on 25.11.17.
 */

public class VisualComponent implements Component {

	float[] vertices;
	private Sprite img;

	public Sprite getImg() {
		return img;
	}

	public void setImg(Texture img) {
		this.img = new Sprite(img);
	}

	public void setVertices(float[] vertices) {
		this.vertices = new float[vertices.length];
		for (int i = 0; i < vertices.length; i++) {
			this.vertices[i] = vertices[i];
		}

	}

	public float[] getVertices() {
		return vertices;
	}

}
