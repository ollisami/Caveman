package caveman;

import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
        GameController gameController = new GameController();
        gameController.createMap();
        gameController.rePaint();
    }

}
