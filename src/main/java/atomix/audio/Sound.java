package atomix.audio;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound implements Runnable {

    private AudioInputStream inputStream;
    private String soundFile;
    private Clip soundClip;

    private Thread soundThread;

    private boolean running = false;
    private boolean playSong = false;

    public Sound() {
        this.start();
    }

    public void start() {
        if (running)
            return;

        soundThread = new Thread(this);
        running = true;
        soundThread.start();
    }

    @Override
    public void run() {
        while (running) {
            if (inputStream == null && playSong) {
                playSong = false;

                try {
                    inputStream = AudioSystem.getAudioInputStream(Sound.class.getResourceAsStream("audio/" + soundFile));
                    soundClip.open(inputStream);
                    soundClip.loop(10);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void playSound(String string) {
        if (soundClip != null) {
            soundClip.stop();
            soundClip.close();
        }
        try {
            soundClip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

        soundFile = string + ".wav";
        playSong = true;
        inputStream = null;
    }

    public void disposeSound() {
        if (soundClip != null) {
            soundClip.stop();
            soundClip.close();
        }

        soundClip = null;
        playSong = false;
        inputStream = null;
    }

    public boolean playing() { return playSong; }
}