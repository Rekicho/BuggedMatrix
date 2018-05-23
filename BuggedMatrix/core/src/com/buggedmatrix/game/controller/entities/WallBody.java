package com.buggedmatrix.game.controller.entities;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.buggedmatrix.game.model.entities.EntityModel;

import static com.buggedmatrix.game.controller.GameController.MATRIX_HEIGTH;
import static com.buggedmatrix.game.controller.GameController.MATRIX_WIDTH;
import static com.buggedmatrix.game.view.GameView.PIXEL_TO_METER;

public class WallBody extends EntityBody {

    public WallBody(World world, EntityModel model, char orientation) {
        super(world, model, BodyDef.BodyType.StaticBody);

        float density = 0.5f, friction = 0.5f, restitution = 0.25f;

        if (orientation == 'l') {
            int width = 0, height = (int) ((MATRIX_HEIGTH+1)/PIXEL_TO_METER);
            createFixture(body, new float[]{
                    0,0, 0,-height, -1,0
            }, width, height, density, friction, restitution, PLAYER_BODY, PLAYER_BODY);
        } else if (orientation == 'r') {
            int width = 0, height = (int) ((MATRIX_HEIGTH+1)/PIXEL_TO_METER);
            createFixture(body, new float[]{
                    0,0, 0,-height, 1,0
            }, width, height, density, friction, restitution, PLAYER_BODY, PLAYER_BODY);
        } else if (orientation == 'f') {
            int width = (int) (MATRIX_WIDTH/PIXEL_TO_METER), height = 0;
            createFixture(body, new float[]{
                    0,0, 0,1, width,0
            }, width, height, density, friction, restitution, PLAYER_BODY, PLAYER_BODY);
        } else {
            int width = (int) (MATRIX_WIDTH/PIXEL_TO_METER), height = 0;
            createFixture(body, new float[]{
                    0,0, 0,-1, width,0
            }, width, height, density, friction, restitution, PLAYER_BODY, PLAYER_BODY);
        }

    }
}
