package atomix.screens;

import java.awt.*;

/**
 * The skeleton for a Screen.
 * Has everything a "Screen" of the game will
 * need to be handled, swapped between, etc.
 *
 * @author ArcaneSunku
 * @since 12/27/2019
 */
public abstract class Screen {

    public abstract void init();
    public abstract void update();
    public abstract void render(Graphics2D g);

}
