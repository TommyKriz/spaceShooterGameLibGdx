package com.mygdx.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.components.AsteroidComponent;
import com.mygdx.components.MovementComponent;
import com.mygdx.components.TransformComponent;
import com.mygdx.game.World;
import com.mygdx.messages.CollisionMsg;
import com.mygdx.messages.MessageManager;

/**
 * Created by Gabriela Ferenczy on 21.01.18.
 */

public class AsteroidSystem extends IteratingSystem implements Telegraph {

	private ComponentMapper<MovementComponent> mc = ComponentMapper
			.getFor(MovementComponent.class);
	private ComponentMapper<TransformComponent> tc = ComponentMapper
			.getFor(TransformComponent.class);
	private ComponentMapper<AsteroidComponent> ac = ComponentMapper
			.getFor(AsteroidComponent.class);

	Texture asteroid_small_texture = new Texture("asteroid_small.png");

	public AsteroidSystem(Family family) {
		super(family);
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {

	}

	private void destroyAsteroid(Entity entity) {

		AsteroidComponent asteroid = ac.get(entity);
		TransformComponent transform = tc.get(entity);
		MovementComponent movement = mc.get(entity);

		if (asteroid.getSize() == 2) {

			World.createSmallAsteroid(getEngine(), asteroid_small_texture, 0,
					transform.getPos().cpy(), movement.getVel().cpy());
			World.createSmallAsteroid(getEngine(), asteroid_small_texture, 90,
					transform.getPos().cpy(), movement.getVel().cpy());
			World.createSmallAsteroid(getEngine(), asteroid_small_texture, 180,
					transform.getPos().cpy(), movement.getVel().cpy());
			World.createSmallAsteroid(getEngine(), asteroid_small_texture, 270,
					transform.getPos().cpy(), movement.getVel().cpy());

		}

		getEngine().removeEntity(entity);

	}

	private boolean isAsteroid(Entity entity) {
		return ac.has(entity);
	}

	@Override
	public boolean handleMessage(Telegram msg) {
		CollisionMsg data = (CollisionMsg) msg.extraInfo;

		if (isAsteroid(data.getFirst())) {
			destroyAsteroid(data.getFirst());
		}
		if (isAsteroid(data.getSecond())) {
			destroyAsteroid(data.getSecond());
		}

		return true;
	}

	public void addedToEngine(Engine engine) {
		super.addedToEngine(engine);
		MessageManager.getInstance().addListener(this, CollisionMsg.MSG_ID);
	}
}
