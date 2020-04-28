package atomix.screens;

import atomix.Assets;
import atomix.graphics.Sprite;
import atomix.handlers.Handler;
import atomix.handlers.ScreenHandler;
import atomix.level.Level;
import atomix.level.TitleLevel;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * This is what displays when we are on the
 * Title of the game.
 *
 * @author ArcaneSunku
 * @since 12/27/2019
 */
public class TitleScreen extends Screen {

    private final String PRESS_START = "Press Space to play!";

    private Sprite m_Logo;
    private Level m_Backdrop;

    private float m_startX, m_startY;
    private boolean m_Falling, m_Spinning, m_DrawStartText;

    @Override
    public void init() {
        m_Logo = new Sprite(Assets.getImage("Logo"));
        m_Backdrop = new TitleLevel();
        m_Backdrop.createLevel();

        m_Logo.angle = 0;
        m_Logo.width = m_Logo.getImage().getWidth() * 2;
        m_Logo.height = m_Logo.getImage().getHeight() * 2;

        m_Logo.x = (Handler.getWidth() - m_Logo.width) / 2f;
        m_Logo.y = -m_Logo.height * 2;

        m_startX = 0;
        m_startY = ((Handler.getHeight() - m_Logo.height) / 2f) + m_Logo.height + 24f;

        m_Falling = true;
        m_Spinning = true;
        m_DrawStartText = false;
    }

    @Override
    public void update() {
        if(m_Logo.y > 0 && m_Spinning) {
            if (Math.abs(m_Logo.angle) >= 360) {
                m_Logo.angle = 0;
                m_Spinning = false;
                return;
            }

            m_Logo.angle -= 8;
        }

        if(m_Logo.y > (Handler.getHeight() - m_Logo.height) / 2f) {
            m_Logo.y = (Handler.getHeight() - m_Logo.height) / 2f;
            m_Falling = false;
        }

        if(!m_Falling) {
            if(Handler.keyJustPressed(KeyEvent.VK_ESCAPE))
                Handler.stopRunning();

            if (Handler.keyJustPressed(KeyEvent.VK_SPACE))
                ScreenHandler.setScreen(1);

            if (Handler.keyJustPressed(KeyEvent.VK_BACK_SPACE))
                ScreenHandler.setScreen(2);

            m_DrawStartText = true;
        } else {
            m_Logo.y += 2.25;

            if (Handler.keyJustPressed(KeyEvent.VK_SPACE)) {
                if(m_Spinning) {
                    m_Logo.angle = 0;
                    m_Spinning = false;
                }

                m_Logo.y = (Handler.getHeight() - m_Logo.height) / 2f;
            }
        }
    }

    @Override
    public void render(Graphics2D g) {
        g.setFont(Assets.getFont("vcr", Font.BOLD, 24f));

        if(m_startX == 0)
            m_startX = (Handler.getWidth() - g.getFontMetrics().stringWidth(PRESS_START)) / 2f;

        m_Backdrop.draw(g);
        m_Logo.draw(g);

        if(m_DrawStartText) {
            g.setColor(Color.CYAN);
            g.drawString(PRESS_START, m_startX, m_startY);
        }
    }
}
