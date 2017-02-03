package caveman.test;

import caveman.AvatarController;
import caveman.GameController;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AvatarControllerTest {

    public AvatarControllerTest() {
    }

    GameController gc;
    AvatarController cc;

    @Before
    public void setUp() {
        gc = new GameController();
        cc = gc.getCharacterController();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getPlayer() {
        gc.createMap();
        cc.getPlayer();
        assertFalse(cc.getPlayer() == null);
    }

    @Test
    public void getEnemies() {
        gc.createMap();
        assertFalse(cc.getEnemies().isEmpty());
    }
}
