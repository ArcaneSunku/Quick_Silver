package atomix;

import atomix.graphics.Window;
import atomix.handlers.Handler;
import atomix.handlers.ScreenHandler;
import atomix.screens.AboutScreen;
import atomix.screens.InGameScreen;
import atomix.screens.TitleScreen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

/**
 * The Game class itself. This holds everything from the Thread
 * to even the Window. Most of the "engine" is probably going to
 * reside in here.
 *
 * @author ArcaneSunku
 * @since 12/27/2019
 */
public class Game implements Runnable {
    private final int WIDTH = 800, HEIGHT = WIDTH / 16 * 9;
    private static Handler handler = null;

    private ScreenHandler m_ScreenHandler;
    private Thread m_Thread;
    private Window m_Window;

    private int m_Frames, m_Ticks;
    private boolean m_Running;

    private String[] m_Screens;

    public Game() {
        m_Window = new Window("Quick Silver", WIDTH, HEIGHT);
        handler = new Handler(this, m_Window);
    }

    @Override
    public void run() {
        init();

        // We setup the variables for our timing
        int ticks = 0, frames = 0;
        long last_time = System.nanoTime();
        long timer = System.currentTimeMillis();

        double tick_limit = 60.0;
        double nanoseconds_per_tick = 1e9 / tick_limit;
        double delta = 0.0;

        // Helps us equalize the performance even on bad computers
        boolean should_render = false;

        while(m_Running) {
            if(m_Window.hasClosed() || m_Window.isClosing())
                stopRunning();

            long now = System.nanoTime();
            delta += (now - last_time) / nanoseconds_per_tick;
            last_time = now;

            while(delta >= 1) {
                update();
                should_render = true;

                ticks++;
                delta -= 1;
            }

            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(should_render) {
                render();
                frames++;
                should_render = false;
            }

            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                m_Frames = frames;
                m_Ticks = ticks;

                ticks = 0;
                frames = 0;
            }
        }

        stop();
    }

    public synchronized void start() {
        if(m_Running) return;

        m_Running = true;
        m_Thread = new Thread(this);
        m_Thread.start();
    }

    public synchronized void stop() {
        if(m_Running) m_Running = false;

        try {
            m_Window.dispose();
            m_Thread.join(1);
            System.exit(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private void init() {
        m_ScreenHandler = new ScreenHandler();
        Assets.initialize();

        m_ScreenHandler.addScreen(new TitleScreen());
        m_ScreenHandler.addScreen(new InGameScreen());
        m_ScreenHandler.addScreen(new AboutScreen());

        ScreenHandler.setScreen(0);

        m_Screens = new String[] {
                "Title",
                "In Game",
                "About"
        };

        m_Window.display();
    }

    private void update() {
        m_ScreenHandler.update();
        m_Window.getFrame().setTitle(m_Window.getTitle() + String.format(" | FPS: %d, TPF: %d | Screen: %s", m_Frames, m_Ticks, m_Screens[ScreenHandler.getScreen()]));
    }

    private void render() {
        BufferStrategy bufferStrategy = m_Window.getBufferStrategy();

        if(bufferStrategy == null) {
            m_Window.createBufferStrategy(3);
            return;
        }

        Graphics graphics = bufferStrategy.getDrawGraphics();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, m_Window.getWidth(), m_Window.getHeight());

        m_ScreenHandler.render((Graphics2D) graphics);

        graphics.dispose();
        bufferStrategy.show();
    }

    public void stopRunning() { m_Running = false; }

    public int getWidth() { return WIDTH; }
    public int getHeight() { return HEIGHT; }

}
