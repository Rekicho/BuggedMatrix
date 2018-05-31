package com.buggedmatrix.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.buggedmatrix.game.BuggedMatrix;

public class MainMenuScreen extends ScreenAdapter {
    final BuggedMatrix game;
    OrthographicCamera camera;

    public MainMenuScreen(BuggedMatrix game)
    {
        this.game = game;
        loadAssets();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 2500, 1250);
    }

    private void loadAssets()
    {
        this.game.getAssetManager().load("menu.png", Texture.class);
        this.game.getAssetManager().finishLoading();
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);

        game.getBatch().begin();

        Texture background = game.getAssetManager().get("menu.png", Texture.class);
        game.getBatch().draw(background, 0 , 0);

        game.getBatch().end();

        if (Gdx.input.justTouched()) {
            game.resetGame();
            game.startGame();
        }
    }
}
