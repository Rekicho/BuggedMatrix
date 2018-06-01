import com.buggedmatrix.game.controller.GameController;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(GdxTestRunner.class)
public class TestGameController {

    @Test
    public void constructor() {
        assertNotNull(GameController.getInstance());
    }
}
