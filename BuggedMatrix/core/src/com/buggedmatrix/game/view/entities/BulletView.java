package com.buggedmatrix.game.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.buggedmatrix.game.BuggedMatrix;

public class BulletView extends EntityView {
    public BulletView(BuggedMatrix game, int type){super(game, type);}

    public Sprite createSprite(BuggedMatrix game)
    {
        int player = -getType();

        if(player == 1) {
            Texture texture = game.getAssetManager().get("bullet.png");
            return new Sprite(texture, texture.getWidth(), texture.getHeight());
        }

        else {
            Texture texture = game.getAssetManager().get("bullet2.png");
            return new Sprite(texture, texture.getWidth(), texture.getHeight());
        }
    }
}
