package com.mygdx.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.components.MovementComponent;
import com.mygdx.components.TransformComponent;
import com.mygdx.messages.CollisionPhysicsMsg;
import com.mygdx.messages.MessageManager;

/**
 * Created by Gabriela Ferenczy on 25.11.17.
 */

public class PhysicsSystem extends IteratingSystem implements Telegraph {

	private ComponentMapper<MovementComponent> mc = ComponentMapper
			.getFor(MovementComponent.class);
	private ComponentMapper<TransformComponent> tc = ComponentMapper
			.getFor(TransformComponent.class);

	private float worldWidth = Gdx.graphics.getWidth() / 2;
	private float worldHeight = Gdx.graphics.getHeight() / 2;

	public static final float FRICTION = 0.97f;

	public PhysicsSystem(Family family) {
		super(family);
	}

	public PhysicsSystem(Family family, float worldWidth, float worldHeight) {
		super(family);
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		MovementComponent movement = mc.get(entity);
		TransformComponent transform = tc.get(entity);

		if (movement.getPhysicsInfluence() > 0.0f) {
			// Calculate damping factor
			float dampingFactor = (movement.getVel().len2() * movement.getVel()
					.len2()) / 100000000.0f;
			// clamp
			if (dampingFactor > 1.0f) {
				dampingFactor = 1.0f;
			}

			float damp = (1.0f - dampingFactor)
					* movement.getPhysicsInfluence();

			// set damping (dependent on speed, as faster, as stronger)
			movement.setVel(movement.getVel().mulAdd(movement.getForce(), damp));
			// set friction (constant value, for slowing down when no thrust)
			movement.setVel(movement.getVel().scl(FRICTION));

		}

		transform.setAngle(transform.getAngle() + movement.getAngVel()
				* deltaTime);
		transform.setPos(transform.getPos()
				.mulAdd(movement.getVel(), deltaTime));

		// check if outside of screen
		if (transform.getPos().x < -worldHeight) {
			transform.getPos().x = worldHeight;
			// System.out.println("left edge reached");
		} else if (transform.getPos().x > worldHeight) {
			transform.getPos().x = -worldHeight;
			// System.out.println("right edge reached");
		} else if (transform.getPos().y < -worldWidth) {
			transform.getPos().y = worldWidth;
			// System.out.println("bottom edge reached");
		} else if (transform.getPos().y > worldWidth) {
			transform.getPos().y = -worldWidth;
			// System.out.println("top edge reached");
		}

	}

	@Override
	public boolean handleMessage(Telegram msg) {
		CollisionPhysicsMsg data = (CollisionPhysicsMsg) msg.extraInfo;

		MovementComponent mc01 = data.getFirst().getComponent(
				MovementComponent.class);
		MovementComponent mc02 = data.getSecond().getComponent(
				MovementComponent.class);
		TransformComponent tc01 = data.getFirst().getComponent(
				TransformComponent.class);
		TransformComponent tc02 = data.getSecond().getComponent(
				TransformComponent.class);

		Vector2 pos = new Vector2(tc01.getPos());
		Vector2 pos2 = new Vector2(tc02.getPos());

		Vector2 vec = new Vector2(mc01.getVel());
		Vector2 vec2 = new Vector2(mc02.getVel());

		mc01.setVel(vec2);
		mc02.setVel(vec);

		tc01.setPos(pos.scl(1.0f / FRICTION));
		tc02.setPos(pos2.scl(1.0f / FRICTION));

		return true;
	}

	public void addedToEngine(Engine engine) {
		super.addedToEngine(engine);
		MessageManager.getInstance().addListener(this,
				CollisionPhysicsMsg.MSG_ID);
	}

}
