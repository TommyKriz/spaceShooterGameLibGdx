package com.mygdx.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.components.MovementComponent;
import com.mygdx.components.ShipComponent;
import com.mygdx.components.TransformComponent;
import com.mygdx.game.GameKeys;
import com.mygdx.game.World;
import com.mygdx.messages.CollisionMsg;
import com.mygdx.messages.MessageManager;

/**
 * Created by Gabriela Ferenczy on 23.11.17.
 */

public class ShipSystem extends IteratingSystem implements Telegraph {

	private ComponentMapper<ShipComponent> sc = ComponentMapper
			.getFor(ShipComponent.class);
	private ComponentMapper<TransformComponent> tc = ComponentMapper
			.getFor(TransformComponent.class);
	private ComponentMapper<MovementComponent> mc = ComponentMapper
			.getFor(MovementComponent.class);

	Texture bullet_texture = new Texture("bullet.png");

	public ShipSystem(Family family) {

		// family = Family.all(ShipComponent.class,
		// TransformComponent.class).get();
		super(family);
	}

	public ShipSystem(Family family, int priority) {
		super(family, priority);
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {

		TransformComponent transform = tc.get(entity);
		MovementComponent movement = mc.get(entity);
		ShipComponent ship = sc.get(entity);

		if (GameKeys.isPressed(ship.getKeys(0))) {
			// konstante Beschleunigung
			movement.setForce(new Vector2(0, 15).rotate(transform.getAngle()));
			// Starthilfe
			if (movement.getVel().len2() == 0) {
				movement.setVel(movement.getVel().mulAdd(movement.getForce(),
						deltaTime / 1000f));
			}

		} else {
			// keine Beschleunigung
			movement.setForce(new Vector2(0, 0));
		}

		// Drehgeschwindigkeit
		if (GameKeys.isPressed(ship.getKeys(3))) {
			movement.setAngVel(movement.getAngVel()
					+ (movement.getAngForce() - movement.getAngForce()
							* PhysicsSystem.FRICTION));
		} else if (GameKeys.isPressed(ship.getKeys(1))) {
			movement.setAngVel(movement.getAngVel()
					- (movement.getAngForce() - movement.getAngForce()
							* PhysicsSystem.FRICTION));
		} else {
			movement.setAngVel(movement.getAngVel() * PhysicsSystem.FRICTION
					/ 1.05f);
		}

		if (movement.getAngVel() > movement.getAngForce()) {
			movement.setAngVel(movement.getAngForce());
		} else if (movement.getAngVel() < -movement.getAngForce()) {
			movement.setAngVel(-movement.getAngForce());
		}

		if (GameKeys.isPressed(ship.getKeys(4)) && ship.getBulletCounter() <= 0) {
			World.createBullet(getEngine(), bullet_texture, transform
					.getAngle(), transform.getPos().cpy(), movement.getVel()
					.cpy());
			// Rückstoß beim Schuss
			movement.setVel(movement.getVel().add(
					new Vector2(0, 100).rotate(transform.getAngle() - 180)));
			ship.setBulletCounter(50);
		} else {
			ship.setBulletCounter(ship.getBulletCounter() - 1);
		}

	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
	}

	@Override
	public void removedFromEngine(Engine engine) {
		super.removedFromEngine(engine);
	}

	@Override
	public ImmutableArray<Entity> getEntities() {
		return super.getEntities();
	}

	@Override
	public Family getFamily() {
		return super.getFamily();
	}

	private boolean isShip(Entity entity) {
		return sc.has(entity);
	}

	private void destroyShip(Entity entity) {
		getEngine().removeEntity(entity);
	}

	@Override
	public boolean handleMessage(Telegram msg) {
		CollisionMsg data = (CollisionMsg) msg.extraInfo;

		if (isShip(data.getFirst())) {
			// destroyShip(data.getFirst());
		}
		if (isShip(data.getSecond())) {
			// destroyShip(data.getSecond());
		}

		return true;
	}

	@Override
	public void addedToEngine(Engine engine) {
		super.addedToEngine(engine);
		MessageManager.getInstance().addListener(this, CollisionMsg.MSG_ID);
	}
}
