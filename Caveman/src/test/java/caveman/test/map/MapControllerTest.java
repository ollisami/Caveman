package caveman.test.map;

import caveman.map.MapController;
import org.junit.After;
import org.junit.Before;
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
        mapController.createNewMap(50);
        assertEquals(50, mapController.getMapSize(0));
    }

    @Test
    public void tooSmallMapSizeFails() {
        mapController.createNewMap(-10);
        assertEquals(50, mapController.getMapSize(0));
    }

    @Test
    public void tooBigMapSizeFails() {
        mapController.createNewMap(6000);
        assertEquals(5000, mapController.getMapSize(0));
    }

    @Test
    public void cantFindRoomWorks() {
        assertEquals(0, mapController.getMapSize(0));
    }

    @Test
    public void getMaps() {
        assertFalse(mapController.getMaps() == null);
    }
}
