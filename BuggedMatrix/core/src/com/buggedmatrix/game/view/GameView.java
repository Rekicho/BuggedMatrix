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
import com.buggedmatrix.game.model.entities.BulletModel;
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

        loadAssets();

        camera = createCamera();
    }

    private void loadAssets() {
        this.game.getAssetManager().load("head.png", Texture.class);
        this.game.getAssetManager().load("chest.png", Texture.class);
        this.game.getAssetManager().load("leg.png", Texture.class);
        this.game.getAssetManager().load("arm.png", Texture.class);
        this.game.getAssetManager().load("head2.png", Texture.class);
        this.game.getAssetManager().load("chest2.png", Texture.class);
        this.game.getAssetManager().load("leg2.png", Texture.class);
        this.game.getAssetManager().load("arm2.png", Texture.class);
        this.game.getAssetManager().load("bullet.png", Texture.class);
        this.game.getAssetManager().load("bullet2.png", Texture.class);

        this.game.getAssetManager().finishLoading();
    }

    private OrthographicCamera createCamera() {

        OrthographicCamera camera = new OrthographicCamera(VIEWPORT_WIDTH / PIXEL_TO_METER,VIEWPORT_HEIGHT / PIXEL_TO_METER);

        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
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
        drawEntities();
        drawLifes();
        game.getBatch().end();

        if (DEBUG_PHYSICS) {
            debugCamera = camera.combined.cpy();
            debugCamera.scl(1/PIXEL_TO_METER);
            debugRenderer.render(GameController.getInstance().getWorld(), debugCamera);
        }

        if(GameModel.getInstance().checkGameOver() != 0)
            game.mainMenu();

        else if(GameModel.getInstance().needsReset())
            softReset();
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
            GameController.getInstance().getPlayerTwo().applyForce(0,2500);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            GameController.getInstance().getPlayerTwo().applyForce(-2500, 0);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            GameController.getInstance().getPlayerTwo().applyForce(2500,0);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            GameController.getInstance().getPlayerTwo().applyForce(0,-2500);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.C)) {
            GameController.getInstance().PlayerOneShoot();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.L)) {
            GameController.getInstance().PlayerTwoShoot();
        }
    }

    private void drawEntities() {
        PlayerModel playerOne = GameModel.getInstance().getPlayerOne();
        PlayerModel playerTwo = GameModel.getInstance().getPlayerTwo();

        MemberModel chest1 = playerOne.getChest();
        MemberModel leftleg1 = playerOne.getLeftleg();
        MemberModel rightleg1 = playerOne.getRightleg();
        MemberModel leftarm1 = playerOne.getLeftarm();
        MemberModel rightarm1 = playerOne.getRightarm();
        MemberModel head1 = playerOne.getHead();

        EntityView viewOneChest = ViewFactory.makeView(game, chest1);
        viewOneChest.update(chest1);
        viewOneChest.draw(game.getBatch());

        EntityView viewOneLeftLeg = ViewFactory.makeView(game, leftleg1);
        viewOneLeftLeg.update(leftleg1);
        viewOneLeftLeg.draw(game.getBatch());

        EntityView viewOneRightLeg = ViewFactory.makeView(game, rightleg1);
        viewOneRightLeg.update(rightleg1);
        viewOneRightLeg.draw(game.getBatch());

        EntityView viewOneLeftArm = ViewFactory.makeView(game, leftarm1);
        viewOneLeftArm.update(leftarm1);
        viewOneLeftArm.draw(game.getBatch());

        EntityView viewOneRightArm = ViewFactory.makeView(game, rightarm1);
        viewOneRightArm.update(rightarm1);
        viewOneRightArm.draw(game.getBatch());

        EntityView viewOneHead = ViewFactory.makeView(game, head1);
        viewOneHead.update(head1);
        viewOneHead.draw(game.getBatch());

        MemberModel chest2 = playerTwo.getChest();
        MemberModel leftleg2 = playerTwo.getLeftleg();
        MemberModel rightleg2 = playerTwo.getRightleg();
        MemberModel leftarm2 = playerTwo.getLeftarm();
        MemberModel rightarm2 = playerTwo.getRightarm();
        MemberModel head2 = playerTwo.getHead();

        EntityView viewTwoChest = ViewFactory.makeView(game, chest2);
        viewTwoChest.update(chest2);
        viewTwoChest.draw(game.getBatch());

        EntityView viewTwoLeftLeg = ViewFactory.makeView(game, leftleg2);
        viewTwoLeftLeg.update(leftleg2);
        viewTwoLeftLeg.draw(game.getBatch());

        EntityView viewTwoRightLeg = ViewFactory.makeView(game, rightleg2);
        viewTwoRightLeg.update(rightleg2);
        viewTwoRightLeg.draw(game.getBatch());

        EntityView viewTwoLeftArm = ViewFactory.makeView(game, leftarm2);
        viewTwoLeftArm.update(leftarm2);
        viewTwoLeftArm.draw(game.getBatch());

        EntityView viewTwoRightArm = ViewFactory.makeView(game, rightarm2);
        viewTwoRightArm.update(rightarm2);
        viewTwoRightArm.draw(game.getBatch());

        EntityView viewTwoHead = ViewFactory.makeView(game, head2);
        viewTwoHead.update(head2);
        viewTwoHead.draw(game.getBatch());

        BulletModel bulletOne = GameModel.getInstance().getBulletOne();
        BulletModel bulletTwo = GameModel.getInstance().getBulletTwo();

        if(bulletOne != null)
        {
            EntityView viewOneBullet = ViewFactory.makeView(game, bulletOne);
            viewOneBullet.update(bulletOne);
            viewOneBullet.draw(game.getBatch());
        }

        if(bulletTwo != null)
        {
            EntityView viewTwoBullet = ViewFactory.makeView(game, bulletTwo);
            viewTwoBullet.update(bulletTwo);
            viewTwoBullet.draw(game.getBatch());
        }
    }

    private void drawLifes()
    {
        game.getFont().getData().setScale(10);
        game.getFont().draw(game.getBatch(), Integer.toString(GameModel.getInstance().getPlayerOneLifes()), 0, 1250);
        game.getFont().draw(game.getBatch(), Integer.toString(GameModel.getInstance().getPlayerTwoLifes()), 2400, 1250);
    }

    public void reset() { GameController.getInstance().reset(); }

    public void softReset()
    {
        GameController.getInstance().softReset();
    }

}