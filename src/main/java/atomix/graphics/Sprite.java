package atomix.graphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

/**
 * @author ArcaneSunku
 * @since 12/27/2019
 */
public class Sprite {

    private Color m_TintColor;
    private BufferedImage m_Image, m_Filtered, m_Tint;

    public float x, y;
    public double angle;
    public int width, height;

    public Sprite(BufferedImage image) {
        m_Image = image;
        m_TintColor = new Color(0, 0, 0);
    }

    public void draw(Graphics2D g) {
        drawRotatedImage(g, m_Image, angle);

        if(m_Tint != null && !m_TintColor.equals(Color.BLACK))
            drawRotatedImage(g, m_Tint, angle);
    }

    public void tint(int red, int green, int blue) {
        if(m_Tint == null)
            m_Tint = new BufferedImage(m_Image.getWidth(), m_Image.getHeight(), BufferedImage.TRANSLUCENT);

        if(red != m_TintColor.getRed() || green != m_TintColor.getGreen() || blue != m_TintColor.getBlue()) {
            m_TintColor = new Color(red, green, blue);

            for (int x = 0; x < m_Image.getWidth(); x++) {
                for (int y = 0; y < m_Image.getHeight(); y++) {
                    Color pixels = new Color(m_Image.getRGB(x, y), true);
                    int r = (pixels.getRed() + m_TintColor.getRed()) / 2;
                    int g = (pixels.getGreen() + m_TintColor.getGreen()) / 2;
                    int b = (pixels.getBlue() + m_TintColor.getBlue()) / 2;
                    int a = pixels.getAlpha() / 2;

                    int rgba = (a << 24) | (r << 16) | (g << 8) | b;
                    m_Tint.setRGB(x, y, rgba);
                }
            }
        }
    }

    private void drawRotatedImage(Graphics2D og, BufferedImage img, double degree) {
        double angle = Math.toRadians(degree);
        Graphics2D imageGraphics = img.createGraphics();

        AffineTransform origin = imageGraphics.getTransform();
        AffineTransform transform = new AffineTransform();

        transform.scale(1, 1);
        transform.rotate(angle, x + (width / 2d), y + (height / 2d));

        AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        if(m_Filtered == null)
            m_Filtered = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);

        op.filter(img, m_Filtered);

        og.setTransform(transform);
        og.drawImage(img, (int) x, (int) y, width, height, null);
        og.setTransform(origin);
        imageGraphics.dispose();
    }

    public BufferedImage getImage() { return m_Image; }

    public BufferedImage getTintedImage() { return m_Tint; }

    public BufferedImage getFilteredImage() { return m_Filtered; }
}
