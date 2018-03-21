package com.mygdx.messages;

import com.badlogic.gdx.ai.msg.MessageDispatcher;

/**
 * Created by Gabriela Ferenczy on 22.01.18.
 */

public class MessageManager extends MessageDispatcher {

	private static final MessageManager instance = new MessageManager();

	/** Don't let anyone else instantiate this class */
	private MessageManager() {
	}

	/** Returns the singleton instance of the message dispatcher. */
	public static MessageManager getInstance() {
		return instance;
	}

}
