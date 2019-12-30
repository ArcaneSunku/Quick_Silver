package atomix.handlers;

import atomix.screens.Screen;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles all the Screens we decide to add to the game.
 * This is set up so that if there are no screens, it won't even
 * think about rendering or updating.
 *
 * @author ArcaneSunku
 * @since 12/27/2019
 */
public class ScreenHandler {

    private static List<Screen> m_Screens;
    private static int m_Screen = -1;

    public ScreenHandler() {
        this(10);
    }

    public ScreenHandler(int initialCapacity) {
        m_Screens = new ArrayList<>(initialCapacity);
    }

    public void update() {
        Handler.update();

        if(m_Screen != -1)
            m_Screens.get(m_Screen).update();
    }

    public void render(Graphics2D g) {
        if(m_Screen != -1)
            m_Screens.get(m_Screen).render(g);
    }

    public void addScreen(Screen screen) {
        if(m_Screen < 0)
            m_Screen = 0;

        m_Screens.add(screen);
    }

    public void removeScreen() {
        if(m_Screen > 0) {
            m_Screens.remove(m_Screen);
            m_Screen--;
        } else if(m_Screen == 0) {
            m_Screens.remove(m_Screen);
            m_Screen = -1;
        }
    }

    public static void setScreen(int state) {
        setScreen(state, true);
    }

    public static void setScreen(int state, boolean initialize) {
        m_Screen = state;
        if(initialize)
            init();
    }

    private static void init() {
        if(m_Screen != -1)
            m_Screens.get(m_Screen).init();
    }

}
