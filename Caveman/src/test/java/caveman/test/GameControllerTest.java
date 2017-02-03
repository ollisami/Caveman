package caveman.test;


import caveman.GameController;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
        assertFalse(gameController.getCharacterController() == null);
        assertFalse(gameController.getMapController() == null);
    }

    @Test
    public void getCurrentMapWorks() {
        assertTrue(gameController.getCurrentMap() == null);
        gameController.createMap();
        assertFalse(gameController.getCurrentMap() == null);
    }

}
