package com.mygdx.systems;

/**
 * Created by Gabriela Ferenczy on 23.11.17.
 */
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.mygdx.game.GameKeys;

public class InputSystem extends InputAdapter {
	public boolean keyDown(int k) {
		// Player1 keys
		if (k == Keys.UP) {
			GameKeys.setKey(GameKeys.UP, true);
		}

		// delete key (not used)
		/*
		 * if(k == Keys.R) { GameKeys.setKey(GameKeys.DOWN, true); }
		 */

		if (k == Keys.LEFT) {
			GameKeys.setKey(GameKeys.LEFT, true);
		}
		if (k == Keys.RIGHT) {
			GameKeys.setKey(GameKeys.RIGHT, true);
		}

		if (k == Keys.SPACE) {
			GameKeys.setKey(GameKeys.SPACE, true);
		}

		// Player2 keys
		if (k == Keys.W) {
			GameKeys.setKey(GameKeys.UP_2, true);
		}

		/*
		 * //delete key if(k == Keys.S) { GameKeys.setKey(GameKeys.DOWN_2,
		 * true); }
		 */

		if (k == Keys.A) {
			GameKeys.setKey(GameKeys.LEFT_2, true);
		}
		if (k == Keys.D) {
			GameKeys.setKey(GameKeys.RIGHT_2, true);
		}

		if (k == Keys.C) {
			GameKeys.setKey(GameKeys.SPACE_2, true);
		}

		// Keys for visual
		if (k == Keys.NUM_1) {
			GameKeys.setKey(GameKeys.TEXTURE, true);
		}
		if (k == Keys.NUM_2) {
			GameKeys.setKey(GameKeys.DEBUG, true);
		}

		return true;
	}

	public boolean keyUp(int k) {
		// Player1
		if (k == Keys.UP) {
			GameKeys.setKey(GameKeys.UP, false);
		}
		/*
		 * //delete key if(k == Keys.R) { GameKeys.setKey(GameKeys.DOWN, false);
		 * }
		 */
		if (k == Keys.LEFT) {
			GameKeys.setKey(GameKeys.LEFT, false);
		}
		if (k == Keys.RIGHT) {
			GameKeys.setKey(GameKeys.RIGHT, false);
		}

		if (k == Keys.SPACE) {
			GameKeys.setKey(GameKeys.SPACE, false);
		}

		// Player2
		if (k == Keys.W) {
			GameKeys.setKey(GameKeys.UP_2, false);
		}
		/*
		 * //delete key if(k == Keys.S) { GameKeys.setKey(GameKeys.DOWN_2,
		 * false); }
		 */
		if (k == Keys.A) {
			GameKeys.setKey(GameKeys.LEFT_2, false);
		}
		if (k == Keys.D) {
			GameKeys.setKey(GameKeys.RIGHT_2, false);
		}

		if (k == Keys.C) {
			GameKeys.setKey(GameKeys.SPACE_2, false);
		}

		// Keys for visual
		if (k == Keys.NUM_1) {
			GameKeys.setKey(GameKeys.TEXTURE, false);
		}
		if (k == Keys.NUM_2) {
			GameKeys.setKey(GameKeys.DEBUG, false);
		}

		return true;
	}
}
