package com.mygdx.messages;

import com.badlogic.ashley.core.Entity;

/**
 * Created by Gabriela Ferenczy on 22.01.18.
 */

public class CollisionMsg {

    public static final int MSG_ID = 0x002A;

    private Entity first;
    private Entity second;

    public CollisionMsg(Entity first, Entity second) {
        this.first = first;
        this.second = second;
    }

    public Entity getFirst() {
        return this.first;
    }

    public Entity getSecond() {
        return this.second;
    }

}
