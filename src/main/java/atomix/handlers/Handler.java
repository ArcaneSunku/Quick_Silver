package atomix.handlers;

import atomix.Game;
import atomix.graphics.Window;

/**
 * The "Hub" for a lot of methods that will be used
 * throughout the whole project. This is bound to change
 * as I add more.
 *
 * @author ArcaneSunku
 * @since 12/27/2019
 */
public class Handler {

    private static Game m_Game;
    private static Window m_Window;
    private static InputHandler m_Input;

    public Handler(Game game, Window window) {
        m_Game = game;
        m_Window = window;
        m_Input = new InputHandler(m_Window);
    }

    public static void update() {
        m_Input.update();
    }

    public static void stopRunning() { m_Game.stopRunning(); }

    public static int getWidth() { return m_Window.getWidth(); }
    public static int getHeight() { return m_Window.getHeight(); }

    public static float getMouseX() { return m_Input.getMouseX(); }
    public static float getMouseY() { return m_Input.getMouseY(); }

    public static boolean isKeyPressed(int key) { return m_Input.isKeyPressed(key); }
    public static boolean keyJustPressed(int key) { return m_Input.keyJustPressed(key); }

    public static boolean isButtonPressed(int button) { return m_Input.isButtonPressed(button); }
    public static boolean buttonJustPressed(int button) { return m_Input.buttonJustPressed(button); }

}
