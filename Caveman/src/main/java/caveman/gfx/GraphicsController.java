package caveman.gfx;

import caveman.GameController;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GraphicsController extends JPanel {

    private GameController gameController;
    private SpriteSheet spriteSheet;
    private BufferedImage[][] imageMap;
    private JPanel prev;

    public GraphicsController(GameController gc) {
        this.gameController = gc;
        this.spriteSheet = new SpriteSheet("src/main/resources/images/sprites.png");
    }

    private void buildImageMap() {
        int[][] mapView = this.gameController.getPlayerView();
        this.imageMap = new BufferedImage[mapView.length][mapView.length];

        for (int y = 0; y < mapView.length; y++) {
            for (int x = 0; x < mapView[y].length; x++) {
                this.imageMap[y][x] = this.spriteSheet.getSprite(mapView[y][x]);
            }
        }
    }
    
    public void paintMap () {
        JPanel p = this;
        buildImageMap();
        // Draw the grid
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        for (int y = 0; y < imageMap.length; y++) {
            for (int x = 0; x < imageMap.length; x++) {
                c.gridy = y;
                c.gridx = x;
                BufferedImage img = imageMap[y][x];
                panel.add(new JLabel(new ImageIcon(img)), c);
                add(panel);
            }
        }
        prev = panel;
        if (p.getComponents().length > 1) {
            p.removeAll();
        }
        p.add(panel);
        super.revalidate();
    }

}
