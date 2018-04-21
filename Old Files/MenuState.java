package com.buggedmatrix.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;

public class MenuState extends State {

    Texture background;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, BuggedMatrix.WIDTH / 2, BuggedMatrix.HEIGHT / 2);
        background = new Texture("bg.png");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
        }
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
