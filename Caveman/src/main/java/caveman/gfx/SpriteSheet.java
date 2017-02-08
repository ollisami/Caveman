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

    private BufferedImage getImage(int y, int x) {
        if (y < 0 || y > 10 || x < 0 || x > 10) {
            System.out.println("Incorrect values! x=" + x + " y = " + y);
            return null;
        }
        return sheet.getSubimage(x * 32 - 32, y * 32 - 32, 32, 32);
    }

    public BufferedImage getSprite(int i) {
        BufferedImage buff = null;
        switch (i) {
            case 0://Water
                buff = getImage(1, 3);
                break;
            case 1://floor
                buff = getImage(1, 4);
                break;
            case 2://wall
                buff = getImage(1, 5);
                break;
            case 3://player
                buff = getImage(1, 1);
                break;
            case 4://enemy
                buff = getImage(1, 2);
                break;
            default:
                break;
        }
        return buff;
    }
}
