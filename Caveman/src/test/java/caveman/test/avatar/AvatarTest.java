package caveman.test.avatar;

import caveman.avatar.Avatar;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AvatarTest {

    Avatar c;

    public AvatarTest() {

    }

    @Before
    public void setUp() {
        c = new Avatar(0, 0, 0, "test");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetY() {
        assertEquals(0, c.getPosY());
    }

    @Test
    public void testGetX() {
        assertEquals(0, c.getPosX());
    }

    @Test
    public void testGetSpriteValue() {
        assertEquals(0, c.getSpriteValue());
    }

    @Test
    public void testPrevDataValue() {
        c.setPrevData(1);
        assertEquals(1, c.getPrevData());
        c.setPrevData(3);
        assertEquals(1, c.getPrevData());
    }

    @Test
    public void testMove() {
        assertEquals(0, c.getPosX());
        assertEquals(0, c.getPosY());
        c.move(1, 1);
        assertEquals(1, c.getPosX());
        assertEquals(1, c.getPosY());
    }

    @Test
    public void testDistance() {
        Avatar a = new Avatar(5, 5, 5, "test");
        assertEquals(10, c.distanceTo(a));
    }

}
