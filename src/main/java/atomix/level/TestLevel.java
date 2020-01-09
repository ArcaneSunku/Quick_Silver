package atomix.level;

import atomix.tiles.Dirt;
import atomix.tiles.Grass;
import atomix.tiles.Sand;
import atomix.tiles.Tile;
import atomix.toolbox.Camera;

import java.util.concurrent.ThreadLocalRandom;

/**
 * This is a temporary level, used for testing and so we
 * can see some sort of graphics within the InGame Screen.
 *
 * @author ArcaneSunku
 * @since 1/4/2020
 */
public class TestLevel extends Level {

    private Camera m_Camera;

    public TestLevel(int tileSize) {
        super(tileSize);
        m_Camera = new Camera();
    }

    @Override
    public void update() {
        for(int x = 0; x < m_Width; x++) {
            for(int y = 0; y < m_Height; y++) {
                m_Tiles[x][y].setX(m_Tiles[x][y].getX() + m_Camera.getX());
                m_Tiles[x][y].setY(m_Tiles[x][y].getY() + m_Camera.getY());
            }
        }
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

    public Camera getCamera() {
        return m_Camera;
    }
}
