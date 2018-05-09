package com.starfish.game;

import com.badlogic.gdx.Game;

public abstract class AbstractBaseGame extends Game {
    private static AbstractBaseGame abstractBaseGame;
    public AbstractBaseGame() {
        abstractBaseGame = this;
    }

    public static void setActiveScreen(AbstractBaseScreen abstractBaseScreen) {
        abstractBaseGame.setScreen(abstractBaseScreen);
    }
}
