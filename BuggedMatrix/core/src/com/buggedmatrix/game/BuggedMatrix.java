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

/**
 * The game main class.
 */
public class BuggedMatrix extends Game {

	/**
	 * Sprite batch
	 */
	private SpriteBatch batch;

	/**
	 * Asset manager
	 */
	private AssetManager assetManager;

	/**
	 * Bitmap font
	 */
	private BitmapFont font;

	/**
	 * Inital menu
	 */
	private MainMenuScreen initialMenu;

	/**
	 * Game screen
	 */
	private GameView gameScreen;

	/**
	 * End menu
	 */
	private GameOverScreen gameEndMenu;

	/**
	 * Background music
	 */
    private Music music;

	/**
	 * Shooting sound
	 */
	private Sound sound;

	/**
	 * Creates the game. Initializes the sprite batch and asset manager.
	 * Also starts the game until we have a main menu.
	 */
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

	/**
	 * Initial menu screen
	 */
	public void mainMenu() { setScreen(initialMenu);}

	/**
	 * Game over menu with winner
	 * @param winner winner
	 */
	public void gameOverMenu(int winner) {
		gameEndMenu.setWinner(winner);
		setScreen(gameEndMenu);
	}

	/**
	 * Starts the game.
	 */
    public void startGame() {
        setScreen(gameScreen);
    }

	/**
	 * Resets game
	 */
	public void resetGame() { gameScreen.reset();}

	/**
	 * Returns the asset manager used to load all textures and sounds.
	 *
	 * @return the asset manager
	 */
	public AssetManager getAssetManager() {
		return assetManager;
	}

	/**
	 * Get sprite batch
	 * @return sprite batch
	 */
	public SpriteBatch getBatch() {
		return batch;
	}

	/**
	 * Get bitmap font
	 * @return bitmap font
	 */
	public BitmapFont getFont() {
		return font;
	}

	/**
	 * Render
	 */
	public void render(){
		super.render();
	}

	/**
	 * Disposes of all assets.
	 */
	public void dispose()
	{
        batch.dispose();
		assetManager.dispose();
		music.dispose();
		sound.dispose();

	}

	/**
	 * Set background music
	 * @param music background music
	 */
    public void setMusic(Music music) {
        this.music = music;
    }

	/**
	 * Set sound
	 * @param sound shooting sound
	 */
    public void setSound(Sound sound) {
        this.sound = sound;
    }

	/**
	 * Get music
	 * @return music
	 */
	public Music getMusic() {
        return music;
    }

	/**
	 * Get sound
	 * @return sound
	 */
	public Sound getSound() {
        return sound;
    }
}
