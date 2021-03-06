package atomix.screens;

import atomix.Assets;
import atomix.handlers.Handler;
import atomix.handlers.ScreenHandler;
import atomix.level.AboutLevel;
import atomix.level.Level;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

/**
 * This is where the About portion of the game
 * will be held. Gives a little back story on why
 * I ended up working on this.
 *
 * @author ArcaneSunku
 * @since 12/30/2019
 */
public class AboutScreen extends Screen {

    private Color m_Color;
    private Level m_Backdrop;

    private String[] m_About;
    private String m_ToGoBack;

    private int m_Timer;

    @Override
    public void init() {
        m_Backdrop = new AboutLevel();
        m_About = new String[] {
                "Quick-Silver is a project developed by Tahnner Shambaugh(ArcaneSunku)\n",
                "in hopes of better understanding Java and it's inner functions.\n",
                "It was also created in hopes of helping my understanding\n",
                "of Game Engines as well."
        };

        m_ToGoBack = "Press BackSpace to return to Title.";
        m_Timer = 0;

        m_Color = Color.BLACK;
        m_Backdrop.createLevel();
    }

    @Override
    public void update() {
        m_Timer++;

        if(Handler.keyJustPressed(KeyEvent.VK_BACK_SPACE))
            ScreenHandler.setScreen(0);
    }

    @Override
    public void render(Graphics2D g) {
        m_Backdrop.draw(g);

        g.setColor(Color.WHITE);
        g.setFont(Assets.getFont("vcr", 20f));

        for(int i = 0; i < m_About.length; i++) {
            int fontHeight = g.getFontMetrics().getHeight();
            int lineWidth = g.getFontMetrics().stringWidth(m_About[i]);

            g.drawString(m_About[i], (Handler.getWidth() - lineWidth) / 2, Handler.getHeight() / 4 + (24 * i) + fontHeight);
        }

        g.setFont(Assets.getFont("vcr", Font.ITALIC | Font.BOLD, 20f));
        int lineWidth = g.getFontMetrics().stringWidth(m_ToGoBack);

        if(m_Timer % 60 == 0) {
            if (m_Color.equals(Color.BLACK))
                m_Color = Color.WHITE;
            else
                m_Color = Color.BLACK;
        }

        g.setColor(m_Color);
        g.drawString(m_ToGoBack, (Handler.getWidth() - lineWidth) / 2, Handler.getHeight() - g.getFontMetrics().getHeight() * 2);
    }

}
