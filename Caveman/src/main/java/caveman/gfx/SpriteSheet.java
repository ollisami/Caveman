package caveman.gfx;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SpriteSheet {

    private BufferedImage sheet;
    BufferedImage[] cutImages;

    public SpriteSheet(String path) {
        try {
//            // Retrieve Image
            this.sheet = ImageIO.read(new File(path));

        } catch (IOException e) {
            e.printStackTrace();
        }
        cutSprite();
    }
    
    private void cutSprite() {
        this.cutImages = new BufferedImage[5];
        cutImages[0] = getImage(1, 3);
        cutImages[1] = getImage(1, 4);
        cutImages[2] = getImage(1, 5);
        cutImages[3] = getImage(1, 1);
        cutImages[4] = getImage(1, 2);
    }

    private BufferedImage getImage(int y, int x) {
        if (y < 0 || y > 10 || x < 0 || x > 10) {
            System.out.println("Incorrect values! x=" + x + " y = " + y);
            return null;
        }
        return sheet.getSubimage(x * 32 - 32, y * 32 - 32, 32, 32);
    }

    public BufferedImage getSprite(int i) {
        return this.cutImages[i];
    }
}
