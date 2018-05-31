package com.buggedmatrix.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
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

import java.lang.reflect.Member;

public class GameController implements ContactListener{

    private static GameController instance;

    public static final float MATRIX_WIDTH = 100;
    public static final float MATRIX_HEIGTH = 50;

    private static final float GRAVITY = -15f;

    private final World world;

    private final PlayerBody playerOne;
    private final PlayerBody playerTwo;

    private BulletBody playerOneBullet;
    private BulletBody playerTwoBullet;


    private GameController() {
        world = new World(new Vector2(0, GRAVITY), true);

        playerOne = new PlayerBody(world, GameModel.getInstance().getPlayerOne());
        playerOne.setTransform(playerOne.getX(), playerOne.getY(), GameModel.getInstance().getPlayerOne().getRotation());

        playerTwo = new PlayerBody(world, GameModel.getInstance().getPlayerTwo());
        playerTwo.setTransform(playerTwo.getX(), playerTwo.getY(), GameModel.getInstance().getPlayerTwo().getRotation());

        new WallBody(world, GameModel.getInstance().getLeftWall(), 'l');
        new WallBody(world, GameModel.getInstance().getRightWall(), 'r');
        new WallBody(world, GameModel.getInstance().getFloorWall(), 'f');
        new WallBody(world, GameModel.getInstance().getCeelingWall(), 'c');

        world.setContactListener(this);
    }

    public static GameController getInstance() {

        if(instance == null) {
            instance = new GameController();
        }
        return instance;
    }

    public void update(float delta) {
        GameModel.getInstance().update(delta);

        world.step(delta, 6, 2);

        removeFlagged();

        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);

        for (Body body : bodies) {
            if (body.getUserData() instanceof BulletModel)
                body.setAngularVelocity(0);

            ((EntityModel) body.getUserData()).setPosition(body.getPosition().x, body.getPosition().y);
            ((EntityModel) body.getUserData()).setRotation(body.getAngle());
        }
    }

    public World getWorld() {
        return world;
    }

    public PlayerBody getPlayerOne() {return playerOne; }

    public PlayerBody getPlayerTwo() {return playerTwo; }

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

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    public void PlayerOneShoot()
    {
        if(playerOneBullet != null || ((PlayerModel)playerOne.getUserData()).getShootTime() < 3f)
            return;

        playerOneBullet = new BulletBody(world, GameModel.getInstance().getPlayerOneBullet(), GameModel.getInstance().getPlayerOne().getGun().getRotation());
    }

    public void PlayerTwoShoot()
    {
        if(playerTwoBullet != null || ((PlayerModel)playerTwo.getUserData()).getShootTime() < 3f)
            return;

        playerTwoBullet = new BulletBody(world, GameModel.getInstance().getPlayerTwoBullet(), GameModel.getInstance().getPlayerTwo().getGun().getRotation());
    }

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

    public static void reset()
    {
        instance = null;
        GameModel.reset();
    }

    public static void softReset()
    {
        instance = null;
        GameModel.softReset();
    }
}
