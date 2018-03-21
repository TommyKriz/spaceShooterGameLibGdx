package com.mygdx.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Gabriela Ferenczy on 25.11.17.
 */

public class MovementComponent implements Component {
    private Vector2 vel = new Vector2();
    private Vector2 force;

    private float angVel;
    private float angForce;

    private float physicsInfluence = 1.0f;

    public Vector2 getForce() {
        return force;
    }

    public void setForce(Vector2 force) {
        this.force = force;
    }

    public void setVel(Vector2 vel) {
        this.vel = vel;
    }

    public Vector2 getVel() {
        return vel;
    }

    public void setAngVel(float angVel) {
        this.angVel = angVel;
    }

    public float getAngVel() {
        return angVel;
    }

    public float getAngForce() {
        return angForce;
    }

    public void setAngAcc(float angForce) {
        this.angForce = angForce;
    }


    public float getPhysicsInfluence() {
        return physicsInfluence;
    }

    public void setPhysicsInfluence(float physicsInfluence) {
        this.physicsInfluence = physicsInfluence;
    }
}
