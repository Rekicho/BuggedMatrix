package com.buggedmatrix.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.buggedmatrix.game.BuggedMatrix;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1250;
		config.height = 625;
		new LwjglApplication(new BuggedMatrix(), config);
	}
}
