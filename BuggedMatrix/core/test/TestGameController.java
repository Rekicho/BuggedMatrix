import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;
import com.buggedmatrix.game.controller.GameController;
import com.buggedmatrix.game.model.GameModel;
import com.buggedmatrix.game.model.entities.EntityModel;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.swing.text.html.parser.Entity;

@RunWith(GdxTestRunner.class)
public class TestGameController {

    @Test
    public void testConstructor() {
        assertNotNull(GameController.getInstance());
        assertNotNull(GameController.getInstance().getWorld());
        assertNotNull(GameController.getInstance().getPlayerOne());
        assertNotNull(GameController.getInstance().getPlayerTwo());
        assertNull(GameController.getInstance().getPlayerOneBullet());
        assertNull(GameController.getInstance().getPlayerTwoBullet());
    }

    @Test
    public void testUpdate() {
        Array<Body> bodies1 = new Array<Body>();
        GameController.getInstance().getWorld().getBodies(bodies1);

        GameController.getInstance().update(0.1f);

        Array<Body> bodies2 = new Array<Body>();
        GameController.getInstance().getWorld().getBodies(bodies2);

        assertEquals(bodies1.size, bodies2.size);

        for(int i = 0; i < bodies1.size && i < bodies2.size; i++) {
            assertTrue(bodies1.get(i).getPosition().y <= bodies2.get(i).getPosition().y);
            assertEquals((int)bodies1.get(i).getPosition().x, (int) ((EntityModel) bodies2.get(i).getUserData()).getX());
            assertEquals((int)bodies1.get(i).getPosition().y, (int) ((EntityModel) bodies2.get(i).getUserData()).getY());
            assertEquals((int)bodies1.get(i).getAngle(), (int) ((EntityModel) bodies2.get(i).getUserData()).getRotation());
        }
    }

    @Test
    public void testShoot() {
        Sound sound = Gdx.audio.newSound(Gdx.files.internal("shoot.mp3"));

        assertNull(GameController.getInstance().getPlayerOneBullet());
        assertNull(GameController.getInstance().getPlayerTwoBullet());

        GameController.getInstance().PlayerOneShoot(sound);
        GameController.getInstance().PlayerTwoShoot(sound);

        assertNull(GameController.getInstance().getPlayerOneBullet());
        assertNull(GameController.getInstance().getPlayerTwoBullet());

        assertEquals(0, (int) GameModel.getInstance().getPlayerOne().getShootTime());
        assertEquals(0, (int) GameModel.getInstance().getPlayerTwo().getShootTime());

        GameController.getInstance().update(3f);

        assertEquals(3, (int) GameModel.getInstance().getPlayerOne().getShootTime());
        assertEquals(3, (int) GameModel.getInstance().getPlayerTwo().getShootTime());

        GameController.getInstance().PlayerOneShoot(sound);
        GameController.getInstance().PlayerTwoShoot(sound);

        assertNotNull(GameController.getInstance().getPlayerOneBullet());
        assertNotNull(GameController.getInstance().getPlayerTwoBullet());

        assertTrue(GameModel.getInstance().getBulletOne().isInitial());
        assertTrue(GameModel.getInstance().getBulletTwo().isInitial());

        boolean playerOneTime = false;
        boolean playerTwoTime = false;
        boolean initial = true;

        while(!playerOneTime && !playerTwoTime)
        {
            GameController.getInstance().update(1f);

            if(initial)
            {
                assertFalse(GameModel.getInstance().getBulletOne().isInitial());
                assertFalse(GameModel.getInstance().getBulletTwo().isInitial());
                initial = false;
            }

            if(!playerOneTime && GameController.getInstance().getPlayerOneBullet() == null)
            {
                assertEquals(0, (int) GameModel.getInstance().getPlayerOne().getShootTime());
                playerOneTime = true;
            }

            if(!playerTwoTime && GameController.getInstance().getPlayerTwoBullet() == null)
            {
                assertEquals(0, (int) GameModel.getInstance().getPlayerTwo().getShootTime());
                playerTwoTime = true;
            }
        }
    }
}
