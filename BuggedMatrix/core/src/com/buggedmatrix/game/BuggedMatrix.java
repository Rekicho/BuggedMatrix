package com.buggedmatrix.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BuggedMatrix extends Game {
    public static final int HEIGHT = 480;
    public static final int WIDTH = 800;
	private SpriteBatch batch;
	private AssetManager assetManager;
    private GameStateManager gameStateManager;

	public void create ()
	{
		batch = new SpriteBatch();
		assetManager = new AssetManager();

		setScreen(new GameScreen(this));

		gameStateManager = new GameStateManager();
		gameStateManager.push(new MenuState(gameStateManager));
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
