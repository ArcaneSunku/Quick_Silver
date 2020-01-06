package atomix.screens;

import atomix.handlers.Handler;
import atomix.handlers.ScreenHandler;
import atomix.level.Level;
import atomix.level.TestLevel;

import java.awt.Graphics2D;
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

    private Level m_Test;

    @Override
    public void init() {
        m_Test = new TestLevel(32);
        m_Test.createLevel();
    }

    @Override
    public void update() {
        if(Handler.keyJustPressed(KeyEvent.VK_ESCAPE))
            ScreenHandler.setScreen(0);
    }

    @Override
    public void render(Graphics2D g) {
        m_Test.draw(g);
    }

}
