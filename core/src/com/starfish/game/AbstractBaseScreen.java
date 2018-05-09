package com.starfish.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class AbstractBaseScreen implements Screen {
    protected Stage mainStage;
    protected Stage uiStage;

    public AbstractBaseScreen() {
        mainStage = new Stage();
        uiStage = new Stage();
        initialize();
    }

    protected abstract void initialize();
    protected abstract void update(float delta);

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        mainStage.act(delta);
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mainStage.draw();
        uiStage.act(delta);
        uiStage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
