package com.starfish.game.impl;

import com.starfish.game.AbstractBaseGame;

public class StarfishGame extends AbstractBaseGame {
    @Override
    public void create() {
        setActiveScreen(new MenuScreen());
    }
}
