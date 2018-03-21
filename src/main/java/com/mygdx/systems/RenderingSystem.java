package com.mygdx.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.components.CollisionComponent;
import com.mygdx.components.TransformComponent;
import com.mygdx.components.VisualComponent;

/**
 * Created by Gabriela Ferenczy on 25.11.17.
 */

public class RenderingSystem extends IteratingSystem {

	private ComponentMapper<VisualComponent> vc = ComponentMapper
			.getFor(VisualComponent.class);
	private ComponentMapper<TransformComponent> tc = ComponentMapper
			.getFor(TransformComponent.class);

	// for debugging
	private ComponentMapper<CollisionComponent> cc = ComponentMapper
			.getFor(CollisionComponent.class);

	SpriteBatch batch;
	OrthographicCamera camera = new OrthographicCamera();
	ShapeRenderer shapeRenderer = new ShapeRenderer(); // Rendert Geometrie zur
														// Laufzeit

	Sprite img = new Sprite();

	boolean renderTexture = true;
	boolean renderColliderCircle = true;

	// RAINBOW COLORS
	Color purple = new Color(243 / 255f, 135 / 255f, 155 / 255f, 1.0f);
	Color green = new Color(0f, 165 / 255f, 80 / 255f, 1.0f);
	Color yellow = new Color(1.0f, 241 / 255f, 0.0f, 1.0f);
	Color blue = new Color(0f, 173 / 255f, 239 / 255f, 1.0f);

	public RenderingSystem(Family family) {
		super(family);
	}

	public RenderingSystem(Family family, int priority) {
		super(family, priority);
	}

	public RenderingSystem(SpriteBatch batch, Family family) {
		super(family);
		this.batch = batch;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {

		camera.position.set(0, 0, 0);
		camera.update();

		TransformComponent transform = tc.get(entity);
		VisualComponent visual = vc.get(entity);
		CollisionComponent collision = cc.get(entity);

		// render texture
		batch.begin();
		if (renderTexture) {
			img = visual.getImg();
			img.setSize(img.getWidth(), img.getHeight());
			img.setPosition(
					transform.getPos().x + Gdx.graphics.getWidth() / 2
							- img.getWidth() / 2,
					transform.getPos().y + Gdx.graphics.getHeight() / 2
							- img.getHeight() / 2);
			img.setRotation(transform.getAngle());
			img.draw(batch);
		}
		batch.end();

		shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
		shapeRenderer.identity();
		shapeRenderer.translate(Gdx.graphics.getWidth() / 2,
				Gdx.graphics.getHeight() / 2, 0.0f);

		// render circle collider
		if (renderColliderCircle) {
			shapeRenderer.setColor(collision.getColor());
			shapeRenderer.point(transform.getPos().x, transform.getPos().y,
					0.0f);
			shapeRenderer.circle(transform.getPos().x, transform.getPos().y,
					collision.getRadius());
		}

		shapeRenderer.end();

	}

}
