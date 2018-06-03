import com.buggedmatrix.game.model.GameModel;
import com.buggedmatrix.game.model.entities.EntityModel;

import static com.buggedmatrix.game.model.entities.BulletModel.MAX_BOUNCES;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(GdxTestRunner.class)
public class TestGameModel {

    @Test
    public void testConstructor() {
        assertNotNull(GameModel.getInstance());
        assertNotNull(GameModel.getInstance().getPlayerOne());
        assertNotNull(GameModel.getInstance().getPlayerTwo());
        assertNotNull(GameModel.getInstance().getLeftWall());
        assertNotNull(GameModel.getInstance().getRightWall());
        assertNotNull(GameModel.getInstance().getFloorWall());
        assertNotNull(GameModel.getInstance().getCeilingWall());
        assertNull(GameModel.getInstance().getBulletOne());
        assertNull(GameModel.getInstance().getBulletTwo());
    }

    @Test
    public void testRoundWin(){
        assertEquals(0, GameModel.getPlayerOneScore());
        assertEquals(0, GameModel.getPlayerTwoScore());

        GameModel.getInstance().roundWin(1);
        assertEquals(1, GameModel.getPlayerOneScore());

        assertTrue(GameModel.getInstance().needsReset());
        GameModel.softReset();

        GameModel.getInstance().roundWin(2);
        assertEquals(1, GameModel.getPlayerTwoScore());
    }

    @Test
    public void testGameWin(){
        assertEquals(0, GameModel.getPlayerOneScore());
        assertEquals(0, GameModel.getPlayerTwoScore());

        GameModel.getInstance().roundWin(1);
        assertEquals(1, GameModel.getPlayerOneScore());
        assertTrue(GameModel.getInstance().needsReset());
        GameModel.softReset();

        GameModel.getInstance().roundWin(2);
        assertEquals(1, GameModel.getPlayerTwoScore());
        assertTrue(GameModel.getInstance().needsReset());
        GameModel.softReset();

        GameModel.getInstance().roundWin(1);
        assertEquals(2, GameModel.getPlayerOneScore());
        assertTrue(GameModel.getInstance().needsReset());
        GameModel.softReset();

        GameModel.getInstance().roundWin(2);
        assertEquals(2, GameModel.getPlayerTwoScore());
        assertTrue(GameModel.getInstance().needsReset());
        GameModel.softReset();

        GameModel.getInstance().roundWin(1);
        assertEquals(3, GameModel.getPlayerOneScore());
        assertEquals(1, GameModel.getInstance().checkGameOver());

        GameModel.reset();
        assertEquals(0, GameModel.getPlayerOneScore());
        assertEquals(0, GameModel.getPlayerTwoScore());
    }

    @Test
    public void testPlayerMembers()
    {
        assertNotNull(GameModel.getInstance().getPlayerOne());
        assertEquals(EntityModel.ModelType.PLAYER,GameModel.getInstance().getPlayerOne().getType());
        assertNotNull(GameModel.getInstance().getPlayerOne().getHead());
        assertEquals(EntityModel.ModelType.HEAD1,GameModel.getInstance().getPlayerOne().getHead().getType());
        assertNotNull(GameModel.getInstance().getPlayerOne().getChest());
        assertEquals(EntityModel.ModelType.CHEST1,GameModel.getInstance().getPlayerOne().getChest().getType());
        assertNotNull(GameModel.getInstance().getPlayerOne().getLeftarm());
        assertEquals(EntityModel.ModelType.ARM1,GameModel.getInstance().getPlayerOne().getLeftarm().getType());
        assertNotNull(GameModel.getInstance().getPlayerOne().getRightarm());
        assertEquals(EntityModel.ModelType.ARM1,GameModel.getInstance().getPlayerOne().getRightarm().getType());
        assertNotNull(GameModel.getInstance().getPlayerOne().getLeftleg());
        assertEquals(EntityModel.ModelType.LEG1,GameModel.getInstance().getPlayerOne().getLeftleg().getType());
        assertNotNull(GameModel.getInstance().getPlayerOne().getRightleg());
        assertEquals(EntityModel.ModelType.LEG1,GameModel.getInstance().getPlayerOne().getRightleg().getType());
        assertNotNull(GameModel.getInstance().getPlayerOne().getGun());
        assertEquals(EntityModel.ModelType.GUN,GameModel.getInstance().getPlayerOne().getGun().getType());

        assertNotNull(GameModel.getInstance().getPlayerTwo());
        assertEquals(EntityModel.ModelType.PLAYER,GameModel.getInstance().getPlayerTwo().getType());
        assertNotNull(GameModel.getInstance().getPlayerTwo().getHead());
        assertEquals(EntityModel.ModelType.HEAD2,GameModel.getInstance().getPlayerTwo().getHead().getType());
        assertNotNull(GameModel.getInstance().getPlayerTwo().getChest());
        assertEquals(EntityModel.ModelType.CHEST2,GameModel.getInstance().getPlayerTwo().getChest().getType());
        assertNotNull(GameModel.getInstance().getPlayerTwo().getLeftarm());
        assertEquals(EntityModel.ModelType.ARM2,GameModel.getInstance().getPlayerTwo().getLeftarm().getType());
        assertNotNull(GameModel.getInstance().getPlayerTwo().getRightarm());
        assertEquals(EntityModel.ModelType.ARM2,GameModel.getInstance().getPlayerTwo().getRightarm().getType());
        assertNotNull(GameModel.getInstance().getPlayerTwo().getLeftleg());
        assertEquals(EntityModel.ModelType.LEG2,GameModel.getInstance().getPlayerTwo().getLeftleg().getType());
        assertNotNull(GameModel.getInstance().getPlayerTwo().getRightleg());
        assertEquals(EntityModel.ModelType.LEG2,GameModel.getInstance().getPlayerTwo().getRightleg().getType());
        assertNotNull(GameModel.getInstance().getPlayerTwo().getGun());
        assertEquals(EntityModel.ModelType.GUN,GameModel.getInstance().getPlayerTwo().getGun().getType());
    }

    @Test
    public void testBulletBounce()
    {
        assertFalse(GameModel.getInstance().getPlayerOneBullet().isFlaggedForRemoval());

        for(int bounces = 0; bounces <= MAX_BOUNCES; bounces++)
            GameModel.getInstance().getPlayerOneBullet().bounce();

        assertTrue(GameModel.getInstance().getPlayerOneBullet().isFlaggedForRemoval());
    }
}
