package com.buggedmatrix.game.controller.entities;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.buggedmatrix.game.model.entities.EntityModel;

import static com.buggedmatrix.game.controller.GameController.MATRIX_HEIGHT;
import static com.buggedmatrix.game.controller.GameController.MATRIX_WIDTH;
import static com.buggedmatrix.game.view.GameView.PIXEL_TO_METER;

/**
 * A concrete representation of an EntityBody representing a wall.
 */
public class WallBody extends EntityBody {

    /**
     * Constructs a wall body according to wall model
     * @param world the physical world this wall belongs to.
     * @param model the model representing this wall.
     * @param orientation wall's orientation
     */
    public WallBody(World world, EntityModel model, char orientation) {
        super(world, model, BodyDef.BodyType.StaticBody);

        float density = 0.5f, friction = 0.5f, restitution = 0.25f;

        if (orientation == 'l') {
            int width = 0, height = (int) ((MATRIX_HEIGHT +1)/PIXEL_TO_METER);
            createFixture(body, new float[]{
                    0,0, 0,-height, -1,0
            }, width, height, density, friction, restitution, BODY, BODY);
        } else if (orientation == 'r') {
            int width = 0, height = (int) ((MATRIX_HEIGHT +1)/PIXEL_TO_METER);
            createFixture(body, new float[]{
                    0,0, 0,-height, 1,0
            }, width, height, density, friction, restitution, BODY, BODY);
        } else if (orientation == 'f') {
            int width = (int) (MATRIX_WIDTH/PIXEL_TO_METER), height = 0;
            createFixture(body, new float[]{
                    0,0, 0,1, width,0
            }, width, height, density, friction, restitution, BODY, BODY);
        } else {
            int width = (int) (MATRIX_WIDTH/PIXEL_TO_METER), height = 0;
            createFixture(body, new float[]{
                    0,0, 0,-1, width,0
            }, width, height, density, friction, restitution, BODY, BODY);
        }

    }
}
