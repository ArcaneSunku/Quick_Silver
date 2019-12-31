package atomix.tiles;

import atomix.graphics.Sprite;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author ArcaneSunku
 * @since 12/31/2019
 */
public abstract class Tile {

    private Sprite m_Sprite;

    public Tile(BufferedImage image) {
        this(image, 32, 32);
    }

    public Tile(BufferedImage image, int width, int height) {
        this(image, 0, 0, width, height);
    }

    public Tile(BufferedImage image, int x, int y, int width, int height) {
        m_Sprite = new Sprite(image);

        m_Sprite.x = x;
        m_Sprite.y = y;
        m_Sprite.width = width;
        m_Sprite.height = height;
    }

    public abstract void update();

    public void render(Graphics2D g) {
        if(m_Sprite != null)
            m_Sprite.draw(g);
    }

    public void setSize(int width, int height) {
        m_Sprite.width = width;
        m_Sprite.height = height;
    }

    public void setPosition(float xPos, float yPos) {
        m_Sprite.x = xPos;
        m_Sprite.y = yPos;
    }

    public float getX() { return m_Sprite.x; }
    public float getY() { return m_Sprite.y; }

    public int getWidth() { return m_Sprite.width; }
    public int getHeight() { return m_Sprite.height; }

}
