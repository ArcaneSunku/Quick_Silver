package atomix.toolbox;

/**
 * A Simple Timer. Nothing special will
 * probably be done with this for some time.
 *
 * @author ArcaneSunku
 * @since 12/27/2019
 */
public class Timer {

    private int m_Tick;
    private int m_Seconds, m_Minutes, m_Hours;

    private boolean m_Running;

    // initializes a stopped timer
    public Timer() {
        m_Seconds = 0;
        m_Minutes = 0;
        m_Hours = 0;

        m_Running = false;
    }

    /**
     * Starts up the timer, letting it know to count.
     */
    public void start() {
        m_Running = true;
    }

    /**
     * Stops the timer, letting it know it doesn't have to count.
     */
    public void stop() {
        m_Running = false;
    }

    /**
     * Stops the timer if it is running, then resets all parts of
     * the timer. After all that, it starts up again.
     */
    public void restart() {
        if(m_Running)
            stop();

        m_Seconds = 0;
        m_Minutes = 0;
        m_Hours = 0;

        start();
    }

    /**
     * Crucial. Has to be called in at least one method for
     * start or stop to work at all.
     */
    public void update() {
        if(m_Running){
            m_Tick++;

            if(m_Tick >= 60) {
                m_Seconds++;
                m_Tick = 0;
            }

            if(m_Seconds >= 60) {
                m_Minutes++;
                m_Seconds = 0;
            }

            if(m_Minutes >= 60) {
                m_Hours++;
                m_Minutes = 0;
            }
        }
    }

    public int seconds() { return m_Seconds; }
    public int minutes() { return m_Minutes; }
    public int hours() { return m_Hours;}

    private String twoDigitNumber(int num) {
        if(num < 10)
            return String.format("0%d", num);
        else
            return Integer.toString(num);
    }

    public String formattedString(int display) {
        String sec, min, hour;

        sec = twoDigitNumber(m_Seconds);
        min = twoDigitNumber(m_Minutes);
        hour = twoDigitNumber(m_Hours);

        switch(display) {
            case 1:
                return String.format("%s", sec);
            case 2:
                return String.format("%s:%s", min, sec);
            case 3:
                return String.format("%s:%s:%s", hour, min, sec);
        }

        return "NiL";
    }

    @Override
    public String toString() {
        return formattedString(3);
    }

}
