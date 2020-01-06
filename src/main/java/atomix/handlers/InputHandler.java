package atomix.handlers;

import atomix.graphics.Window;
import atomix.handlers.input.KeyboardHandler;
import atomix.handlers.input.MouseHandler;

/**
 * Handles everything Input related whether
 * it be from your Keyboard, Mouse, or even
 * a Controller.
 *
 * @author ArcaneSunku
 * @since 12/27/2019
 */
public class InputHandler {

    private KeyboardHandler m_Keyboard;
    private MouseHandler m_Mouse;

    public InputHandler(Window window) {
        m_Keyboard = new KeyboardHandler();
        m_Mouse = new MouseHandler();

        window.addKeyListener(m_Keyboard);
        window.addMouseListener(m_Mouse);
        window.addMouseMotionListener(m_Mouse);
        window.getFrame().addMouseListener(m_Mouse);
        window.getFrame().addMouseMotionListener(m_Mouse);
    }

    public void update() {
        m_Keyboard.update();
        m_Mouse.update();
    }

    public float getMouseX() { return m_Mouse.m_X; }
    public float getMouseY() { return m_Mouse.m_Y; }

    public boolean isKeyPressed(int key) { return m_Keyboard.m_Keys[key]; }
    public boolean keyJustPressed(int key) { return m_Keyboard.m_JustPressed[key]; }

    public boolean isButtonPressed(int button) { return m_Mouse.m_Buttons[button]; }
    public boolean buttonJustPressed(int button) { return m_Mouse.m_JustPressed[button]; }

}
