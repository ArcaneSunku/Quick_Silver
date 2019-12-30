package atomix.toolbox;

/**
 * @author ArcaneSunku
 * @since 12/27/2019
 */
public class Clock {

    private int m_Seconds, m_Minutes, m_Hours;
    private boolean m_Running;

    public Clock() {
        m_Seconds = 0;
        m_Minutes = 0;
        m_Hours = 0;

        m_Running = false;
    }

    public void start() {
        m_Running = true;
    }

    public void stop() {
        m_Running = false;
    }

    public void update() {
        if(m_Running){
            m_Seconds++;

            if(m_Seconds % 60 == 0) {
                m_Minutes++;
                m_Seconds = 0;
            }

            if(m_Minutes % 60 == 0) {
                m_Hours++;
            }
        }
    }

    public int seconds() { return m_Seconds; }
    public int minutes() { return m_Minutes; }
    public int hours() { return m_Hours;}

}
