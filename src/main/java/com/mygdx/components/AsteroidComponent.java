package com.mygdx.components;

import com.badlogic.ashley.core.Component;

/**
 * Created by Gabriela Ferenczy on 11.01.18.
 */

public class AsteroidComponent implements Component {

	private int size; // 1, 2

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
