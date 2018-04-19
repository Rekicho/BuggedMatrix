package com.buggedmatrix.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BuggedMatrix extends Game {
	private SpriteBatch batch;
	private AssetManager assetManager;

	public void create ()
	{
		batch = new SpriteBatch();
		assetManager = new AssetManager();

		setScreen(new GameScreen(this));
	}

	public AssetManager getAssetManager() {
		return assetManager;
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public void dispose()
	{
		assetManager.dispose();
	}
}
