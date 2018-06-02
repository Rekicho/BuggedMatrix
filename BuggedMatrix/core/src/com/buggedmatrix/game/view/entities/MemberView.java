package com.buggedmatrix.game.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.buggedmatrix.game.BuggedMatrix;

public class MemberView extends EntityView {

    /**
     * Constructs a member view.
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     * @param bodypart type of body
     */
    public MemberView(BuggedMatrix game, int bodypart) {
        super(game, bodypart);
    }

    /**
     * Creates a sprite representing this member.
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     * @return the sprite representing this member
     */
    public Sprite createSprite(BuggedMatrix game) {

        Texture texture;
        int bodypart = getType();

        if (bodypart == 4) {
            texture = game.getAssetManager().get("chest.png");
            return new Sprite(texture, texture.getWidth(), texture.getHeight());
        } else if (bodypart == 1) {
            texture = game.getAssetManager().get("leg.png");
            return new Sprite(texture, texture.getWidth(), texture.getHeight());
        } else if (bodypart == 2) {
            texture = game.getAssetManager().get("arm.png");
            return new Sprite(texture, texture.getWidth(), texture.getHeight());
        } else if (bodypart == 3) {
            texture = game.getAssetManager().get("head.png");
            return new Sprite(texture, texture.getWidth(), texture.getHeight());
        } else if (bodypart == 5) {
            texture = game.getAssetManager().get("chest2.png");
            return new Sprite(texture, texture.getWidth(), texture.getHeight());
        } else if (bodypart == 6) {
            texture = game.getAssetManager().get("leg2.png");
            return new Sprite(texture, texture.getWidth(), texture.getHeight());
        } else if (bodypart == 7) {
            texture = game.getAssetManager().get("arm2.png");
            return new Sprite(texture, texture.getWidth(), texture.getHeight());
        } else if (bodypart == 8) {
            texture = game.getAssetManager().get("head2.png");
            return new Sprite(texture, texture.getWidth(), texture.getHeight());
        } else {
            texture = game.getAssetManager().get("wand.png");
            return new Sprite(texture, texture.getWidth(), texture.getHeight());
        }

    }
}
