package atomix.level;

import atomix.tiles.Dirt;
import atomix.tiles.Tile;

/**
 * The Level that we use to display the Dirt
 * "background" behind the About Screen.
 *
 * @author ArcaneSunku
 * @since 1/4/2020
 */
public class AboutLevel extends Level {

    public AboutLevel() {
        super(64);
    }

    @Override
    public void update() {

    }

    @Override
    public void createLevel() {
        initLevel(64, 64);

        for(int i = 0; i < m_Width; i++) {
            for(int j = 0; j < m_Height; j++) {
                Tile t = new Dirt(m_TileWidth, m_TileHeight);
                t.setPosition(i * t.getWidth(), j * t.getHeight());
                m_Tiles[i][j] = t;
            }
        }
    }

}
