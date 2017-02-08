package caveman.test;


import caveman.avatar.AvatarController;
import caveman.GameController;
import caveman.map.MapController;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class MapControllerTest {

    MapController mapController;

    public MapControllerTest() {
    }

    @Before
    public void setUp() {  
        this.mapController = new MapController();
    }

    @After
    public void tearDown() {
    }


    //------------------MapController-------------------
    @Test
    public void mapSizeWorks() {
        mapController.createNewMap(10);
        assertEquals(10, mapController.getMapSize(0));
    }

    @Test
    public void tooSmallMapSizeFails() {
        mapController.createNewMap(-10);
        assertEquals(10, mapController.getMapSize(0));
    }

    @Test
    public void tooBigMapSizeFails() {
        mapController.createNewMap(600);
        assertEquals(500, mapController.getMapSize(0));
    }

    @Test
    public void cantFindRoomWorks() {
        assertEquals(0, mapController.getMapSize(0));
    }

    @Test
    public void addRoomWorks() {
        mapController.createNewMap(30);
        assertTrue(mapController.addRoom(0, 10, 10));
    }

    @Test
    public void addTooBigRoomsFails() {
        mapController.createNewMap(10);
        assertFalse(mapController.addRoom(0, 15, 15));
    }
}
