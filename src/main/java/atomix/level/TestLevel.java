package atomix.level;

import atomix.tiles.Dirt;
import atomix.tiles.Grass;
import atomix.tiles.Sand;
import atomix.tiles.Tile;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author ArcaneSunku
 * @since 1/4/2020
 */
public class TestLevel extends Level {

    public TestLevel(int tileSize) {
        super(tileSize);
    }

    @Override
    public void createLevel() {
        initLevel(100, 100);

        for(int i = 0; i < m_Width; i++) {
            for(int j = 0; j < m_Height; j++) {
                ThreadLocalRandom rand = ThreadLocalRandom.current();
                Tile t = null;

                switch(rand.nextInt(0, 3)) {
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