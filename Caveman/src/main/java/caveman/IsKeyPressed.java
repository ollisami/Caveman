/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caveman;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

/**
 *
 * @author ollisami
 */
public class IsKeyPressed implements KeyListener {

    private GameController gc;

    public IsKeyPressed(GameController gc) {
        this.gc = gc;
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

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
        gc.keyPressed(dy,dx);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

//    public static void main(String[] s) {
//        JFrame f = new JFrame();
//        f.getContentPane().add(new MyPanel());
//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        f.pack();
//        f.setVisible(true);
//    }
}
