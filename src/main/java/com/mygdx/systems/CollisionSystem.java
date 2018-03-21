package com.mygdx.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.mygdx.components.CollisionComponent;
import com.mygdx.components.TransformComponent;
import com.mygdx.messages.CollisionMsg;
import com.mygdx.messages.CollisionPhysicsMsg;
import com.mygdx.messages.MessageManager;

/**
 * Created by Gabriela Ferenczy on 14.12.17.
 */

public class CollisionSystem extends IteratingSystem {

	private ComponentMapper<TransformComponent> tc = ComponentMapper
			.getFor(TransformComponent.class);
	private ComponentMapper<CollisionComponent> cc = ComponentMapper
			.getFor(CollisionComponent.class);

	int width = Gdx.graphics.getWidth();
	int height = Gdx.graphics.getHeight();
	Engine engine;

	private ImmutableArray<Entity> entities;

	// private Family family = Family.all(TransformComponent.class,
	// CollisionComponent.class).get();

	public CollisionSystem(Family family) {
		super(family);
	}

	public CollisionSystem(Family family, int priority) {
		super(family, priority);
	}

	public void update(float deltaTime) {

		for (int i = 0; i < entities.size(); i++) {
			Entity entity1 = entities.get(i);
			CollisionComponent c = cc.get(entity1);
			for (int j = i + 1; j < entities.size(); j++) {
				Entity entity2 = entities.get(j);
				CollisionComponent c2 = cc.get(entity2);
				// when entities colliding
				if (testEntities(entity1, entity2)) {
					if (!c.getCollision() || !c2.getCollision()) {
						MessageManager.getInstance().dispatchMessage(null,
								CollisionPhysicsMsg.MSG_ID,
								new CollisionPhysicsMsg(entity1, entity2));
						MessageManager.getInstance().dispatchMessage(null,
								CollisionMsg.MSG_ID,
								new CollisionMsg(entity1, entity2));
						printCollisionInfo(entity1, entity2);
					}
				} else {
					// collision physics
					if (testDistance(entity1, entity2, c.getRadius(),
							c2.getRadius())) {
						MessageManager.getInstance().dispatchMessage(null,
								CollisionPhysicsMsg.MSG_ID,
								new CollisionPhysicsMsg(entity1, entity2));
					}
				}

			}
		}
		// super.update(deltaTime);
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
	}

	@Override
	public void addedToEngine(Engine engine) {
		this.engine = engine;
		entities = engine.getEntities();
	}

	private void printCollisionInfo(Entity entity1, Entity entity2) {
		CollisionComponent c = cc.get(entity1);
		CollisionComponent c2 = cc.get(entity2);
		System.out.println("Collision detected between " + c.getName()
				+ " and " + c2.getName());
	}

	private boolean testEntities(Entity entity1, Entity entity2) {

		CollisionComponent c = cc.get(entity1);
		CollisionComponent c2 = cc.get(entity2);

		// Check whether entities can collide
		if (((c.getGroup() & c2.getMask()) != 0)
				&& ((c2.getGroup() & c.getMask()) != 0)) {

			return testDistance(entity1, entity2, c.getRadius(), c2.getRadius());

		}

		return false;
	}

	private boolean testDistance(Entity entity1, Entity entity2, float r1,
			float r2) {

		TransformComponent pos = tc.get(entity1);
		TransformComponent pos2 = tc.get(entity2);

		float xDistPow2 = ((pos.getPos().x - pos2.getPos().x) * (pos.getPos().x - pos2
				.getPos().x));
		float yDistPow2 = ((pos.getPos().y - pos2.getPos().y) * (pos.getPos().y - pos2
				.getPos().y));
		float distance = r1 + r2;

		// if distance between the midpoints of the entities is smaller
		// than the sum of collision circle's radii
		if (distance >= Math.sqrt(xDistPow2 + yDistPow2)) {
			return true;
		} else {
			return false;
		}
	}
}
