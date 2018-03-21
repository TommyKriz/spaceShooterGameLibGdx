package com.mygdx.game;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.components.AsteroidComponent;
import com.mygdx.components.BulletComponent;
import com.mygdx.components.CollisionComponent;
import com.mygdx.components.MovementComponent;
import com.mygdx.components.ShipComponent;
import com.mygdx.components.TransformComponent;
import com.mygdx.components.VisualComponent;
import com.mygdx.systems.AsteroidSystem;
import com.mygdx.systems.BulletSystem;
import com.mygdx.systems.CollisionSystem;
import com.mygdx.systems.InputSystem;
import com.mygdx.systems.PhysicsSystem;
import com.mygdx.systems.RenderingSystem;
import com.mygdx.systems.ShipSystem;

public class MyGdxGame extends ApplicationAdapter {

	private SpriteBatch batch;

	private Texture img;
	Texture player1_texture;
	Texture player2_texture;
	Texture asteroid_big_texture;

	private Engine engine;

	ShipSystem snakesystem;
	RenderingSystem renderingsystem;
	PhysicsSystem physicsystem;
	CollisionSystem collisionsystem;
	BulletSystem bulletsystem;
	AsteroidSystem asteroidsystem;

	@Override
	public void create() {

		batch = new SpriteBatch();

		initTextures();

		engine = new Engine();

		initSystems();
		addSystemsToEngine();

		createEntitesInWorld();

		Gdx.input.setInputProcessor(new InputSystem());

	}

	private void initTextures() {
		img = new Texture("badlogic.jpg");
		player1_texture = new Texture("player1.png");
		player2_texture = new Texture("player2.png");
		asteroid_big_texture = new Texture("asteroid_big.png");
	}

	private void initSystems() {
		renderingsystem = new RenderingSystem(batch, Family.all(
				VisualComponent.class, TransformComponent.class).get());
		snakesystem = new ShipSystem(Family.all(ShipComponent.class,
				TransformComponent.class).get());
		physicsystem = new PhysicsSystem(Family.all(TransformComponent.class,
				MovementComponent.class).get(), Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		collisionsystem = new CollisionSystem((Family.all(
				TransformComponent.class, CollisionComponent.class)).get());
		bulletsystem = new BulletSystem((Family.all(TransformComponent.class,
				MovementComponent.class, BulletComponent.class).get()));
		asteroidsystem = new AsteroidSystem((Family.all(
				TransformComponent.class, MovementComponent.class,
				AsteroidComponent.class).get()));
	}

	private void addSystemsToEngine() {
		engine.addSystem(snakesystem);
		engine.addSystem(renderingsystem);
		engine.addSystem(physicsystem);
		engine.addSystem(collisionsystem);
		engine.addSystem(bulletsystem);
		engine.addSystem(asteroidsystem);
	}

	private void createEntitesInWorld() {
		// TODO: is a different collision mask necessary?
		World.createShip(engine, player1_texture, 30, 0, new int[] { 0, 1, 2,
				3, 4 }, Color.YELLOW, "Player 1", 3);
		World.createShip(engine, player2_texture, -30, 10, new int[] { 5, 6, 7,
				8, 9 }, Color.PINK, "Player 2", 6);

		World.createAsteroid(engine, asteroid_big_texture,
				new Vector2(200, 280));
		World.createAsteroid(engine, asteroid_big_texture, new Vector2(-100,
				-140));
		World.createAsteroid(engine, asteroid_big_texture, new Vector2(170,
				-120));
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1); // r,g,b,transparency
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.end();
		updateInputAndEngine();
	}

	private void updateInputAndEngine() {
		engine.update(Gdx.graphics.getDeltaTime());
	}

	@Override
	public void dispose() {
		batch.dispose();
		img.dispose();
	}
}
