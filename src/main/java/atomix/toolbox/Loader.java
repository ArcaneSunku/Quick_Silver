package atomix.toolbox;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Loads images in and that's about it.
 *
 * @author ArcaneSunku
 * @since 12/27/2019
 */
public class Loader {

    public static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(Loader.class.getResourceAsStream("/textures/" + path));
        } catch (IOException e) {
            System.err.printf("ERROR::GRAPHICS::Images Failed to load [%s]!%n", path);
            return null;
        }
    }

    public static Font loadTTF(String fontName) {
        return loadFont(fontName, true);
    }

    public static Font loadFont(String fontName, boolean ttf) {
        Font temp;

        try {
            if(ttf)
                temp = Font.createFont(Font.TRUETYPE_FONT, Loader.class.getResourceAsStream("/fonts/" + fontName));
            else
                temp = Font.createFont(Font.TYPE1_FONT, Loader.class.getResourceAsStream("/fonts/" + fontName));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            System.err.printf("ERROR::GRAPHICS::Fonts Failed to load [%s]!%n", fontName);
            temp = null;
        }

        return temp;
    }

}
