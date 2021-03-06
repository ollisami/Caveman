package caveman.test.map;

import caveman.avatar.Avatar;
import caveman.avatar.Player;
import caveman.map.Map;
import caveman.map.Room;
import org.junit.After;
import org.junit.Before;
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
        assertTrue(map.addRoom(new Room(5, 5, 5, 5)));
        assertTrue(map.addRoom(new Room(20, 20, 5, 5)));
        assertTrue(map.getRoom(0) != null);
    }

    @Test
    public void noErrorInSettingWalls() {
        assertTrue(map.addRoom(new Room(5, 5, 5, 5)));
        assertTrue(map.addRoom(new Room(20, 20, 5, 5)));
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
        Avatar a = new Player(size / 2, size / 2, 9, "player");
        int[][] d = map.getPlayerView(a, 20);
        assertEquals(20, d.length);
    }

    @Test
    public void createWalls() {
        map.addRoom(new Room(10, 10, 10, 10));
        map.setWalls();
        int[][] m = map.getMap();
        boolean hasWalls = false;
        for (int y = 0; y < m.length; y++) {
            for (int x = 0; x < m.length; x++) {
                if (m[y][x] == 2) {
                    hasWalls = true;
                    break;
                }
            }
        }
        assertTrue(hasWalls);
    }

    @Test
    public void getDataWithBadValue() {
        assertEquals(0, map.getData(-1, -1));
        assertEquals(0, map.getData(10000, 1000000));
    }

    @Test
    public void setDataWithBadValue() {
        map.setData(-1, -1, 5);
        map.setData(-100, -100, 5);
        map.setData(100000, 1000000, 5);
    }
}
