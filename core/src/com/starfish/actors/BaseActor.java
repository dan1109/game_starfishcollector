package com.starfish.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

public class BaseActor extends Actor {
	private Animation<TextureRegion> animation;
	private float elapsedTime;
	private boolean animationPaused;
	private Vector2 velocityVec;
	private Vector2 accelerationVec;
	private float acceleration;
	private float maxSpeed;
	private float decelaration;
	private Polygon boundaryPolygon;

	public BaseActor(final float x, final float y, Stage stage) {
		super();
		setPosition(x, y);
		stage.addActor(this);
		animation = null;
		elapsedTime = 0;
		animationPaused = false;
		velocityVec = new Vector2(0,0);
		accelerationVec = new Vector2(0,0);
		acceleration = 0;
		maxSpeed = 1000;
		decelaration = 0;
	}

	public void setAnimation(Animation<TextureRegion> animation) {
		this.animation = animation;
		TextureRegion tr = this.animation.getKeyFrame(0);
		float w = tr.getRegionWidth();
		float h = tr.getRegionHeight();
		setSize(w, h);
		setOrigin(w/2, h/2);
		if (boundaryPolygon == null) {
			setBoundaryPolygon();
		}
	}

	public void setAnimationPaused(boolean animationPaused) {
		this.animationPaused = animationPaused;
	}

	protected Animation<TextureRegion> loadAnimationFromFiles(String[] fileNames, float frameDuration, boolean loop) {
		int fileCount = fileNames.length;
		Array<TextureRegion> textureArray = new Array<TextureRegion>();
		for (int n = 0; n < fileCount; n++) {
			String fileName = fileNames[n];
			Texture texture = new Texture(Gdx.files.internal(fileName));
			texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
			textureArray.add(new TextureRegion(texture));
		}
		Animation<TextureRegion> anim = new Animation<TextureRegion>(frameDuration, textureArray);
		if (loop) {
			anim.setPlayMode(Animation.PlayMode.LOOP);
		} else {
			anim.setPlayMode(Animation.PlayMode.NORMAL);
		}

		if (animation == null) {
			setAnimation(anim);
		}
		return animation;
	}

	protected Animation<TextureRegion> loadAnimationFromSheet(String fileName, float frameDuration, int rows, int cols, boolean loop) {

		Texture texture = new Texture(Gdx.files.internal(fileName), true);
		texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		int frameHeight = texture.getHeight() / rows;
		int frameWidth = texture.getWidth() / cols;

		TextureRegion[][] temp = TextureRegion.split(texture, frameWidth, frameHeight);
		Array<TextureRegion> textureArray = new Array<TextureRegion>();
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				textureArray.add(temp[r][c]);
			}
		}

		Animation<TextureRegion> anim = new Animation<TextureRegion>(frameDuration, textureArray);
		if (loop) {
			anim.setPlayMode(Animation.PlayMode.LOOP);
		} else {
			anim.setPlayMode(Animation.PlayMode.NORMAL);
		}

		if (animation == null) {
			setAnimation(anim);
		}
		return animation;
	}

	public Animation<TextureRegion> loadTexture(String fileName) {
		String[] fileNames = new String[1];
		fileNames[0] = fileName;
		return loadAnimationFromFiles(fileNames,1, true);
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		if (!animationPaused) {
			elapsedTime += delta;
		}
	}

	public boolean isAnimationFinished() {
		return animation.isAnimationFinished(elapsedTime);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		Color color = getColor();
		batch.setColor(color.r, color.g, color.b, color.a);
		if (animation != null && isVisible()) {
			batch.draw(animation.getKeyFrame(elapsedTime),
					getX(), getY(), getOriginX(), getOriginY(),
					getWidth(), getHeight(),
					getScaleX(), getScaleY(), getRotation());
		}
	}

	public void setSpeed(float speed) {
		if (velocityVec.len() == 0) {
			velocityVec.set(speed, 0);
		} else {
			velocityVec.setLength(speed);
		}
	}

	public float getSpeed() {
		return velocityVec.len();
	}

	public void setMotionAngle(float angle) {
		velocityVec.setAngle(angle);
	}

	public float getMotionAngle() {
		return velocityVec.angle();
	}

	public boolean isMoving() {
		return (getSpeed() > 0);
	}

	public void setAcceleration(float acc) {
		acceleration  = acc;
	}

	public void accelerateAtAngle(float angle) {
		accelerationVec.add(new Vector2(acceleration, 0).setAngle(angle));
	}

	public void accelerateForward() {
		accelerateAtAngle(getRotation());
	}

	public void setMaxSpeed(float maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public void setDecelaration(float decelaration) {
		this.decelaration = decelaration;
	}

	public void applyPhysics(float dt) {
		velocityVec.add(accelerationVec.x * dt, accelerationVec.y * dt);
		float speed = getSpeed();
		if (accelerationVec.len() == 0) {
			speed -= decelaration * dt;
		}
		speed = MathUtils.clamp(speed, 0, maxSpeed);
		setSpeed(speed);
		moveBy(velocityVec.x * dt, velocityVec.y * dt);
		accelerationVec.set(0,0);
	}

	public void setBoundaryPolygon() {
		final float w = getWidth();
		final float h = getHeight();
		final float[] vertices = {0, 0, w, 0, w, h, h, 0};
		this.boundaryPolygon = new Polygon(vertices);
	}

	public void setBoundaryPolygon(int numSides) {
		final float w = getWidth();
		final float h = getHeight();
		final float[] vertices = new float[2 * numSides];
		for (int index = 0; index < numSides; index++) {
			float angle = index * 6.28f / numSides;
			vertices[2 * index] = w/2 * MathUtils.cos(angle) + w/2;
			vertices[2 * index+1] = h/2 * MathUtils.sin(angle) + h/2;
		}
		boundaryPolygon = new Polygon(vertices);
	}
}
