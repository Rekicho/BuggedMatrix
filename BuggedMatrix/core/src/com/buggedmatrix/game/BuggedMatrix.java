package com.buggedmatrix.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.buggedmatrix.game.view.GameView;


public class BuggedMatrix extends Game {
	private SpriteBatch batch;
	private AssetManager assetManager;

	public void create ()
	{
		batch = new SpriteBatch();
		assetManager = new AssetManager();
		startGame();
	}

    private void startGame() {
        setScreen(new GameView(this));
    }

	public AssetManager getAssetManager() {
		return assetManager;
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	@Override
	public void dispose()
	{
        batch.dispose();
		assetManager.dispose();
	}

}
