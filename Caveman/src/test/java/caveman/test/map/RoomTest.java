package caveman.test.map;

import caveman.map.Room;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class RoomTest {

    public RoomTest() {
    }
    Room r;

    @Before
    public void setUp() {
        r = new Room(0, 0, 12, 12);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testLeft() {
        assertEquals(0, r.getLeft());
    }

    @Test
    public void testTop() {
        assertEquals(0, r.getTop());
    }

    @Test
    public void testRight() {
        assertEquals(11, r.getRight());
    }

    @Test
    public void testBottom() {
        assertEquals(11, r.getBottom());
    }

    @Test
    public void testCenterX() {
        assertEquals(6, r.getCenterX());
    }

    @Test
    public void testCenterY() {
        assertEquals(6, r.getCenterY());
    }

    @Test
    public void testWidth() {
        assertEquals(12, r.getWidth());
    }

    @Test
    public void testHeight() {
        assertEquals(12, r.getHeight());
    }

    @Test
    public void Constructor() {
        int[][] data = r.getRoom();
        assertEquals(data[0][0], 1);
    }

    @Test
    public void setData() {
        r.setData(-1, -1, 0);
        r.setData(-100, -100, 0);
        r.setData(100000000, 10000000, 0);

    }
}
