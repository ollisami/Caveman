
import caveman.caveman.MapController;
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

    @Test
    public void mapConstructorpWorks() {
        assertEquals(0, mapController.getMaps().size());
    }

    @Test
    public void createMapWorks() {
        mapController.createNewMap(10);
    }

    @Test
    public void createMultipleMapsWorks() {
        mapController.createNewMap(10);
        mapController.createNewMap(20);
        mapController.createNewMap(50);
    }

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

    @Test
    public void roomGetsAdded() {
        mapController.createNewMap(10);
        assertTrue(mapController.addRoom(0, 2, 2));
        String s = mapController.getMaps().get(0).toString();
        boolean hasWalls = false;
        for (char c : s.toCharArray()) {
            if (c == '1') {
                hasWalls = true;
            }
        }
        assertTrue(hasWalls);
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
