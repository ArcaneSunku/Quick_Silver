package atomix.level;

import atomix.handlers.Handler;
import atomix.tiles.Tile;

import java.awt.*;

/**
 * Handles everything that a level will need.
 * This is bound to change as I learn and develop
 * new ways to handle these issues.
 *
 * @author ArcaneSunku
 * @since 1/4/2020
 */
public abstract class Level {

    /* TODO - Manage the Camera and how it works within each Level */

    protected Tile[][] m_Tiles;

    protected float m_X, m_Y;

    protected int m_Width, m_Height;
    protected int m_TileWidth, m_TileHeight;

    public Level(int tileSize) {
        m_X = 0;
        m_Y = 0;

        m_TileWidth = tileSize;
        m_TileHeight = tileSize;
    }

    /**
     * Is meant for manually creating a level by hard coding it.
     */
    public void createLevel() { }

    public abstract void update();

    public void draw(Graphics2D g) {
        if(m_Tiles != null) {
            for(int x = 0; x < m_Width; x++) {
                for(int y = 0; y < m_Height; y++) {
                    Tile t = m_Tiles[x][y];

                    if (t.getX() + m_TileWidth < 0 || t.getY() + m_TileHeight < 0 || t.getX() > Handler.getWidth() || t.getY() > Handler.getHeight())
                            continue;

                    t.render(g);
                }
            }
        }
    }

    protected void initLevel(int width, int height) {
        m_Width = width;
        m_Height = height;

        m_Tiles = new Tile[m_Width][m_Height];
    }

    public int getWidth() { return m_Width; }
    public int getHeight() { return m_Height; }

    public int getPixelWidth() { return m_Width * m_TileWidth; }
    public int getPixelHeight() { return m_Height * m_TileHeight; }

}
