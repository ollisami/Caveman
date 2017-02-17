/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caveman.gfx;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Paneli johon grafiikat päivittyvät.
 *
 * @version 1.0
 * @author Sami Ollila
 */
public class CanvasPanel extends JPanel {

    private SpriteSheet spriteSheet;
    private BufferedImage[][] imageMap;

    /**
     * Konstruktori.
     *
     */
    public CanvasPanel() {
        this.spriteSheet = new SpriteSheet("src/main/resources/images/sprites.png");
    }

    /**
     * Metodi luo BufferedImage[][], joka liittää pelaajan näkemän kartan ja
     * kuva id:t kuva taulukoksi.
     *
     */
    private void buildImageMap(int[][] mapView) {
        this.imageMap = new BufferedImage[mapView.length][mapView.length];
        for (int y = 0; y < mapView.length; y++) {
            for (int x = 0; x < mapView[y].length; x++) {
                this.imageMap[y][x] = this.spriteSheet.getSprite(mapView[y][x]);
            }
        }
    }

    /**
     * Piirtää / päivittää ruudulla näkyvän kartan.
     *
     * @param map kartta joka piirretään.
     */
    public void paintMap(int[][] map) {
        JPanel p = this;
        buildImageMap(map);
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        for (int y = 0; y < imageMap.length; y++) {
            for (int x = 0; x < imageMap.length; x++) {
                c.gridy = y;
                c.gridx = x;
                BufferedImage img = imageMap[y][x];
                panel.add(new JLabel(new ImageIcon(img)), c);
                //add(panel);
            }
        }
        p.removeAll();
        p.add(panel);
        super.revalidate();
    }

}
