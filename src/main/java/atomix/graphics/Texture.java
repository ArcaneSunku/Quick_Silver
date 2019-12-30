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
public class Texture {

    private BufferedImage m_Image;

    public float x, y;
    public double angle;
    public int width, height;

    public Texture(BufferedImage image) {
        m_Image = image;
    }

    public void draw(Graphics2D g) {
        draw(g, 0, 0, 0);
    }

    public void draw(Graphics2D g, int red, int green, int blue) {
        rotate(g, m_Image, angle, (int) x, (int) y, width, height);
        g.drawImage(tint(red, green, blue), (int) x, (int) y, width, height, null);
    }

    private BufferedImage tint(int red, int green, int blue) {
        BufferedImage img = new BufferedImage(m_Image.getWidth(), m_Image.getHeight(), BufferedImage.TRANSLUCENT);
        Color color = new Color(red, green, blue);

        for(int x = 0; x < m_Image.getWidth(); x++) {
            for(int y = 0; y < m_Image.getHeight(); y++) {
                Color pixels = new Color(m_Image.getRGB(x, y), true);
                int r = (pixels.getRed() + color.getRed()) / 2;
                int g = (pixels.getGreen() + color.getGreen()) / 2;
                int b = (pixels.getBlue() + color.getBlue()) / 2;
                int a = pixels.getAlpha() / 2;

                int rgba = (a << 24) | (r << 16) | (g << 8) | b;
                img.setRGB(x, y, rgba);
            }
        }

        return img;
    }

    private void rotate(Graphics2D og, BufferedImage img, double degree, int x, int y, int width, int height) {
        double angle = Math.toRadians(degree);
        Graphics2D g = img.createGraphics();

        AffineTransform origin = g.getTransform();
        AffineTransform transform = new AffineTransform();

        transform.scale(1, 1);
        transform.rotate(angle, x + (width / 2d), y + (height / 2d));

        AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        BufferedImage filtered = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
        op.filter(img, filtered);

        og.setTransform(transform);
        og.drawImage(img, x, y, width, height, null);
        og.setTransform(origin);
        g.dispose();
    }

    public BufferedImage getImage() { return m_Image; }

}
