package atomix.tiles;

import atomix.graphics.Sprite;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Everything a Tile will need to be used
 * within a Level. This is definitely going to change
 * in the future.
 *
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
        setWidth(width);
        setHeight(height);
    }

    public void setPosition(float xPos, float yPos) {
        setX(xPos);
        setY(yPos);
    }

    public void setX(float x) {
        m_Sprite.x = x;
    }

    public void setY(float y) {
        m_Sprite.y = y;
    }

    public void setWidth(int width) {
        m_Sprite.width = width;
    }

    public void setHeight(int height) {
        m_Sprite.height = height;
    }

    public float getX() { return m_Sprite.x; }
    public float getY() { return m_Sprite.y; }

    public int getWidth() { return m_Sprite.width; }
    public int getHeight() { return m_Sprite.height; }

}
