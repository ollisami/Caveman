package caveman.test.map;

import caveman.avatar.Avatar;
import caveman.avatar.Player;
import caveman.map.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sami Ollila
 */
public class MapTest {

    int size = 100;
    Map map;

    public MapTest() {
    }

    @Before
    public void setUp() {
        this.map = new Map(size);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void mapConstructor() {
        assertEquals(0, map.getData(0, 0));
        assertEquals(0, map.getData(1, 1));
        assertEquals(0, map.getData(size - 1, size - 1));
    }

    @Test
    public void canAddRoom() {
        assertTrue(map.addRoom(10, 10));
        assertTrue(map.addRoom(10, 10));
        assertTrue(map.getEmptyRoom() != null);
    }

    @Test
    public void noErrorInSettingWalls() {
        assertTrue(map.addRoom(10, 10));
        assertTrue(map.addRoom(10, 10));
        map.setWalls();
    }

    @Test
    public void setAndGetData() {
        map.setData(5, 5, 9);
        assertEquals(9, map.getData(5, 5));
        map.setData(15, 15, 6);
        assertEquals(6, map.getData(15, 15));
    }

    @Test
    public void getMap() {
        int[][] d = map.getMap();
        assertEquals(size, d.length);
    }

    @Test
    public void getPlayerMap() {
        Avatar a = new Player(size / 2, size / 2, 9, 100);
        int[][] d = map.getPlayerView(a, 20);
        assertEquals(20, d.length);
    }
}