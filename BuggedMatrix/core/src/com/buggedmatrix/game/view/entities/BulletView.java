package com.buggedmatrix.game.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.buggedmatrix.game.BuggedMatrix;

/**
 * A view representing a bullet
 */
public class BulletView extends EntityView {

    /**
     * Constructs a bullet view.
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     * @param type type of body
     */
    public BulletView(BuggedMatrix game, int type){
        super(game, type);
    }

    /**
     * Creates a sprite representing this bullet.
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     * @return the sprite representing this bullet
     */
    public Sprite createSprite(BuggedMatrix game)
    {
        int player = -getType();

        if(player == 1) {
            Texture texture = game.getAssetManager().get("newbullet.png");
            return new Sprite(texture, texture.getWidth(), texture.getHeight());
        }

        else {
            Texture texture = game.getAssetManager().get("newbullet.png");
            return new Sprite(texture, texture.getWidth(), texture.getHeight());
        }
    }
}
