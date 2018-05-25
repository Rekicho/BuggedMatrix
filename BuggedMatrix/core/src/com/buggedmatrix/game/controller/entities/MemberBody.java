package com.buggedmatrix.game.controller.entities;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.buggedmatrix.game.model.entities.EntityModel;

public class MemberBody extends EntityBody {

    public MemberBody(World world, EntityModel model, int width, int height) {
        super(world, model, BodyDef.BodyType.DynamicBody);

        float density = 0.5f, friction = 0.5f, restitution = 0.25f;


        createFixture(body, new float[]{
                0,0, 0,height, width,0, width,height
        }, width, height, density, friction, restitution, BODY, BODY);

    }

    public void applyForce(float forcex, float forcey)
    {
        applyForceToCenter(forcex,forcey,true);
    }

}