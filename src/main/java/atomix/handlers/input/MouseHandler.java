package atomix.handlers.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * @author ArcaneSunku
 * @since 12/30/2019
 */
public class MouseHandler implements MouseListener, MouseMotionListener {

    public int m_X, m_Y;
    public boolean[] m_Buttons, m_CantPress, m_JustPressed;

    public MouseHandler() {
        m_Buttons = new boolean[10];
        m_CantPress = new boolean[m_Buttons.length];
        m_JustPressed = new boolean[m_Buttons.length];
    }

    public void update() {
        for(int i = 0; i < m_Buttons.length; i++) {
            if(m_CantPress[i] && !m_Buttons[i]) {
                m_CantPress[i] = false;
            } else if(m_JustPressed[i]) {
                m_CantPress[i] = true;
                m_JustPressed[i] = false;
            }

            if(!m_CantPress[i] && m_Buttons[i])
                m_JustPressed[i] = true;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        m_X = e.getX();
        m_Y = e.getY();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() < 0 || e.getButton() >= m_Buttons.length)
            return;

        m_Buttons[e.getButton()] = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton() < 0 || e.getButton() >= m_Buttons.length)
            return;

        m_Buttons[e.getButton()] = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        m_X = e.getX();
        m_Y = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        m_X = e.getX();
        m_Y = e.getY();
    }
}
