package com.mygdx.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.mygdx.components.BulletComponent;
import com.mygdx.messages.CollisionMsg;
import com.mygdx.messages.MessageManager;

//import sun.jvm.hotspot.CommandProcessor;

/**
 * Created by Gabriela Ferenczy on 21.01.18.
 */

public class BulletSystem extends IteratingSystem implements Telegraph {

	private ComponentMapper<BulletComponent> bc = ComponentMapper
			.getFor(BulletComponent.class);

	public BulletSystem(Family family) {
		super(family);
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		BulletComponent bullet = bc.get(entity);

		if (bullet.getLifeTime() <= 0) {
			getEngine().removeEntity(entity);
		} else {
			bullet.setLifeTime(bullet.getLifeTime() - 1);
		}

	}

	private boolean isBullet(Entity entity) {
		return bc.has(entity);
	}

	@Override
	public boolean handleMessage(Telegram msg) {

		CollisionMsg data = (CollisionMsg) msg.extraInfo;

		if (isBullet(data.getFirst())) {
			destroyBullet(data.getFirst());
		}
		if (isBullet(data.getSecond())) {
			destroyBullet(data.getSecond());
		}

		return true;
	}

	private void destroyBullet(Entity entity) {
		getEngine().removeEntity(entity);
	}

	public void addedToEngine(Engine engine) {
		super.addedToEngine(engine);
		MessageManager.getInstance().addListener(this, CollisionMsg.MSG_ID);
	}
}
