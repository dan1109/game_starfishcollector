package com.starfish.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Turtle extends BaseActor {
	public Turtle(float x, float y, Stage stage) {
		super(x, y, stage);
		String[] fileNames  = {"turtle-1.png", "turtle-2.png","turtle-3.png","turtle-4.png","turtle-5.png","turtle-6.png"};
		loadAnimationFromFiles(fileNames, 0.1f, true);
		setMaxSpeed(100);
		setAcceleration(400);
		setDecelaration(400);
	}


	@Override
	public void act(float dt) {
		super.act(dt);
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			accelerateAtAngle(180);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			accelerateAtAngle(0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			accelerateAtAngle(90);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			accelerateAtAngle(270);
		}
		applyPhysics(dt);
		setAnimationPaused(!isMoving());
		if (getSpeed() > 0) {
			setRotation(getMotionAngle());
		}
	}
}
