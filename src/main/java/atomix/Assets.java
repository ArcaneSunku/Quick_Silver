package atomix;

import atomix.toolbox.Cache;
import atomix.toolbox.Loader;

import java.awt.Font;
import java.awt.image.BufferedImage;

/**
 * @author ArcaneSunku
 * @since 12/27/2019
 */
public class Assets {

    private static Cache<String, BufferedImage> m_ImageCache;
    private static Cache<String, Font> m_FontCache;

    public static void initialize() {
        m_ImageCache = Cache.newInstance(15);

        m_ImageCache.putIfAbsent("Testing", Loader.loadImage("oc.png"));
        m_ImageCache.putIfAbsent("Vector", Loader.loadImage("vec.png"));

        m_ImageCache.putIfAbsent("Player", Loader.loadImage("stick.png"));
        m_ImageCache.putIfAbsent("Tiles", Loader.loadImage("tiles.png"));
        m_ImageCache.putIfAbsent("Logo", Loader.loadImage("logo.png"));

        m_FontCache = Cache.newInstance(4);

        m_FontCache.putIfAbsent("vcr", Loader.loadTTF("vcr.ttf"));
    }

    public static BufferedImage getImage(String name) { return m_ImageCache.get(name); }

    public static Font getFont(String name, float size) { return getFont(name, Font.PLAIN, size); }
    public static Font getFont(String name, int style, float size) { return m_FontCache.get(name).deriveFont(style, size); }

}
