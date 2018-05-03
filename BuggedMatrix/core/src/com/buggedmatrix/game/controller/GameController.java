package com.buggedmatrix.game.controller;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.buggedmatrix.game.controller.entities.PlayerBody;
import com.buggedmatrix.game.model.GameModel;
import com.buggedmatrix.game.model.entities.EntityModel;

public class GameController implements ContactListener{

    private static GameController instance;

    public static final int MATRIX_WIDTH = 100;
    public static final int MATRIX_HEIGTH = 50;

    private static final float GRAVITY = -10f;

    private final World world;

    private final PlayerBody playerBody;

    private GameController() {
        world = new World(new Vector2(0, GRAVITY), true);

        playerBody = new PlayerBody(world, GameModel.getInstance().getPlayerOne());

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
            verifyBounds(body);
            ((EntityModel) body.getUserData()).setPosition(body.getPosition().x, body.getPosition().y);
        }
    }

    private void verifyBounds(Body body) {
        if (body.getPosition().x < 0)
            body.setTransform(0, body.getPosition().y, body.getAngle());
        if (body.getPosition().x > MATRIX_WIDTH)
            body.setTransform(MATRIX_WIDTH, body.getPosition().y, body.getAngle());
        if (body.getPosition().y < 0)
            body.setTransform(body.getPosition().x, 0, body.getAngle());
        if (body.getPosition().y > MATRIX_HEIGTH)
            body.setTransform(body.getPosition().x, MATRIX_HEIGTH, body.getAngle());
    }

    public World getWorld() {
        return world;
    }

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
