package atomix.screens;

import atomix.Assets;
import atomix.graphics.Sprite;
import atomix.handlers.Handler;
import atomix.tiles.Dirt;
import atomix.tiles.Grass;
import atomix.tiles.Sand;
import atomix.tiles.Tile;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author ArcaneSunku
 * @since 12/30/2019
 */
public class InGameScreen extends Screen {

    private final Tile GRASS = new Grass(), DIRT = new Dirt(), SAND = new Sand();
    private final int MAP_WIDTH = 32, MAP_HEIGHT = 32;

    private Tile[][] m_Map;

    private Sprite m_Player;

    @Override
    public void init() {
        m_Map = new Tile[MAP_WIDTH][MAP_HEIGHT];

        for(int x = 0; x < MAP_WIDTH; x++) {
            for(int y = 0; y < MAP_HEIGHT; y++) {
                ThreadLocalRandom rand = ThreadLocalRandom.current();
                int tile = rand.nextInt(0, 200);
                Tile t;

                if(tile % 100 == 0 || tile % 122 == 0) {
                    t = new Sand();
                } else if(tile % 50 == 0 || tile % 20 == 0) {
                    t = new Dirt();
                } else {
                    t = new Grass();
                }

                t.setSize(32, 32);
                t.setPosition(x * t.getWidth(), y * t.getHeight());

                m_Map[x][y] = t;
            }
        }

        m_Player = new Sprite(Assets.getImage("Vector"));
        m_Player.x = 0;
        m_Player.y = 0;

        m_Player.angle = 0;
        m_Player.width = 64;
        m_Player.height = 64;

        m_Player.tint(0, 0, 255 / 2);
    }

    @Override
    public void update() {
        if(Handler.isKeyPressed(KeyEvent.VK_RIGHT))
            m_Player.angle+=2.5;
        else if(Handler.isKeyPressed(KeyEvent.VK_LEFT))
            m_Player.angle-=2.5;

        if(Handler.isKeyPressed(KeyEvent.VK_W))
            m_Player.y -= 3.25;
        else if(Handler.isKeyPressed(KeyEvent.VK_S))
            m_Player.y += 3.25;

        if(Handler.isKeyPressed(KeyEvent.VK_A))
            m_Player.x -= 3.25;
        else if(Handler.isKeyPressed(KeyEvent.VK_D))
            m_Player.x += 3.25;

        for(int y = 0; y < MAP_HEIGHT; y++) {
            for(int x = 0; x < MAP_WIDTH; x++) {
                if(m_Map[x][y].getX() > Handler.getWidth() || m_Map[x][y].getY() > Handler.getHeight())
                    continue;

                m_Map[x][y].update();
            }
        }
    }

    @Override
    public void render(Graphics2D g) {
        for(int y = 0; y < MAP_HEIGHT; y++) {
            for(int x = 0; x < MAP_WIDTH; x++) {
                if(m_Map[x][y].getX() > Handler.getWidth() || m_Map[x][y].getY() > Handler.getHeight())
                    continue;

                m_Map[x][y].render(g);
            }
        }

        m_Player.draw(g);
    }

}
