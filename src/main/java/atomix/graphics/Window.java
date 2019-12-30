package atomix.graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * The encapsulation of everything we need for a usable Window.
 * This way we can tell when it's closing or closed.
 *
 * @author ArcaneSunku
 * @since 12/27/2019
 */
public class Window extends Canvas {

    private JFrame m_Frame;
    private WindowListener m_Listener;

    private String m_Title;
    private int m_Width, m_Height;

    private boolean m_Closing, m_Closed;

    public Window(String title, int width, int height) {
        m_Title = title;
        m_Width = width;
        m_Height = height;

        Dimension dimension = new Dimension(m_Width, m_Height);

        setMinimumSize(dimension);
        setMaximumSize(dimension);
        setPreferredSize(dimension);

        init();
    }

    public void display() {
        m_Frame.setVisible(true);
        m_Frame.requestFocus();

        setFocusable(true);
        requestFocus();
    }

    public void dispose() {
        m_Frame.dispose();
    }

    private void init() {
        m_Frame = new JFrame(m_Title);
        m_Listener = new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
                m_Closing = true;
            }

            @Override
            public void windowClosed(WindowEvent e) {
                m_Closed = true;
            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        };

        setFocusable(true);

        m_Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        m_Frame.setLayout(new BorderLayout());
        m_Frame.add(this, BorderLayout.CENTER);
        m_Frame.addWindowListener(m_Listener);
        m_Frame.pack();
        m_Frame.setResizable(false);
        m_Frame.setFocusable(true);
        m_Frame.setLocationRelativeTo(null);
    }

    public JFrame getFrame() { return m_Frame; }
    public String getTitle() { return m_Title; }

    public boolean hasClosed() { return m_Closed; }
    public boolean isClosing() { return m_Closing; }
}
