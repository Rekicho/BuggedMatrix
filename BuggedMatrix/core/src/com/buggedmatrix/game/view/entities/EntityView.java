package com.buggedmatrix.game.view.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.buggedmatrix.game.BuggedMatrix;
import com.buggedmatrix.game.model.entities.EntityModel;

import javax.swing.text.html.parser.Entity;

import static com.buggedmatrix.game.view.GameView.PIXEL_TO_METER;

public abstract class EntityView {

    Sprite sprite;

    public EntityView(BuggedMatrix game) {
        sprite = createSprite(game);
    }

    public abstract Sprite createSprite(BuggedMatrix game);

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public void update(EntityModel model) {
        sprite.setCenter(model.getX() / PIXEL_TO_METER, model.getY() / PIXEL_TO_METER);
        sprite.setRotation((float) Math.toDegrees(model.getRotation()));
    }
}
