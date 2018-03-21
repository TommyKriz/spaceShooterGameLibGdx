package com.mygdx.components;

/**
 * Created by Gabriela Ferenczy on 25.11.17.
 */

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class TransformComponent implements Component {

    private Vector2 pos = new Vector2();
    private float angle = 0.0f;

    public void setPos(Vector2 pos) {
        this.pos = pos;
    }
    public void setAngle(float angle) {
        this.angle = angle;
    }

    public Vector2 getPos() {
        return pos;
    }

    public float getAngle() {
        return angle;
    }

}