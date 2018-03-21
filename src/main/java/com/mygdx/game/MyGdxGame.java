package com.mygdx.game;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
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
	SpriteBatch batch;
	Texture img;
	Texture player1_texture;
	Texture player2_texture;
	Texture asteroid_big_texture;

	Engine engine;

	ShipSystem snakesystem;
	RenderingSystem renderingsystem;
	PhysicsSystem physicsystem;
	CollisionSystem collisionsystem;
	BulletSystem bulletsystem;
	AsteroidSystem asteroidsystem;

	Vector2 center;

	@Override
	//create is called once before rendering
	public void create () {
		batch = new SpriteBatch();
		//pixels in a grid form in memory
		img = new Texture("badlogic.jpg");
		player1_texture = new Texture("player1.png");
		player2_texture = new Texture("player2.png");
		asteroid_big_texture = new Texture("asteroid_big.png");
		center = new Vector2(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);

		//SYSTEMS
		renderingsystem = new RenderingSystem(batch, Family.all(VisualComponent.class, TransformComponent.class).get());
		snakesystem = new ShipSystem(Family.all(ShipComponent.class, TransformComponent.class).get());
		physicsystem = new PhysicsSystem(Family.all(TransformComponent.class, MovementComponent.class).get(),
		Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		collisionsystem = new CollisionSystem((Family.all(TransformComponent.class, CollisionComponent.class)).get());
		bulletsystem = new BulletSystem((Family.all(TransformComponent.class, MovementComponent.class, BulletComponent.class).get()));
		asteroidsystem = new AsteroidSystem((Family.all(TransformComponent.class, MovementComponent.class, AsteroidComponent.class).get()));

		engine = new Engine();

		//add systems
		engine.addSystem(snakesystem);
		engine.addSystem(renderingsystem);
		engine.addSystem(physicsystem);
		engine.addSystem(collisionsystem);
		engine.addSystem(bulletsystem);
		engine.addSystem(asteroidsystem);

		//create entities in world
		World.createShip(engine, player1_texture);
		World.createShip2(engine, player2_texture);
		World.createAsteroid(engine, asteroid_big_texture, new Vector2(200, 280));
		World.createAsteroid(engine, asteroid_big_texture, new Vector2(-100, -140));
		World.createAsteroid(engine, asteroid_big_texture, new Vector2(170, -120));

		Gdx.input.setInputProcessor(new InputSystem());

	}

	@Override
		public void render(){
			Gdx.gl.glClearColor(0, 0, 0, 1); //r,g,b,transparency
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			float dt = Gdx.graphics.getDeltaTime();
			batch.begin();
			batch.end();
			GameKeys.update();
			engine.update(Gdx.graphics.getDeltaTime());
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
