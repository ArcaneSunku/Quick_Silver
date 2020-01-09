package atomix.graphics;

import java.awt.image.BufferedImage;

/**
 * A collection of Sprites that would be used to
 * handle animations or even act as an Atlas.
 *
 * @author ArcaneSunku
 * @since 12/31/2019
 */
public class SpriteSheet {

    private Sprite[][] m_Sprites;
    private int m_Column, m_Row;

    public SpriteSheet(BufferedImage image, int cellWidth, int cellHeight) {
        int columns = image.getWidth() / cellWidth, rows = image.getHeight() / cellHeight;

        m_Sprites = new Sprite[columns][rows];
        m_Column = 0;
        m_Row = 0;

        for(int col = 0; col < columns; col++) {
            for(int row = 0; row < rows; row++) {
                BufferedImage sprite = image.getSubimage(col * cellWidth, row * cellHeight, cellWidth, cellHeight);
                m_Sprites[col][row] = new Sprite(sprite);
            }
        }
    }

    public void setSprite(int column, int row) {
        m_Column = column;
        m_Row = row;
    }

    public Sprite getCellAt(int column, int row) {
        return m_Sprites[column][row];
    }

    public Sprite getSprite() {
        return getCellAt(m_Column, m_Row);
    }

}
