package com.starfish;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.starfish.actors.BaseActor;
import com.starfish.actors.Starfish;
import com.starfish.actors.Turtle;
import com.starfish.game.GameBase;

public class StarfishCollector extends GameBase {

	private Turtle turtle;
	private Starfish starfish;
	private BaseActor ocean;
	private Boolean win;

	@Override
	protected void initialize() {
		ocean = new BaseActor(0,0,mainStage);
		ocean.loadTexture("water.jpg");
		ocean.setSize(800,600);
		starfish = new Starfish(380,380,mainStage);
		turtle = new Turtle(20,20,mainStage);
		win = false;
	}

	@Override
	protected void update(float delta) {
//
//		if (turtle.overlaps(sharky)) {
//			turtle.remove();
//			winMessage.remove();
//			gameOverMessage.setVisible(true);
//		}
//
//		if (turtle.overlaps(starfish)) {
//			win = true;
//			starfish.remove();
//			winMessage.setVisible(true);
//		}
	}
}
