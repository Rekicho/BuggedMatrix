package com.buggedmatrix.game.controller.entities;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.buggedmatrix.game.model.entities.BulletModel;
import com.buggedmatrix.game.model.entities.EntityModel;

/**
 * A concrete representation of an EntityBody representing a bullet.
 */
public class BulletBody extends EntityBody {

    /**
     * Bullet's shoot velocity
     */
    public static final int BULLET_VELOCITY = 1000;

    /**
     * Constructs a bullet body according to bullet model
     * @param world the physical world this bullet belongs to.
     * @param model the model representing this bullet.
     * @param rotation bullet'r rotation
     */
    public BulletBody(World world, EntityModel model, float rotation) {
        super(world, model, BodyDef.BodyType.DynamicBody);

        float density = 1f, friction = 0.5f, restitution = 1f;
        int width = 8, height = 20;

        createFixture(body, new float[]{
                0,0, 0,width, height,0, height,width
        }, width, height, density, friction, restitution, BODY, BODY);

        this.body.setTransform(this.body.getPosition().x, this.body.getPosition().y, rotation);
        this.body.setLinearVelocity((float) ((((BulletModel)model).getDirection())*BULLET_VELOCITY*Math.cos(rotation)),(float) ((((BulletModel)model).getDirection())*BULLET_VELOCITY*Math.sin(rotation)));
    }
}
