package com.starfish;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.starfish.actors.*;
import com.starfish.game.GameBase;

public class StarfishCollector extends GameBase {

	private Turtle turtle;
	private Starfish starfish;
	private BaseActor ocean;
	private Boolean win;
	private Rock rock;

	@Override
	protected void initialize() {
		ocean = new BaseActor(0,0,mainStage);
		ocean.loadTexture("water.jpg");
		ocean.setSize(800,600);
		starfish = new Starfish(380,380,mainStage);
		turtle = new Turtle(20,20,mainStage);
		rock = new Rock(200,200, mainStage);
		win = false;
	}

	@Override
	protected void update(float delta) {
		turtle.preventOverlap(rock);
		if (turtle.overlaps(starfish) && !starfish.isCollected()) {
			starfish.collect();
			Whirlpool whirl = new Whirlpool(0,0, mainStage);
			whirl.centerAtActor(starfish);
			whirl.setOpacity(0.25f);

			BaseActor youWinMassage = new BaseActor(0, 0, mainStage);
			youWinMassage.loadTexture("you-win.png");
			youWinMassage.centerAtPosition(400, 300);
			youWinMassage.setOpacity(0);
			youWinMassage.addAction(Actions.delay(1));
			youWinMassage.addAction(Actions.after(Actions.fadeIn(1)));
		}
//
//		if (turtle.overlaps(starfish)) {
//			win = true;
//			starfish.remove();
//			winMessage.setVisible(true);
//		}
	}
}
