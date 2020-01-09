package atomix.toolbox;

/**
 * This class handles how the Levels are displayed
 * along with how they will look when the camera needs
 * to "move"
 *
 * @author ArcaneSunku
 * @since 1/5/2020
 */
public class Camera {
    private float m_X, m_Y;

    public Camera() {
        this(0, 0);
    }

    public Camera(int x, int y) {
        m_X = x;
        m_Y = y;
    }

    public void move(float xOffs, float yOffs) {
        m_X += xOffs;
        m_Y += yOffs;
    }

    public float getX() { return m_X; }
    public float getY() { return m_Y; }

}
