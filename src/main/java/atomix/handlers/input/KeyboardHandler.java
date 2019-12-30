package atomix.handlers.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author ArcaneSunku
 * @since 12/30/2019
 */
public class KeyboardHandler implements KeyListener {

    public boolean[] m_Keys, m_CantPress, m_JustPressed;

    public KeyboardHandler() {
        m_Keys = new boolean[256];
        m_CantPress = new boolean[m_Keys.length];
        m_JustPressed = new boolean[m_Keys.length];
    }

    public void update() {
        for(int i = 0; i < m_Keys.length; i++) {
            if(m_CantPress[i] && !m_Keys[i]) {
                m_CantPress[i] = false;
            } else if(m_JustPressed[i]) {
                m_CantPress[i] = true;
                m_JustPressed[i] = false;
            }

            if(!m_CantPress[i] && m_Keys[i])
                m_JustPressed[i] = true;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() < 0 || e.getKeyCode() >= m_Keys.length)
            return;

        m_Keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() < 0 || e.getKeyCode() >= m_Keys.length)
            return;

        m_Keys[e.getKeyCode()] = false;
    }
}
