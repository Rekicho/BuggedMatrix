package com.buggedmatrix.game.controller.entities;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.buggedmatrix.game.model.entities.EntityModel;

/**
 * A concrete representation of an EntityBody representing a member.
 */
public class MemberBody extends EntityBody {


    /**
     * Constructs a member body according to member model
     * @param world the physical world this member belongs to.
     * @param model the model representing this member.
     * @param width member width
     * @param height member height
     */
    public MemberBody(World world, EntityModel model, int width, int height) {
        super(world, model, BodyDef.BodyType.DynamicBody);

        float density = 0.5f, friction = 0.5f, restitution = 0.25f;


        createFixture(body, new float[]{
                0,0, 0,height, width,0, width,height
        }, width, height, density, friction, restitution, BODY, BODY);

    }


    /**
     * Apply force to member's center
     * @param forcex force x-axis
     * @param forcey force y-axis
     */
    public void applyForce(float forcex, float forcey)
    {
        applyForceToCenter(forcex,forcey,true);
    }

}