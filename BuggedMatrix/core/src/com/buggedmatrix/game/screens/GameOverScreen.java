package com.buggedmatrix.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.buggedmatrix.game.BuggedMatrix;

public class GameOverScreen extends ScreenAdapter {
    final BuggedMatrix game;
    OrthographicCamera camera;
    int winner;

    public GameOverScreen(BuggedMatrix game, int winner)
    {
        this.game = game;
        this.winner = winner;
        loadAssets();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 2500, 1250);
    }

    private void loadAssets()
    {
        if (winner == 1)
            this.game.getAssetManager().load("redwon.png", Texture.class);
        else
            this.game.getAssetManager().load("bluewon.png", Texture.class);
        this.game.getAssetManager().finishLoading();
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);

        game.getBatch().begin();

        Texture background;

        if (winner == 1)
            background = game.getAssetManager().get("redwon.png", Texture.class);
        else
            background = game.getAssetManager().get("bluewon.png", Texture.class);

        game.getBatch().draw(background, 0 , 0);

        game.getBatch().end();

        if (Gdx.input.isTouched()) {
            game.resetGame();
            game.startGame();
        }
    }
}
