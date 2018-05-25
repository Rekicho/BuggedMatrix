package com.buggedmatrix.game.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.buggedmatrix.game.BuggedMatrix;

public class MemberView extends EntityView {

    public MemberView(BuggedMatrix game) {
        super(game);
    }

    @Override
    public Sprite createSprite(BuggedMatrix game) {
        Texture texture = game.getAssetManager().get("rectangle.png");
        return new Sprite(texture, texture.getWidth(), texture.getHeight());
    }
}
