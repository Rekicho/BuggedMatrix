package com.buggedmatrix.game.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.buggedmatrix.game.BuggedMatrix;

public class MemberView extends EntityView {

    public MemberView(BuggedMatrix game, int bodypart) {
        super(game, bodypart);
    }

    @Override
    public Sprite createSprite(BuggedMatrix game) {

        Texture texture;
        System.out.print(getImage() + " ");

        if (getImage() == 4) {
            texture = game.getAssetManager().get("chest.png");
            return new Sprite(texture, texture.getWidth(), texture.getHeight());
        } else if (getImage() == 1) {
            texture = game.getAssetManager().get("leg.png");
            return new Sprite(texture, texture.getWidth(), texture.getHeight());
        } else if (getImage() == 2) {
            texture = game.getAssetManager().get("arm.png");
            return new Sprite(texture, texture.getWidth(), texture.getHeight());
        } else if (getImage() == 3) {
            texture = game.getAssetManager().get("head.png");
            return new Sprite(texture, texture.getWidth(), texture.getHeight());
        } else if (getImage() == 5) {
            texture = game.getAssetManager().get("chest2.png");
            return new Sprite(texture, texture.getWidth(), texture.getHeight());
        } else if (getImage() == 6) {
            texture = game.getAssetManager().get("leg2.png");
            return new Sprite(texture, texture.getWidth(), texture.getHeight());
        } else if (getImage() == 7) {
            texture = game.getAssetManager().get("arm2.png");
            return new Sprite(texture, texture.getWidth(), texture.getHeight());
        } else {
            texture = game.getAssetManager().get("head2.png");
            return new Sprite(texture, texture.getWidth(), texture.getHeight());
        }

    }
}
