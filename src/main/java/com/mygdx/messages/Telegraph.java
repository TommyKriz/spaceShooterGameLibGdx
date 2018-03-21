package com.mygdx.messages;

/**
 * Created by Gabriela Ferenczy on 22.01.18.
 */

public interface Telegraph {

    /** Handles the telegram just received.
     * @param msg The telegram
     * @return {@code true} if the telegram has been successfully handled; {@code false} otherwise. */
    public boolean handleMessage (Telegram msg);

}