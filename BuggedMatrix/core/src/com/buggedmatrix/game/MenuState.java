package com.buggedmatrix.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;

public class MenuState extends State {

    Texture background;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("bg.png");
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {
            handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, BuggedMatrix.WIDTH, BuggedMatrix.HEIGHT);
        sb.end();

    }
}
