package Game.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Elan Fernandez
 */
public class ImageLoader {
    public static BufferedImage loadImage(String path){
        try {
            return ImageIO.read(ImageLoader.class.getResource(path));
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
