import com.buggedmatrix.game.controller.GameController;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;

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
}
