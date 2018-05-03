package com.buggedmatrix.game.controller.entities;

import com.badlogic.gdx.physics.box2d.World;
import com.buggedmatrix.game.model.entities.EntityModel;

public class PlayerBody extends EntityBody {

    public PlayerBody(World world, EntityModel model) {
        super(world, model);

        float density = 0.5f, friction = 0.4f, restitution = 0.5f;
        int width = 10, height = 10;

        createFixture(body, new float[]{
                12,28, 15,28, 19,32, 19,42, 13,43
        }, width, height, density, friction, restitution, PLAYER_BODY, PLAYER_BODY);
    }
}
