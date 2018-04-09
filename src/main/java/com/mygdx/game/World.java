package com.mygdx.game;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.components.AsteroidComponent;
import com.mygdx.components.BulletComponent;
import com.mygdx.components.CollisionComponent;
import com.mygdx.components.MovementComponent;
import com.mygdx.components.ShipComponent;
import com.mygdx.components.TransformComponent;
import com.mygdx.components.VisualComponent;

/**
 * Created by Gabriela Ferenczy on 21.01.18.
 */

public class World {

	public static Entity createShip(Texture ship_texture,
			int posX, int posY, int[] keys, Color colliderColor,
			String colliderName, int collisionMask) {

		ShipComponent shipComponent = new ShipComponent();
		TransformComponent transform = new TransformComponent();
		MovementComponent body = new MovementComponent();
		VisualComponent visual = new VisualComponent();
		CollisionComponent collider = new CollisionComponent();

		transform.setAngle(0);

		Vector2 offset = new Vector2(posX, posY);
		transform.setPos(transform.getPos().cpy().add(offset));

		body.setAngVel(0);
		body.setVel(new Vector2(0, 0));
		body.setForce(new Vector2(0, 0));
		body.setAngAcc(200.0f);

		shipComponent.setKeys(keys[0], keys[1], keys[2], keys[3], keys[4]);
		shipComponent.setBulletCounter(0);

		float[] verticesShip = { 0.0f, 25.0f, -20.0f, -20.0f, 0.0f, -14.0f,
				20.0f, -20.0f };

		visual.setVertices(verticesShip);
		visual.setImg(ship_texture);

		collider.setColor(colliderColor);
		collider.setRadius(20.0f);
		collider.setName(colliderName);
		collider.setGroup(1);
		// Collision with astroid(4) and bullet(2)
		collider.setMask(collisionMask);

		Entity player_ship = new Entity();
		player_ship.add(shipComponent);
		player_ship.add(transform);
		player_ship.add(visual);
		player_ship.add(body);
		player_ship.add(collider);
		return player_ship;

	}

	public static void createAsteroid(Engine engine, Texture asteroid_texture,
			Vector2 pos) {
		VisualComponent vc = new VisualComponent();
		MovementComponent mc = new MovementComponent();
		TransformComponent tc = new TransformComponent();
		CollisionComponent cc = new CollisionComponent();
		AsteroidComponent ac = new AsteroidComponent();// (1, new Vector2(0, 1),
														// 2);

		int x = (int) pos.x;
		int y = (int) pos.y;
		int ang = (int) (Math.random() * 360);

		cc.setColor(Color.WHITE);
		cc.setRadius(20.0f);
		cc.setGroup(4);
		// Collision with bullet(2)
		cc.setMask(2);
		cc.setName("Big Asteroid");

		tc.setAngle(ang);
		Vector2 vec2 = new Vector2(x, y);
		tc.setPos(vec2);

		mc.setForce(new Vector2(0, 20.0f).rotate(ang));
		mc.setVel(new Vector2(0, 1).add(mc.getForce()));
		mc.setPhysicsInfluence(0.0f);

		float[] verticesAsteroid = { 15.0f, 15.0f, 0.0f, 20.0f, -15.0f, 15.0f,
				-20.0f, 0.0f, -15.0f, -15.0f, 0.0f, -20.0f, 15.0f, -15.0f,
				20.0f, 0.0f // um Nullpunkt zeichnen
		};
		vc.setVertices(verticesAsteroid);
		vc.setImg(asteroid_texture);

		ac.setSize(2);

		Entity asteroid = new Entity();

		asteroid.add(ac);
		asteroid.add(vc);
		asteroid.add(tc);
		asteroid.add(mc);
		asteroid.add(cc);

		engine.addEntity(asteroid);
	}

	public static void createSmallAsteroid(Engine engine,
			Texture asteroid_texture_small, float angle, Vector2 position,
			Vector2 velocity) {
		VisualComponent vc = new VisualComponent();
		MovementComponent mc = new MovementComponent();
		TransformComponent tc = new TransformComponent();
		CollisionComponent cc = new CollisionComponent();
		AsteroidComponent ac = new AsteroidComponent();// (2, new Vector2(0, 1),
														// 1);

		cc.setColor(Color.WHITE);
		cc.setRadius(10.0f);
		cc.setGroup(4);
		// collision with and bullet(2)
		cc.setMask(2);
		cc.setName("Small Asteroid");

		tc.setAngle(angle);
		tc.setPos(position.add(new Vector2(0, 20).rotate(angle + 45.0f)));

		mc.setForce(new Vector2(0, 10).rotate(angle + 45.0f
				* (float) Math.random()));
		mc.setVel(velocity.add(mc.getForce()));
		mc.setPhysicsInfluence(0.0f);

		float[] verticesAsteroid = { 7.5f, 7.5f, 0.0f, 10.0f, -7.5f, 7.5f,
				-10.0f, 0.0f, -7.5f, -7.5f, 0.0f, -10.0f, 7.5f, -7.5f, 10.0f,
				0.0f // um Nullpunkt zeichnen
		};
		vc.setVertices(verticesAsteroid);
		vc.setImg(asteroid_texture_small);

		ac.setSize(1);

		Entity asteroid = new Entity();

		asteroid.add(ac);
		asteroid.add(vc);
		asteroid.add(tc);
		asteroid.add(mc);
		asteroid.add(cc);

		engine.addEntity(asteroid);

	}

	public static void createBullet(Engine engine, Texture bullet_texture,
			float angle, Vector2 transform, Vector2 velocity) {

		Entity bullet = new Entity();

		VisualComponent vc = new VisualComponent();
		MovementComponent mc = new MovementComponent();
		TransformComponent tc = new TransformComponent();
		BulletComponent bc = new BulletComponent();
		CollisionComponent cc = new CollisionComponent();

		mc.setVel(velocity);
		mc.setForce(new Vector2(0, 0));

		bc.setLifeTime(120);

		cc.setColor(Color.WHITE);
		cc.setRadius(5.0f);
		cc.setGroup(2);
		// collision with astroids(4) and ship(1)
		cc.setMask(5);
		cc.setName("Bullet");

		float[] bulletVertices = { 0.0f, 5.0f, -5.0f, -2.5f, 0.0f, -5.0f, 5.0f,
				-2.5f };
		vc.setVertices(bulletVertices);
		vc.setImg(bullet_texture);

		// offset
		Vector2 offset = new Vector2(0, 25f);
		offset.rotate(angle);

		tc.setAngle(angle);
		tc.setPos(transform.cpy().mulAdd(offset, 1));

		mc.setForce(new Vector2(0, 100.0f).rotate(angle));
		mc.setVel(velocity.add(mc.getForce()));
		mc.setPhysicsInfluence(0.0f);

		bullet.add(vc);
		bullet.add(mc);
		bullet.add(tc);
		bullet.add(bc);
		bullet.add(cc);

		engine.addEntity(bullet);

	}
}
