package atomix.tiles;

import atomix.Assets;

import java.awt.image.BufferedImage;

/**
 * @author ArcaneSunku
 * @since 12/31/2019
 */
public class Sand extends Tile {

    private static final BufferedImage IMAGE = Assets.getImage("Tiles").getSubimage(32, 0, 16, 16);

    public Sand() {
        super(IMAGE);
    }

    public Sand(int width, int height) {
        super(IMAGE, width, height);
    }

    public Sand(int x, int y, int width, int height) {
        super(IMAGE, x, y, width, height);
    }

    @Override
    public void update() {

    }
}
