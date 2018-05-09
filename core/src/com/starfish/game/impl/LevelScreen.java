package com.starfish.game.impl;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.starfish.actors.*;
import com.starfish.game.AbstractBaseScreen;

public class LevelScreen extends AbstractBaseScreen {
    private Turtle turtle;
    private Boolean win;

    @Override
    protected void initialize() {
        BaseActor ocean = new BaseActor(0,0,mainStage);
        ocean.loadTexture("water-border.jpg");
        ocean.setSize(800,600);
        BaseActor.setWorldBounds(ocean);
        new Starfish(400, 400, mainStage);
        new Starfish(500, 100, mainStage);
        new Starfish(100, 450, mainStage);
        new Starfish(200, 250, mainStage);
        turtle = new Turtle(20, 20, mainStage);
        new Rock(200, 150, mainStage);
        new Rock(100, 300, mainStage);
        new Rock(300, 350, mainStage);
        new Rock(450, 200, mainStage);
        win = false;
    }

    @Override
    protected void update(float delta) {
        for (BaseActor rockActor : BaseActor.getList(mainStage, "Rock")) {
            turtle.preventOverlap(rockActor);
        }
        for (BaseActor starfishActor : BaseActor.getList(mainStage, "Starfish")) {
            Starfish starfish = (Starfish) starfishActor;
            if (turtle.overlaps(starfish) && !starfish.isCollected()) {
                starfish.collect();
                Whirlpool whirl = new Whirlpool(0, 0, mainStage);
                whirl.centerAtActor(starfish);
                whirl.setOpacity(0.25f);
            }
        }

        if (BaseActor.count(mainStage, "Starfish") == 0 && !win) {
            win = true;
            BaseActor youWinMassage = new BaseActor(0, 0, uiStage);
            youWinMassage.loadTexture("you-win.png");
            youWinMassage.centerAtPosition(400, 300);
            youWinMassage.setOpacity(0);
            youWinMassage.addAction(Actions.delay(1));
            youWinMassage.addAction(Actions.after(Actions.fadeIn(1)));
        }
    }
}
