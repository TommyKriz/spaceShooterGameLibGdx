package com.mygdx.messages;

/**
 * Created by Gabriela Ferenczy on 22.01.18.
 */

import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.utils.Pool.Poolable;

/** A Telegram is the container of a message.*/

public class Telegram implements Comparable<Telegram>, Poolable {

    /** The agent that sent this telegram */
    public Telegraph sender;

    /** The agent that is to receive this telegram */
    public Telegraph receiver;

    /** The message type. */
    public int message;

    /** Messages can be dispatched immediately or delayed for a specified amount of time. If a delay is necessary, this field is
     * stamped with the time the message should be dispatched. */
    private float timestamp;

    /** Any additional information that may accompany the message */
    public Object extraInfo;

    /** Creates an empty {@code Telegram}. */
    public Telegram () {
    }

    /** Returns the time stamp of this telegram. */
    public float getTimestamp () {
        return timestamp;
    }

    /** Sets the time stamp of this telegram. */
    public void setTimestamp (float timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public void reset () {
        this.sender = null;
        this.receiver = null;
        this.message = 0;
        this.extraInfo = null;
        this.timestamp = 0;
    }

    @Override
    public int compareTo (Telegram other) {
        if (this.equals(other)) return 0;
        return (this.timestamp - other.timestamp < 0) ? -1 : 1;
    }

    @Override
    public int hashCode () {
        final int prime = 31;
        int result = 1;
        result = prime * result + message;
        result = prime * result + ((receiver == null) ? 0 : receiver.hashCode());
        result = prime * result + ((sender == null) ? 0 : sender.hashCode());
        result = prime * result + Float.floatToIntBits(timestamp);
        return result;
    }

    @Override
    public boolean equals (Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Telegram other = (Telegram)obj;
        if (message != other.message) return false;
        if (Float.floatToIntBits(timestamp) != Float.floatToIntBits(other.timestamp)) return false;
        if (sender == null) {
            if (other.sender != null) return false;
        } else if (!sender.equals(other.sender)) return false;
        if (receiver == null) {
            if (other.receiver != null) return false;
        } else if (!receiver.equals(other.receiver)) return false;
        return true;
    }

}
