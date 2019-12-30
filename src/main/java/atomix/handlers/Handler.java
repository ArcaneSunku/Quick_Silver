package atomix.handlers;

import atomix.graphics.Window;

/**
 * @author ArcaneSunku
 * @since 12/27/2019
 */
public class Handler {

    private static Window m_Window;
    private static InputHandler m_Input;

    public Handler(Window window) {
        m_Window = window;
        m_Input = new InputHandler(m_Window);
    }

    public static void update() {
        m_Input.update();
    }

    public static int getWidth() { return m_Window.getWidth(); }
    public static int getHeight() { return m_Window.getHeight(); }

    public static float getMouseX() { return m_Input.getMouseX(); }
    public static float getMouseY() { return m_Input.getMouseY(); }

    public static boolean isKeyPressed(int key) { return m_Input.isKeyPressed(key); }
    public static boolean keyJustPressed(int key) { return m_Input.keyJustPressed(key); }

    public static boolean isButtonPressed(int button) { return m_Input.isButtonPressed(button); }
    public static boolean buttonJustPressed(int button) { return m_Input.buttonJustPressed(button); }

}
