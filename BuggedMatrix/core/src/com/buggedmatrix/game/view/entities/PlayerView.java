package com.buggedmatrix.game.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.buggedmatrix.game.BuggedMatrix;
import com.buggedmatrix.game.controller.entities.EntityBody;
import com.buggedmatrix.game.model.entities.EntityModel;
import com.buggedmatrix.game.model.entities.PlayerModel;

import static com.buggedmatrix.game.view.GameView.PIXEL_TO_METER;

public class PlayerView extends EntityView {

    public PlayerView(BuggedMatrix game) {
        super(game, 0);
    }

    @Override
    public Sprite createSprite(BuggedMatrix game) {
        Texture texture = game.getAssetManager().get("rectangle.png");
        return new Sprite(texture, texture.getWidth(), texture.getHeight());
    }

}
