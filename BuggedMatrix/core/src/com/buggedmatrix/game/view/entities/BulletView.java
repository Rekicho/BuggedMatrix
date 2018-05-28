package com.buggedmatrix.game.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.buggedmatrix.game.BuggedMatrix;

public class BulletView extends EntityView {
    public BulletView(BuggedMatrix game){super(game, 0);}

    public Sprite createSprite(BuggedMatrix game)
    {
        Texture texture = game.getAssetManager().get("bullet.png");
        return new Sprite(texture, texture.getWidth(), texture.getHeight());
    }
}
