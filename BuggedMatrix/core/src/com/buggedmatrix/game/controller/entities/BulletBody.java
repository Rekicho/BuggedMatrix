package com.buggedmatrix.game.controller.entities;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.buggedmatrix.game.model.entities.BulletModel;
import com.buggedmatrix.game.model.entities.EntityModel;

public class BulletBody extends EntityBody {
    static final int BULLET_VELOCITY = 1000;

    public BulletBody(World world, EntityModel model, float rotation) {
        super(world, model, BodyDef.BodyType.DynamicBody);

        float density = 1f, friction = 0.5f, restitution = 1f;
        int width = 20, height = 8;

        createFixture(body, new float[]{
                0,0, 0,height, width,0, width,height
        }, width, height, density, friction, restitution, BODY, BODY);

        this.body.setLinearVelocity((float) ((((BulletModel)model).getDirection())*(BULLET_VELOCITY*Math.cos(rotation))),(float) ((((BulletModel)model).getDirection())*BULLET_VELOCITY*Math.sin(rotation)));
    }
}
