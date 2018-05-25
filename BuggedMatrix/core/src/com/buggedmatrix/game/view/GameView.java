package com.buggedmatrix.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.buggedmatrix.game.BuggedMatrix;
import com.buggedmatrix.game.controller.GameController;
import com.buggedmatrix.game.model.GameModel;
import com.buggedmatrix.game.model.entities.MemberModel;
import com.buggedmatrix.game.model.entities.PlayerModel;
import com.buggedmatrix.game.view.entities.EntityView;
import com.buggedmatrix.game.view.entities.ViewFactory;
import com.badlogic.gdx.Input;

import static com.buggedmatrix.game.controller.GameController.MATRIX_HEIGTH;
import static com.buggedmatrix.game.controller.GameController.MATRIX_WIDTH;

public class GameView extends ScreenAdapter {
    private static final boolean DEBUG_PHYSICS = true;
    public final static float PIXEL_TO_METER = 0.04f;
    private static final float VIEWPORT_WIDTH = 100;
    private static final float VIEWPORT_HEIGHT = 50;
    private final BuggedMatrix game;
    private final OrthographicCamera camera;
    private Box2DDebugRenderer debugRenderer;
    private Matrix4 debugCamera;

    public GameView(BuggedMatrix game) {

        this.game = game;

        //Passa imagens para asset manager
        loadAssets();

        camera = createCamera();
    }

    private void loadAssets() {
        this.game.getAssetManager().load("ground.png", Texture.class);
        this.game.getAssetManager().load("rectangle.png", Texture.class);

        this.game.getAssetManager().finishLoading();
    }

    private OrthographicCamera createCamera() {

        OrthographicCamera camera = new OrthographicCamera(VIEWPORT_WIDTH / PIXEL_TO_METER,VIEWPORT_HEIGHT / PIXEL_TO_METER);

        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        //camera.zoom += 0.1f;
        camera.update();

        if (DEBUG_PHYSICS) {
            debugRenderer = new Box2DDebugRenderer();
            debugCamera = camera.combined.cpy();
            debugCamera.scl(1 / PIXEL_TO_METER);
        }

        return camera;
    }

    public void render (float delta) {
        handleInputs(delta);
        GameController.getInstance().update(delta);

        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);

        Gdx.gl.glClearColor( 100/255f, 100/255f, 100/255f, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

        game.getBatch().begin();
        //drawBackground();
        //drawEntities();
        game.getBatch().end();

        if (DEBUG_PHYSICS) {
            debugCamera = camera.combined.cpy();
            debugCamera.scl(1/PIXEL_TO_METER);
            debugRenderer.render(GameController.getInstance().getWorld(), debugCamera);
        }
    }

    private void handleInputs(float delta)
    {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            GameController.getInstance().getPlayerOne().applyForce(0,2500);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            GameController.getInstance().getPlayerOne().applyForce(-2500, 0);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            GameController.getInstance().getPlayerOne().applyForce(2500,0);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            GameController.getInstance().getPlayerOne().applyForce(0,-2500);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            //GameController.getInstance().getPlayerTwo().applyForce(0,2500);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            //GameController.getInstance().getPlayerTwo().applyForce(-2500, 0);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            //GameController.getInstance().getPlayerTwo().applyForce(2500,0);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            //GameController.getInstance().getPlayerTwo().applyForce(0,-2500);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.C)) {
            GameController.getInstance().PlayerOneShoot();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.L)) {
            GameController.getInstance().PlayerTwoShoot();
        }
    }

    private void drawBackground() {
        Texture background = game.getAssetManager().get("ground.png", Texture.class);

        background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

        game.getBatch().draw(background, 0, 0, 0, 0, (int) (MATRIX_WIDTH / PIXEL_TO_METER), (int) (MATRIX_HEIGTH / PIXEL_TO_METER));
    }

    private void drawEntities() {
        PlayerModel playerOne = GameModel.getInstance().getPlayerOne();
        //PlayerModel playerTwo = GameModel.getInstance().getPlayerTwo();

        MemberModel chest1 = playerOne.getChest();
        //MemberModel chest2 = playerTwo.getChest();

        EntityView viewOne = ViewFactory.makeView(game, chest1);
        viewOne.update(chest1);
        viewOne.draw(game.getBatch());

        /*EntityView viewTwo = ViewFactory.makeView(game, chest2);
        viewTwo.update(chest2);
        viewTwo.draw(game.getBatch());*/
    }

}