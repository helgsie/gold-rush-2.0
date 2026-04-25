package goldrush.vinnsla;

/**
 * Heldur utan um timastodu leiksins.
 */
public class Klukka {
    private int currentTimeInSeconds;
    private boolean running;

    /**
     * Byrjar timatalningu fra 0 sekundum.
     */
    public void startCountUp() {
        currentTimeInSeconds = 0;
        running = true;
    }

    /**
     * Haekkar timann um eina sekundu ef klukkan er i gangi.
     */
    public void tick() {
        if (running) {
            currentTimeInSeconds++;
        }
    }

    /**
     * Stoppar timatalningu.
     */
    public void stopCountUp() {
        running = false;
    }

    /**
     * Skilar tima a forminu mm:ss.
     *
     * @return timi a strengjaformi
     */
    public String getFormattedTime() {
        int minutes = currentTimeInSeconds / 60;
        int seconds = currentTimeInSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}
