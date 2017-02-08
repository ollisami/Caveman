package caveman.gfx;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SpriteSheet {

    private BufferedImage sheet;

    public SpriteSheet(String path) {
        try {
//            // Retrieve Image
            this.sheet = ImageIO.read(new File(path));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getSprite(int x, int y) {
        return sheet.getSubimage(x * 32 - 32, y * 32 - 32, 32, 32);
    }
}
