package caveman.gfx;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

/**
 * Leikkaa kuvatiedoston osiin ja hallitsee osia.
 *
 * @version 1.0
 * @author Sami Ollila
 */
public class SpriteSheet {

    private BufferedImage sheet;
    BufferedImage[] cutImages;

    /**
     * Konstruktori.
     *
     */
    public SpriteSheet() {
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream("images/sprites.png");
            this.sheet = ImageIO.read(is);

        } catch (IOException e) {
            e.printStackTrace();
        }
        preCutImages();
    }

    /**
     * Leikkaa kuvatiedoston osiin
     */
    private void preCutImages() {
        this.cutImages = new BufferedImage[7];
        cutImages[0] = getImage(1, 3); //water
        cutImages[1] = getImage(1, 4); //floor
        cutImages[2] = getImage(1, 5); //wall
        cutImages[3] = getImage(1, 1); //player
        cutImages[4] = getImage(1, 2); //enemy
        cutImages[5] = getImage(1, 6); //Swamp
        cutImages[6] = getImage(1, 7); //Ladder
    }

    /**
     * Leikkaa kuva-arkista kuvan annetuista koordinaateista.
     *
     * @param y kuvan y koordinaatti
     * @param x kuvan x koordinaatti
     *
     * @return BufferedImage, leikattu kuva
     */
    private BufferedImage getImage(int y, int x) {
        if (y < 0 || y > 10 || x < 0 || x > 10) {
            System.out.println("Incorrect values! x=" + x + " y = " + y);
            return null;
        }
        return sheet.getSubimage(x * 32 - 32, y * 32 - 32, 32, 32);
    }

    /**
     * Hakee indeksillä kuvan taulukosta.
     *
     * @param i kuvan indeksi taulukossa
     *
     * @return kuva indeksissä, null jos kuvia ei jostain syystä ole.
     */
    public BufferedImage getSprite(int i) {
        if (cutImages.length == 0) {
            return null;
        }
        return this.cutImages[i];
    }
}
