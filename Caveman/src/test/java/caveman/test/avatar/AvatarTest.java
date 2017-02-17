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

}
