package com.buggedmatrix.game.controller;

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
import com.buggedmatrix.game.controller.entities.PlayerBody;
import com.buggedmatrix.game.controller.entities.WallBody;
import com.buggedmatrix.game.model.GameModel;
import com.buggedmatrix.game.model.entities.EntityModel;

public class GameController implements ContactListener{

    private static GameController instance;

    public static final float MATRIX_WIDTH = 100;
    public static final float MATRIX_HEIGTH = 75;

    private static final float GRAVITY = -15f;

    private final World world;

    private final PlayerBody playerOne;
    private final PlayerBody playerTwo;

    private final WallBody leftWall;
    private final WallBody rightWall;
    private final WallBody floorWall;
    private final WallBody ceelingWall;


    private GameController() {
        world = new World(new Vector2(0, GRAVITY), true);

        playerOne = new PlayerBody(world, GameModel.getInstance().getPlayerOne());
        playerTwo = new PlayerBody(world, GameModel.getInstance().getPlayerTwo());

        leftWall = new WallBody(world, GameModel.getInstance().getLeftWall(), 'l');

        rightWall = new WallBody(world, GameModel.getInstance().getRightWall(), 'r');

        floorWall = new WallBody(world, GameModel.getInstance().getFloorWall(), 'f');

        ceelingWall = new WallBody(world, GameModel.getInstance().getCeelingWall(), 'c');


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

        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);

        for (Body body : bodies) {
            ((EntityModel) body.getUserData()).setPosition(body.getPosition().x, body.getPosition().y);
        }

        world.step(delta, 6, 2);
    }

    public World getWorld() {
        return world;
    }

    public PlayerBody getPlayerOne() {return playerOne; }

    public PlayerBody getPlayerTwo() {return playerTwo; }

    @Override
    public void beginContact(Contact contact) {

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
}
