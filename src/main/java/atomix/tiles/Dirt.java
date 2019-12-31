package atomix.tiles;

import atomix.Assets;

import java.awt.image.BufferedImage;

/**
 * @author ArcaneSunku
 * @since 12/31/2019
 */
public class Dirt extends Tile {

    private static final BufferedImage IMAGE = Assets.getImage("Tiles").getSubimage(16, 0, 16, 16);

    public Dirt() {
        super(IMAGE);
    }

    public Dirt(int width, int height) {
        super(IMAGE, width, height);
    }

    public Dirt(int x, int y, int width, int height) {
        super(IMAGE, x, y, width, height);
    }

    @Override
    public void update() {

    }
}
