package com.buggedmatrix.game.view;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.buggedmatrix.game.BuggedMatrix;
import com.buggedmatrix.game.controller.GameController;
import com.buggedmatrix.game.model.GameModel;
import com.buggedmatrix.game.model.entities.BulletModel;
import com.buggedmatrix.game.model.entities.MemberModel;
import com.buggedmatrix.game.model.entities.PlayerModel;
import com.buggedmatrix.game.view.entities.EntityView;
import com.buggedmatrix.game.view.entities.ViewFactory;
import com.badlogic.gdx.Input;

public class GameView extends ScreenAdapter {
    private static final boolean DEBUG_PHYSICS = false;
    public final static float PIXEL_TO_METER = 0.04f;
    private static final float VIEWPORT_WIDTH = 100;
    private static final float VIEWPORT_HEIGHT = 50;
    private static final int PLAYER_FORCE = 2500;
    private final BuggedMatrix game;
    private final OrthographicCamera camera;
    private Box2DDebugRenderer debugRenderer;
    private Matrix4 debugCamera;

    private final boolean android;
    private Touchpad redTouchpad;
    private Touchpad blueTouchpad;
    private Button redShoot;
    private Button blueShoot;
    private Stage stage;

    public GameView(BuggedMatrix game) {

        this.game = game;

        loadAssets();

        camera = createCamera();

        if(Gdx.app.getType() == Application.ApplicationType.Android) {
            android = true;
            createJoysticks();
        } else android = false;
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
        this.game.getAssetManager().load("newbullet.png", Texture.class);
        this.game.getAssetManager().load("bullet2.png", Texture.class);
        this.game.getAssetManager().load("wand.png", Texture.class);

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

    private void createJoysticks()
    {
        Skin buttonSkin = new Skin();
        buttonSkin.add("RedShoot", new Texture("shootred.png"));
        buttonSkin.add("BlueShoot", new Texture("shootblue.png"));

        Drawable shootRedBackground = buttonSkin.getDrawable("RedShoot");
        Drawable shootBlueBackground = buttonSkin.getDrawable("BlueShoot");

        redShoot = new Button(shootRedBackground);
        blueShoot = new Button(shootBlueBackground);

        redShoot.setPosition(75, 260);
        blueShoot.setPosition(2300, 260);

        Skin touchpadSkin = new Skin();
        touchpadSkin.add("RedBackground", new Texture("redjoystick.png"));
        touchpadSkin.add("BlueBackground", new Texture("bluejoystick.png"));
        touchpadSkin.add("joystick", new Texture("joystick.png"));

        Touchpad.TouchpadStyle touchpadRedStyle = new Touchpad.TouchpadStyle();
        Touchpad.TouchpadStyle touchpadBlueStyle = new Touchpad.TouchpadStyle();

        Drawable touchRedBackground = touchpadSkin.getDrawable("RedBackground");
        Drawable touchBlueBackground = touchpadSkin.getDrawable("BlueBackground");
        Drawable touchKnob = touchpadSkin.getDrawable("joystick");

        touchpadRedStyle.background = touchRedBackground;
        touchpadRedStyle.knob = touchKnob;

        touchpadBlueStyle.background = touchBlueBackground;
        touchpadBlueStyle.knob = touchKnob;

        redTouchpad = new Touchpad(10, touchpadRedStyle);
        redTouchpad.setBounds(25, 25, 200, 200);

        blueTouchpad = new Touchpad(10, touchpadBlueStyle);
        blueTouchpad.setBounds(2250, 25, 200, 200);

        stage = new Stage();
        stage.getViewport().setCamera(camera);
        stage.addActor(redTouchpad);
        stage.addActor(blueTouchpad);
        stage.addActor(redShoot);
        stage.addActor(blueShoot);
        Gdx.input.setInputProcessor(stage);
    }

    public void render (float delta) {
        Gdx.gl.glClearColor( 100/255f, 100/255f, 100/255f, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
        camera.update();

        handleInputs();
        if (android)
            stage.act(delta);
        GameController.getInstance().update(delta);

        game.getBatch().setProjectionMatrix(camera.combined);

        game.getBatch().begin();
        drawEntities();
        drawScore();
        game.getBatch().end();

        if (android)
            stage.draw();

        if (DEBUG_PHYSICS) {
            debugCamera = camera.combined.cpy();
            debugCamera.scl(1/PIXEL_TO_METER);
            debugRenderer.render(GameController.getInstance().getWorld(), debugCamera);
        }

        if(GameModel.getInstance().checkGameOver() == 1)
            game.gameOverMenu(1);
        else if (GameModel.getInstance().checkGameOver() == 2)
            game.gameOverMenu(2);

        else if (GameModel.getInstance().needsReset())
            softReset();
    }

    private void handleInputs()
    {
        if(android) {
            handleJoystick();
            handleButtons();
        }

        else handleKeyboard();
    }

    private void handleKeyboard()
    {
        if (Gdx.input.isKeyPressed(Input.Keys.W))
            GameController.getInstance().getPlayerOne().applyForce(0,PLAYER_FORCE);

        if (Gdx.input.isKeyPressed(Input.Keys.A))
            GameController.getInstance().getPlayerOne().applyForce(-PLAYER_FORCE, 0);

        if (Gdx.input.isKeyPressed(Input.Keys.D))
            GameController.getInstance().getPlayerOne().applyForce(PLAYER_FORCE,0);

        if (Gdx.input.isKeyPressed(Input.Keys.S))
            GameController.getInstance().getPlayerOne().applyForce(0,-PLAYER_FORCE);

        if (Gdx.input.isKeyPressed(Input.Keys.UP))
            GameController.getInstance().getPlayerTwo().applyForce(0,PLAYER_FORCE);

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            GameController.getInstance().getPlayerTwo().applyForce(-PLAYER_FORCE, 0);

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            GameController.getInstance().getPlayerTwo().applyForce(PLAYER_FORCE,0);

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
            GameController.getInstance().getPlayerTwo().applyForce(0,-PLAYER_FORCE);

        if (Gdx.input.isKeyPressed(Input.Keys.C))
            GameController.getInstance().PlayerOneShoot();

        if (Gdx.input.isKeyPressed(Input.Keys.L))
            GameController.getInstance().PlayerTwoShoot();
    }

    private void handleJoystick()
    {
        GameController.getInstance().getPlayerOne().applyForce(PLAYER_FORCE * redTouchpad.getKnobPercentX(), PLAYER_FORCE * redTouchpad.getKnobPercentY());
        GameController.getInstance().getPlayerTwo().applyForce(PLAYER_FORCE * blueTouchpad.getKnobPercentX(), PLAYER_FORCE * blueTouchpad.getKnobPercentY());
    }

    private void handleButtons() {

        if (redShoot.getClickListener().isPressed())
            GameController.getInstance().PlayerOneShoot();
        if (blueShoot.getClickListener().isPressed())
            GameController.getInstance().PlayerTwoShoot();
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
        MemberModel gun1 = playerOne.getGun();

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

        EntityView viewOneGun = ViewFactory.makeView(game, gun1);
        viewOneGun.update(gun1);
        viewOneGun.draw(game.getBatch());

        MemberModel chest2 = playerTwo.getChest();
        MemberModel leftleg2 = playerTwo.getLeftleg();
        MemberModel rightleg2 = playerTwo.getRightleg();
        MemberModel leftarm2 = playerTwo.getLeftarm();
        MemberModel rightarm2 = playerTwo.getRightarm();
        MemberModel head2 = playerTwo.getHead();
        MemberModel gun2= playerTwo.getGun();

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

        EntityView viewTwoGun = ViewFactory.makeView(game, gun2);
        viewTwoGun.update(gun2);
        viewTwoGun.draw(game.getBatch());

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

    private void drawScore()
    {
        game.getFont().getData().setScale(10);
        game.getFont().draw(game.getBatch(), Integer.toString(GameModel.getInstance().getPlayerOneScore()), 750, 1200);
        game.getFont().draw(game.getBatch(), Integer.toString(GameModel.getInstance().getPlayerTwoScore()), 1750, 1200);
    }

    public void reset() { GameController.getInstance().reset(); }

    public void softReset()
    {
        GameController.getInstance().softReset();
    }

}