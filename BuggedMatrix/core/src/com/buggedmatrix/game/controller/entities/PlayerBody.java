package com.buggedmatrix.game.controller.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef;
import com.buggedmatrix.game.model.entities.EntityModel;
import com.buggedmatrix.game.model.entities.PlayerModel;

import static com.buggedmatrix.game.view.GameView.PIXEL_TO_METER;

/**
 * A concrete representation of an EntityBody representing a player.
 */
public class PlayerBody extends EntityBody {

    /**
     * Player's left leg
     */
    private final MemberBody leftleg;

    /**
     * Player's right leg
     */
    private final MemberBody rightleg;

    /**
     * Player's left arm
     */
    private final MemberBody leftarm;

    /**
     * Player's right arm
     */
    private final MemberBody rightarm;

    /**
     * Player's chest
     */
    private final MemberBody chest;

    /**
     * Player's head
     */
    private final MemberBody head;

    /**
     * Player's gun
     */
    private final MemberBody gun;

    /**
     * Constructs a player body according to player model
     * @param world the physical world this player belongs to.
     * @param model the model representing this player.
     */
    public PlayerBody(World world, EntityModel model) {
        super(world, model, BodyDef.BodyType.DynamicBody);

        //SIZES

        float chest_x = model.getX() - 5, chest_y = model.getY() + 5, chest_size_x = 8, chest_size_y = 2,
                leg_size_x = 8, leg_size_y = 1,
                leftleg_x = chest_x + leg_size_y/2f + chest_size_y/2f + 0.9f, leftleg_y = chest_y - chest_size_x/2f - leg_size_x/2f + 0.5f,
                rightleg_x = chest_x - leg_size_y/2f - chest_size_y/2f - 0.9f, rightleg_y = leftleg_y,
                arm_size_x = 7, arm_size_y = 1,
                leftarm_x = chest_x + leg_size_y/2f + chest_size_x/2f + 0.1f, leftarm_y = chest_y + chest_size_x/2f + 0.05f,
                rightarm_x = chest_x - leg_size_y/2f - chest_size_x/2f - 0.1f, rightarm_y = leftarm_y,
                head_size = 3.5f, head_x = chest_x, head_y = chest_y + chest_size_x/2f + head_size/2f + 0.3f,
                gun_size_x = 3.5f, gun_size_y = 0.6f, player1_gun_x = leftarm_x + arm_size_x/2f + 0.5f, player2_gun_x = rightarm_x - arm_size_x/2f - 0.5f,
                gun_y = leftarm_y + arm_size_y/2f + gun_size_y;


        //DISPLAYS

        chest =  new MemberBody(world, ((PlayerModel)model).getChest(), (int) (chest_size_x/PIXEL_TO_METER), (int) (chest_size_y/PIXEL_TO_METER));
        chest.setTransform(chest_x,chest_y,(float) (Math.PI/2));

        leftleg =  new MemberBody(world, ((PlayerModel)model).getLeftleg(), (int) (leg_size_x/PIXEL_TO_METER), (int) (leg_size_y/PIXEL_TO_METER));
        leftleg.setTransform(leftleg_x,leftleg_y,(float) (2*Math.PI/3));

        rightleg =  new MemberBody(world, ((PlayerModel)model).getRightleg(), (int) (leg_size_x/PIXEL_TO_METER), (int) (leg_size_y/PIXEL_TO_METER));
        rightleg.setTransform(rightleg_x,rightleg_y,(float) (Math.PI/3));

        leftarm =  new MemberBody(world, ((PlayerModel)model).getLeftarm(), (int) (arm_size_x/PIXEL_TO_METER), (int) (arm_size_y/PIXEL_TO_METER));
        leftarm.setTransform(leftarm_x,leftarm_y, 0);

        rightarm =  new MemberBody(world, ((PlayerModel)model).getRightarm(), (int) (arm_size_x/PIXEL_TO_METER), (int) (arm_size_y/PIXEL_TO_METER));
        rightarm.setTransform(rightarm_x,rightarm_y, 0);

        head =  new MemberBody(world, ((PlayerModel)model).getHead(), (int) (head_size/PIXEL_TO_METER), (int) (head_size/PIXEL_TO_METER));
        head.setTransform(head_x, head_y, 0);

        gun =  new MemberBody(world, ((PlayerModel)model).getGun(), (int) (gun_size_x/PIXEL_TO_METER), (int) (gun_size_y/PIXEL_TO_METER));
        if (((PlayerModel)model).getPlayerID() == 1)
            gun.setTransform(player1_gun_x, gun_y, 0);
        else
            gun.setTransform(player2_gun_x, gun_y, 0);


        //JOINTS

        DistanceJointDef lefthip = new DistanceJointDef();
        DistanceJointDef righthip = new DistanceJointDef();
        DistanceJointDef leftshoulder = new DistanceJointDef();
        DistanceJointDef rightshoulder = new DistanceJointDef();
        DistanceJointDef neck = new DistanceJointDef();
        DistanceJointDef gungrip1 = new DistanceJointDef();
        DistanceJointDef gungrip2 = new DistanceJointDef();


        lefthip.initialize(leftleg.body, chest.body,
                new Vector2(chest_x + chest_size_y/2f - 0.1f,chest_y - chest_size_x/2f + 0.4f),
                new Vector2(chest_x + chest_size_y/2f - 0.2f,chest_y - chest_size_x/2f + 0.2f));
        lefthip.length = 0.01f;
        lefthip.collideConnected = true;

        righthip.initialize(rightleg.body, chest.body,
                new Vector2(chest_x - chest_size_y/2f + 0.1f,chest_y - chest_size_x/2f + 0.4f),
                new Vector2(chest_x - chest_size_y/2f + 0.2f,chest_y - chest_size_x/2f + 0.2f));
        righthip.length = 0.01f;
        righthip.collideConnected = true;

        leftshoulder.initialize(leftarm.body, chest.body,
                new Vector2(chest_x + chest_size_y/2f - 0.1f,chest_y + arm_size_x/2f),
                new Vector2(chest_x + chest_size_y/2f - 0.2f,chest_y + arm_size_x/2f));
        leftshoulder.length = 0.01f;
        leftshoulder.collideConnected = true;

        rightshoulder.initialize(rightarm.body, chest.body,
                new Vector2(chest_x - chest_size_y/2f + 0.1f,chest_y + arm_size_x/2f),
                new Vector2(chest_x - chest_size_y/2f + 0.2f,chest_y + arm_size_x/2f));
        rightshoulder.length = 0.01f;
        rightshoulder.collideConnected = true;

        neck.initialize(head.body, chest.body,
                new Vector2(head_x,head_y - head_size/2f + 0.1f),
                new Vector2(head_x,chest_y + chest_size_x/2f - 0.1f));
        neck.length = 0.01f;
        neck.collideConnected = true;

        if (((PlayerModel)model).getPlayerID() == 1) {
            gungrip1.initialize(leftarm.body, gun.body,
                    new Vector2(leftarm_x + arm_size_x/2f, leftarm_y),
                    new Vector2(leftarm_x + arm_size_x/2f, gun_y));

            gungrip2.initialize(leftarm.body, gun.body,
                    new Vector2(leftarm_x + arm_size_x/2f - 0.5f, leftarm_y),
                    new Vector2(leftarm_x + arm_size_x/2f - 0.5f, gun_y));
        } else {
            gungrip1.initialize(rightarm.body, gun.body,
                    new Vector2(rightarm_x - arm_size_x/2f, rightarm_y),
                    new Vector2(rightarm_x - arm_size_x/2f, gun_y));

            gungrip2.initialize(rightarm.body, gun.body,
                    new Vector2(rightarm_x - arm_size_x/2f + 0.5f, rightarm_y),
                    new Vector2(rightarm_x - arm_size_x/2f + 0.5f, gun_y));
        }

        gungrip1.length = 0.01f;
        gungrip2.length = 0.01f;

        gungrip1.collideConnected = true;
        gungrip2.collideConnected = true;

        world.createJoint(lefthip);
        world.createJoint(righthip);
        world.createJoint(leftshoulder);
        world.createJoint(rightshoulder);
        world.createJoint(neck);
        world.createJoint(gungrip1);
        world.createJoint(gungrip2);
    }

    /**
     * Apply force to player's centre
     * @param forcex force x-axis
     * @param forcey force y-axis
     */
    public void applyForce(float forcex, float forcey)
    {
        chest.applyForceToCenter(forcex,forcey,true);
    }
}
