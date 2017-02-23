/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caveman.test.gfx;

import caveman.GameController;
import caveman.gfx.GraphicsController;
import javax.swing.JFrame;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ollisami
 */
public class GraphicsControllerTest {

    GameController gc;
    GraphicsController gfxc;

    public GraphicsControllerTest() {
    }

    @Before
    public void setUp() {
        gc = new GameController();
        gfxc = gc.getGraphicsController();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testFrame() {
        gc.start();
        JFrame frame = gfxc.getFrame();
        assertFalse(frame == null);
        assertTrue(frame.getSize().height > 950);
        assertTrue(frame.getSize().width > 950);
        boolean t = true;
        if (frame.isResizable() == true
                || frame.isFocusable() == false
                || frame.isVisible() == false) {
            t = false;
        }
        assertTrue(t);

    }
}
