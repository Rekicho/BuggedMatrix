package com.buggedmatrix.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.buggedmatrix.game.screens.MainMenuScreen;
import com.buggedmatrix.game.view.GameView;


public class BuggedMatrix extends Game {
	private SpriteBatch batch;
	private AssetManager assetManager;
	private BitmapFont font;
	private MainMenuScreen initialMenu;
	private GameView gameScreen;

	public void create ()
	{
		batch = new SpriteBatch();
		assetManager = new AssetManager();
		font = new BitmapFont();

		startScreens();
		mainMenu();
	}

	public void startScreens()
	{
		initialMenu = new MainMenuScreen((this));
		gameScreen = new GameView(this);
	}

	public void mainMenu() { setScreen(initialMenu);}

    public void startGame() {
        setScreen(gameScreen);
    }

    public void resetGame() { gameScreen.reset();}

	public AssetManager getAssetManager() {
		return assetManager;
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public BitmapFont getFont() {
		return font;
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
