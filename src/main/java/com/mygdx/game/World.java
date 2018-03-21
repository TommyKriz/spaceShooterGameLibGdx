package com.mygdx.game;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
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

	private static Vector2 center = new Vector2(Gdx.graphics.getWidth() / 2,
			Gdx.graphics.getHeight() / 2);

	public static void createShip(Engine engine, Texture ship_texture) {

		// Player01
		ShipComponent sc_01 = new ShipComponent();
		TransformComponent tc_01 = new TransformComponent();
		MovementComponent mc_01 = new MovementComponent();
		VisualComponent vc_01 = new VisualComponent();
		CollisionComponent cc_01 = new CollisionComponent();

		// set angle
		tc_01.setAngle(0);

		// set position

		Vector2 vec2 = new Vector2(center.x, center.y);
		Vector2 offset = new Vector2(30, 0);
		tc_01.setPos(tc_01.getPos().cpy().add(offset));

		mc_01.setAngVel(0);
		mc_01.setVel(new Vector2(0, 0));
		mc_01.setForce(new Vector2(0, 0));
		mc_01.setAngAcc(200.0f);

		sc_01.setKeys(0, 1, 2, 3, 4);
		sc_01.setBulletCounter(0);

		float[] verticesShip = { 0.0f, 25.0f, -20.0f, -20.0f, 0.0f, -14.0f,
				20.0f, -20.0f };

		vc_01.setVertices(verticesShip);
		vc_01.setImg(ship_texture);

		cc_01.setColor(Color.GREEN);
		cc_01.setRadius(20.0f);
		cc_01.setName("Player 1");
		cc_01.setGroup(1);
		// Collision with astroid(4) and bullet(2)
		cc_01.setMask(3);

		Entity player01_entity = new Entity();
		player01_entity.add(sc_01);
		player01_entity.add(tc_01);
		player01_entity.add(vc_01);
		player01_entity.add(mc_01);
		player01_entity.add(cc_01);
		engine.addEntity(player01_entity);
	}

	public static void createShip2(Engine engine, Texture ship_texture) {

		// Player02
		ShipComponent sc_02 = new ShipComponent();
		TransformComponent tc_02 = new TransformComponent();
		MovementComponent mc_02 = new MovementComponent();
		VisualComponent vc_02 = new VisualComponent();
		CollisionComponent collider = new CollisionComponent();

		// set angle
		tc_02.setAngle(0);

		// set position
		Vector2 vec2 = new Vector2(center.x, center.y);
		Vector2 offset = new Vector2(-30, 0);
		tc_02.setPos(tc_02.getPos().cpy().add(offset));

		mc_02.setAngVel(0);
		mc_02.setVel(new Vector2(0, 0));
		mc_02.setForce(new Vector2(0, 0));
		mc_02.setAngAcc(200.0f);

		sc_02.setKeys(5, 6, 7, 8, 9);
		sc_02.setBulletCounter(0);

		float[] verticesShip = { 0.0f, 25.0f, -20.0f, -20.0f, 0.0f, -14.0f,
				20.0f, -20.0f };

		vc_02.setVertices(verticesShip);
		vc_02.setImg(ship_texture);

		collider.setColor(Color.YELLOW);
		collider.setRadius(20.0f);
		collider.setName("Player 2");
		collider.setGroup(1);
		// Collision with astroid(4) and bullet(2)
		collider.setMask(6);

		Entity player02_entity = new Entity();
		player02_entity.add(sc_02);
		player02_entity.add(tc_02);
		player02_entity.add(vc_02);
		player02_entity.add(mc_02);
		player02_entity.add(collider);
		engine.addEntity(player02_entity);

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
