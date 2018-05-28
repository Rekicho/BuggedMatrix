package com.buggedmatrix.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.buggedmatrix.game.BuggedMatrix;

public class MainMenuScreen extends ScreenAdapter {
    final BuggedMatrix game;
    OrthographicCamera camera;

    public MainMenuScreen(BuggedMatrix game)
    {
        this.game = game;
        loadAssets();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1600, 500);
    }

    private void loadAssets()
    {
        /*this.game.getAssetManager().load("menu.png", Texture.class);
        this.game.getAssetManager().finishLoading();*/
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.font.draw(game.batch, "Welcome to Drop!!! ", 100, 150);
        game.font.draw(game.batch, "Tap anywhere to begin!", 100, 100);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.startGame();
            dispose();
        }
    }
}
