package atomix.screens;

import atomix.Assets;
import atomix.handlers.Handler;
import atomix.handlers.ScreenHandler;
import atomix.level.Level;
import atomix.level.TestLevel;
import atomix.toolbox.Timer;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * This is what displays everything that will happen
 * inside of our game. This is probably where a chunk
 * of the project is.
 *
 * @author ArcaneSunku
 * @since 12/30/2019
 */
public class InGameScreen extends Screen {

    private Timer m_Timer = null;
    private Level m_Test;

    @Override
    public void init() {
        if(m_Timer == null) {
            m_Timer = new Timer();
            m_Timer.start();
        } else {
            m_Timer.restart();
        }

        m_Test = new TestLevel(32);
        m_Test.createLevel();
    }

    @Override
    public void update() {
        m_Timer.update();

        if(Handler.keyJustPressed(KeyEvent.VK_ESCAPE))
            ScreenHandler.setScreen(0);
    }

    @Override
    public void render(Graphics2D g) {
        m_Test.draw(g);

        g.setFont(Assets.getFont("vcr", Font.BOLD, 16f));
        g.setColor(Color.WHITE);
        g.drawString(m_Timer.formattedString(2), 48, 48);
    }

}
