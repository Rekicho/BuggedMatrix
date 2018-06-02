import com.buggedmatrix.game.model.GameModel;

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
}
