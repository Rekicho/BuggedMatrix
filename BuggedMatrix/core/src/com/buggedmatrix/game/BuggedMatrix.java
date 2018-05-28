package com.buggedmatrix.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.buggedmatrix.game.screens.MainMenuScreen;
import com.buggedmatrix.game.view.GameView;


public class BuggedMatrix extends Game {
	public SpriteBatch batch;
	private AssetManager assetManager;
	public BitmapFont font;

	public void create ()
	{
		batch = new SpriteBatch();
		assetManager = new AssetManager();
		font = new BitmapFont();
		mainMenu();
	}

	private void mainMenu() { setScreen(new MainMenuScreen(this));}

    public void startGame() {
        setScreen(new GameView(this));
    }

	public AssetManager getAssetManager() {
		return assetManager;
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public void render(){
		super.render();
	}

	public void dispose()
	{
        batch.dispose();
		assetManager.dispose();
	}

}
