package com.buggedmatrix.game.controller;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.buggedmatrix.game.controller.entities.BulletBody;
import com.buggedmatrix.game.controller.entities.PlayerBody;
import com.buggedmatrix.game.controller.entities.WallBody;
import com.buggedmatrix.game.model.GameModel;
import com.buggedmatrix.game.model.entities.BulletModel;
import com.buggedmatrix.game.model.entities.EntityModel;
import com.buggedmatrix.game.model.entities.MemberModel;
import com.buggedmatrix.game.model.entities.PlayerModel;
import com.buggedmatrix.game.model.entities.WallModel;

import static com.buggedmatrix.game.controller.entities.BulletBody.BULLET_VELOCITY;

/**
 * Controls the physics aspect of the game.
 */

public class GameController implements ContactListener{

    /**
     * The singleton instance of this controller
     */
    private static GameController instance;

    /**
     * The world width in meters.
     */
    public static final float MATRIX_WIDTH = 100;

    /**
     * The world height in meters.
     */
    public static final float MATRIX_HEIGHT = 50;

    /**
     * World gravity
     */
    private static final float GRAVITY = -15f;

    /**
     * World
     */
    private final World world;

    /**
     * Player one dummy
     */
    private final PlayerBody playerOne;

    /**
     * Player two dummy
     */
    private final PlayerBody playerTwo;

    /**
     * Player one bullet
     */
    private BulletBody playerOneBullet;

    /**
     * Player two bullet
     */
    private BulletBody playerTwoBullet;

    /**
     * Creates a new GameController that controls the physics of a certain GameModel.
     */
    private GameController() {
        world = new World(new Vector2(0, GRAVITY), true);

        playerOne = new PlayerBody(world, GameModel.getInstance().getPlayerOne());
        playerOne.setTransform(playerOne.getX(), playerOne.getY(), GameModel.getInstance().getPlayerOne().getRotation());

        playerTwo = new PlayerBody(world, GameModel.getInstance().getPlayerTwo());
        playerTwo.setTransform(playerTwo.getX(), playerTwo.getY(), GameModel.getInstance().getPlayerTwo().getRotation());

        new WallBody(world, GameModel.getInstance().getLeftWall(), 'l');
        new WallBody(world, GameModel.getInstance().getRightWall(), 'r');
        new WallBody(world, GameModel.getInstance().getFloorWall(), 'f');
        new WallBody(world, GameModel.getInstance().getCeilingWall(), 'c');

        world.setContactListener(this);
    }

    /**
     * Returns a singleton instance of a game controller
     * @return the singleton instance
     */
    public static GameController getInstance() {

        if(instance == null) {
            instance = new GameController();
        }
        return instance;
    }

    /**
     * Calculates the next physics step of duration delta (in seconds).
     * @param delta The size of this physics step in seconds.
     */
    public void update(float delta) {
        GameModel.getInstance().update(delta);

        world.step(delta, 6, 2);

        removeFlagged();

        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);

        for (Body body : bodies) {
            if (body.getUserData() instanceof BulletModel)
            {
                if(((BulletModel)body.getUserData()).isInitial())
                {
                    ((BulletModel)body.getUserData()).setInitial(false);
                    fixBulletVelocity(body);
                }
            }

            ((EntityModel) body.getUserData()).setPosition(body.getPosition().x, body.getPosition().y);
            ((EntityModel) body.getUserData()).setRotation(body.getAngle());
        }
    }


    /**
     * Get world
     * @return world
     */
    public World getWorld() {
        return world;
    }


    /**
     * Get player one
     * @return player one
     */
    public PlayerBody getPlayerOne() {return playerOne; }

    /**
     * Get player two
     * @return player two
     */
    public PlayerBody getPlayerTwo() {return playerTwo; }


    /**
     * A contact between two objects was detected
     *
     * @param contact the detected contact
     */
    public void beginContact(Contact contact) {
        Body bodyA = contact.getFixtureA().getBody();
        Body bodyB = contact.getFixtureB().getBody();

        if(bodyA.getUserData() instanceof MemberModel && bodyB.getUserData() instanceof BulletModel)
        {
            if(!((BulletModel)bodyB.getUserData()).isInitial())
                bulletCollision(bodyA,bodyB);
        }

        else if(bodyA.getUserData() instanceof BulletModel && bodyB.getUserData() instanceof MemberModel)
        {
            if(!((BulletModel)bodyA.getUserData()).isInitial())
                bulletCollision(bodyB,bodyA);
        }

        else if(bodyA.getUserData() instanceof WallModel && bodyB.getUserData() instanceof BulletModel)
            bulletBounce(bodyA, bodyB);

        else if(bodyA.getUserData() instanceof BulletModel && bodyB.getUserData() instanceof WallModel)
            bulletBounce(bodyB, bodyA);
    }

    public void bulletCollision(Body member, Body bullet)
    {
        if(((MemberModel)(member.getUserData())).getPlayerID() != ((BulletModel)(bullet.getUserData())).getPlayerID())
            GameModel.getInstance().roundWin(((BulletModel)(bullet.getUserData())).getPlayerID());

        ((BulletModel)bullet.getUserData()).setFlaggedForRemoval(true);
    }

    public void bulletBounce(Body wall, Body bullet)
    {
        ((BulletModel)bullet.getUserData()).bounce();
    }

    @Override
    public void endContact(Contact contact) {
        Body bodyA = contact.getFixtureA().getBody();
        Body bodyB = contact.getFixtureB().getBody();

        if(bodyA.getUserData() instanceof WallModel && bodyB.getUserData() instanceof BulletModel)
            fixBulletVelocity(bodyB);

        else if(bodyA.getUserData() instanceof BulletModel && bodyB.getUserData() instanceof WallModel)
            fixBulletVelocity(bodyA);
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    /**
     * Player one wants to shoot a bullet
     * @param sound sound shooting
     */
    public void PlayerOneShoot(Sound sound)
    {
        if(playerOneBullet != null || ((PlayerModel)playerOne.getUserData()).getShootTime() < 3f)
            return;

        sound.play(1.0f);
        playerOneBullet = new BulletBody(world, GameModel.getInstance().getPlayerOneBullet(), GameModel.getInstance().getPlayerOne().getGun().getRotation());
    }

    /**
     * Player two wants to shoot a bullet
     * @param sound sound shooting
     */
    public void PlayerTwoShoot(Sound sound)
    {
        if(playerTwoBullet != null || ((PlayerModel)playerTwo.getUserData()).getShootTime() < 3f)
            return;

        sound.play(1.0f);
        playerTwoBullet = new BulletBody(world, GameModel.getInstance().getPlayerTwoBullet(), GameModel.getInstance().getPlayerTwo().getGun().getRotation());
    }

    /**
     * Removes objects that have been flagged for removal on the
     * previous step.
     */
    public void removeFlagged()
    {
        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);
        for (Body body : bodies)
        {
            if (((EntityModel)body.getUserData()).isFlaggedForRemoval()) {
                if(((BulletModel)body.getUserData()).getPlayerID() == 1)
                {
                    playerOneBullet = null;
                    ((PlayerModel)playerOne.getUserData()).setShootTime(0);
                }

                else if(((BulletModel)body.getUserData()).getPlayerID() == 2)
                {
                    playerTwoBullet = null;
                    ((PlayerModel)playerTwo.getUserData()).setShootTime(0);
                }

                GameModel.getInstance().remove((EntityModel) body.getUserData());
                world.destroyBody(body);
            }
        }
    }

    /**
     * Deletes instance and resets Game Model
     */
    public static void reset() {
        instance = null;
        GameModel.reset();
    }

    /**
     * Deletes instance and soft-resets Game Model
     */
    public static void softReset() {
        instance = null;
        GameModel.softReset();
    }

    /**
     * Set bullet velocity
     * @param bullet bullet
     */
    public void fixBulletVelocity (Body bullet)
    {
        bullet.setAngularVelocity(0);
        bullet.setLinearVelocity(
                (float) (BULLET_VELOCITY*Math.cos(bullet.getLinearVelocity().angleRad())),
                (float) (BULLET_VELOCITY*Math.sin(bullet.getLinearVelocity().angleRad())));
    }

    /**
     * Get player one bullet
     * @return player one bullet
     */
    public BulletBody getPlayerOneBullet() {
        return playerOneBullet;
    }

    /**
     * Get player two bullet
     * @return player two bullet
     */
    public BulletBody getPlayerTwoBullet() {
        return playerTwoBullet;
    }
}
