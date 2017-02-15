package caveman.gfx;

import caveman.GameController;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/** 
 * Tarkastaa onka näppäimiä painettu ja raportoi siitä GameControllerille.
 * 
 * @version     1.0
 * @author      Sami Ollila
 */ 
public class IsKeyPressed implements KeyListener {

    private GameController gc;

    public IsKeyPressed(GameController gc) {
        this.gc = gc;
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    /**
     * Tarkistaa painoiko pelaaja nuolinäppäimiä, jos niin välittää tiedon
     * gameControllerille.
     *
     * @param e keyEvent
     *
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        int dx = 0;
        int dy = 0;
        if (key == KeyEvent.VK_LEFT) {
            dx = -1;
        }
        if (key == KeyEvent.VK_RIGHT) {
            dx = 1;
        }
        if (key == KeyEvent.VK_UP) {
            dy = -1;
        }
        if (key == KeyEvent.VK_DOWN) {
            dy = 1;
        }
        if (dx != 0 || dy != 0) {
            gc.keyPressed(dy, dx);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
