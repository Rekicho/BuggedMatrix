
package com.buggedmatrix.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class GameScreen extends ScreenAdapter {
    private static final int VIEWPORT_WIDTH = 6;
    private static final float PIXEL_TO_METER = 0.22f / 200;
    private final BuggedMatrix game;
    private final Texture rectangleTexture;
    private final Texture groundTexture;
    private final OrthographicCamera camera;
    private final World world;
    private final Body playerBody;
    private final Body groundBody;

    public GameScreen(BuggedMatrix game)
    {
        this.game = game;

        game.getAssetManager().load( "rectangle.png" , Texture.class);
        game.getAssetManager().load("ground.png", Texture.class);
        game.getAssetManager().finishLoading();

        rectangleTexture = game.getAssetManager().get("rectangle.png");
        groundTexture = game.getAssetManager().get("ground.png");
        groundTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        float ratio = ((float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth());
        camera = new OrthographicCamera(VIEWPORT_WIDTH / PIXEL_TO_METER, VIEWPORT_WIDTH / PIXEL_TO_METER * ratio);
        camera.position.set(new Vector3(camera.viewportWidth / 2, camera.viewportHeight / 2, 0));

        world = new World(new Vector2(0, -10), true);

        playerBody = createPlayerBody();
        groundBody = createGroundBody();
    }

    private Body createPlayerBody()
    {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        Body body = world.createBody(bodyDef);
        float ratio = ((float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth());
        body.setTransform(VIEWPORT_WIDTH / 2, (VIEWPORT_WIDTH * ratio) / 2, 0);

        PolygonShape rectangle = new PolygonShape();
        rectangle.setAsBox(1f, 0.5f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = rectangle;
        fixtureDef.density = .5f;
        fixtureDef.friction =  .5f;
        fixtureDef.restitution =  .5f;

        body.createFixture(fixtureDef);

        rectangle.dispose();

        return body;
    }

    private Body createGroundBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;

        Body body = world.createBody(bodyDef);
        body.setTransform(0, 0, 0);

        PolygonShape rectangle = new PolygonShape();
        rectangle.setAsBox(VIEWPORT_WIDTH, 0);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = rectangle;
        fixtureDef.density = .5f;
        fixtureDef.friction =  .5f;
        fixtureDef.restitution =  .5f;

        body.createFixture(fixtureDef);

        rectangle.dispose();

        return body;
    }

    public void render(float delta)
    {
        super.render(delta);

        world.step(delta, 6, 2);

        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);

        Gdx.gl.glClearColor( 103/255f, 69/255f, 117/255f, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

        game.getBatch().begin();
        game.getBatch().draw(rectangleTexture, playerBody.getPosition().x / PIXEL_TO_METER,  playerBody.getPosition().y / PIXEL_TO_METER);
        game.getBatch().draw(groundTexture, groundBody.getPosition().x / PIXEL_TO_METER, groundBody.getPosition().y / PIXEL_TO_METER, 0, 0, (int)(VIEWPORT_WIDTH / PIXEL_TO_METER), (int)(.50f / PIXEL_TO_METER));
        game.getBatch().end();
    }

    public void dispose()
    {
        game.dispose();
    }
}