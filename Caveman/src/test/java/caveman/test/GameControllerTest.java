package caveman.test;

import caveman.GameController;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ollisami
 */
public class GameControllerTest {

    GameController gameController;

    public GameControllerTest() {
    }

    @Before
    public void setUp() {
        this.gameController = new GameController();
    }

    @After
    public void tearDown() {
    }

    //-------------------GameController-----------------
    @Test
    public void GCConstructorWorks() {
        assertFalse(gameController.getAvatarController() == null);
        assertFalse(gameController.getMapController() == null);
    }

    @Test
    public void getCurrentMapWorks() {
        assertTrue(gameController.getCurrentMap() == null);
        gameController.createMap();
        assertFalse(gameController.getCurrentMap() == null);
    }

    @Test
    public void noErrorOnRepaint() {
        gameController.createMap();
        gameController.repaint();
    }

    @Test
    public void getPlayerView() {
        gameController.createMap();
        int[][] pv = gameController.getPlayerView();
    }

    @Test
    public void keyPressedTest() {
        gameController.createMap();
        gameController.keyPressed(0, 0);
    }

    @Test
    public void getControllersTest() {
        assertFalse(gameController.getAvatarController() == null);
        assertFalse(gameController.getGraphicsController() == null);
        assertFalse(gameController.getMapController() == null);
    }
}
