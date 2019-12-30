package atomix.screens;

import atomix.handlers.Handler;
import atomix.handlers.ScreenHandler;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

/**
 * @author ArcaneSunku
 * @since 12/27/2019
 */
public class TitleScreen extends Screen {

    @Override
    public void init() {
        System.out.println("Title Initialized...");
    }

    @Override
    public void update() {
        if(Handler.keyJustPressed(KeyEvent.VK_SPACE)) {
            ScreenHandler.setScreen(1);
        } else if(Handler.keyJustPressed(KeyEvent.VK_BACK_SPACE)) {
            ScreenHandler.setScreen(2);
        }
    }

    @Override
    public void render(Graphics2D g) {

    }
}
