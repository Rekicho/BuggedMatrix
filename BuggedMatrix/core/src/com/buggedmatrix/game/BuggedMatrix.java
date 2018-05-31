package com.buggedmatrix.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.buggedmatrix.game.screens.GameOverScreen;
import com.buggedmatrix.game.screens.MainMenuScreen;
import com.buggedmatrix.game.view.GameView;


public class BuggedMatrix extends Game {
	private SpriteBatch batch;
	private AssetManager assetManager;
	private BitmapFont font;
	private MainMenuScreen initialMenu;
	private GameView gameScreen;
	private GameOverScreen gameEndMenu;
    private Music music;
    private Sound sound;

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
		gameEndMenu = new GameOverScreen(this);
	}

	public void mainMenu() { setScreen(initialMenu);}

	public void gameOverMenu(int winner) {
		gameEndMenu.setWinner(winner);
		setScreen(gameEndMenu);
	}

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
		music.dispose();
		sound.dispose();

	}

    public void setMusic(Music music) {
        this.music = music;
    }

    public void setSound(Sound sound) {
        this.sound = sound;
    }

    public Music getMusic() {
        return music;
    }

    public Sound getSound() {
        return sound;
    }
}
