package caveman;

import gfx.SpriteSheet;
import java.io.File;
import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
        GameController gameController = new GameController();
        gameController.createMap();
        //gameController.printCurrentMap();
        SpriteSheet sheet = new SpriteSheet("src/main/resources/images/sprites.png");

        JFrame jframe = new JFrame();
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setSize(1000, 1000);
        jframe.setResizable(false);
        jframe.setVisible(true);
    }

}
