package com.starfish.game.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.starfish.actors.BaseActor;
import com.starfish.game.AbstractBaseScreen;

public class MenuScreen extends AbstractBaseScreen {
    @Override
    protected void initialize() {
        BaseActor ocean = new BaseActor(0,0,mainStage);
        ocean.loadTexture("water.jpg");
        ocean.setSize(800,600);
        BaseActor.setWorldBounds(ocean);
        BaseActor title = new BaseActor(0,0,mainStage);
        title.loadTexture("starfish-collector.png");
        title.centerAtPosition(400,300);
        title.moveBy(0,100);
        BaseActor start = new BaseActor(0,0,mainStage);
        start.loadTexture("message-start.png");
        start.centerAtPosition(400,300);
        start.moveBy(0,-100);
    }

    @Override
    protected void update(float delta) {
        if (Gdx.input.isKeyPressed(Keys.S)) {
            StarfishGame.setActiveScreen(new LevelScreen());
        }
    }
}
