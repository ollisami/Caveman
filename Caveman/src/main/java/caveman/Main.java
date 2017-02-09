package caveman;

import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
        GameController gameController = new GameController();
        gameController.createMap();
        gameController.rePaint();
        JFrame jframe = new JFrame();
        jframe.addKeyListener(new IsKeyPressed(gameController));
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(gameController.getGraphicsController());
        jframe.setSize(1000, 1000);
        jframe.setResizable(false);
        jframe.setFocusable(true);
        jframe.setVisible(true);
    }

}
