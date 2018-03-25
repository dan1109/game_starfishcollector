package com.starfish.actors;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class Whirlpool extends BaseActor {
	public Whirlpool(float x, float y, Stage stage) {
		super(x, y, stage);
		loadAnimationFromSheet("whirlpool.png", 0.1f,2, 5,  false);
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		if (isAnimationFinished()) {
			remove();
		}
	}
}
