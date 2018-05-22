package com.buggedmatrix.game.controller.entities;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.buggedmatrix.game.model.entities.EntityModel;

import java.awt.Rectangle;

public class PlayerBody extends EntityBody {

    public PlayerBody(World world, EntityModel model) {
        super(world, model, BodyDef.BodyType.DynamicBody);

        float density = 0.5f, friction = 0.5f, restitution = 0.25f;
        int width = 20, height = 10;

        createFixture(body, new float[]{
                0,0, 0,height, width,0, width,height
        }, width, height, density, friction, restitution, PLAYER_BODY, PLAYER_BODY);
    }

    public void applyForce(float forcex, float forcey)
    {
        applyForceToCenter(forcex,forcey,true);
    }
}
