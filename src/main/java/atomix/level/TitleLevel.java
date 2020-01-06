package atomix.level;

import atomix.tiles.Dirt;
import atomix.tiles.Grass;
import atomix.tiles.Sand;
import atomix.tiles.Tile;

import java.util.concurrent.ThreadLocalRandom;

/**
 * The Level that we use to display the tile
 * "background" behind the Title Screen.
 *
 * @author ArcaneSunku
 * @since 1/4/2020
 */
public class TitleLevel extends Level {

    public TitleLevel() {
        super(64);
    }

    @Override
    public void createLevel() {
        initLevel(64, 64);
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        int tile = rand.nextInt(0, 3);

        for(int i = 0; i < m_Width; i++) {
            for(int j = 0; j < m_Height; j++) {
                Tile t = null;

                switch(tile) {
                    case 0:
                        t = new Dirt(m_TileWidth, m_TileHeight);
                        t.setPosition(i * t.getWidth(), j * t.getHeight());
                        break;
                    case 1:
                        t = new Grass(m_TileWidth, m_TileHeight);
                        t.setPosition(i * t.getWidth(), j * t.getHeight());
                        break;
                    case 2:
                        t = new Sand(m_TileWidth, m_TileHeight);
                        t.setPosition(i * t.getWidth(), j * t.getHeight());
                        break;
                }

                m_Tiles[i][j] = t;
            }
        }
    }
}
