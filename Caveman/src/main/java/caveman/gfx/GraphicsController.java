package caveman.gfx;

import caveman.GameController;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import javax.swing.JPanel;

public class GraphicsController extends JPanel {

    private GameController gameController;
    private SpriteSheet spriteSheet;

    public GraphicsController(GameController gc) {
        this.gameController = gc;
        this.spriteSheet = new SpriteSheet("src/main/resources/images/sprites.png");

        setBackground(Color.BLACK);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        int[][] mapView = this.gameController.getPlayerView();
        g2d.drawImage(this.spriteSheet.getSprite(0, 0), 10, 10, null);
        for (int y = 0; y < mapView.length; y++) {
            for (int x = 0; x < mapView[y].length; x++) {
                
                //g2d.drawImage(this.spriteSheet.getSprite(0, 0), 10, 10, null);
            }
        }

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

}
