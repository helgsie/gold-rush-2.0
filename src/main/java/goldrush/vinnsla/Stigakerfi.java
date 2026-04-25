package goldrush.vinnsla;

import java.util.ArrayList;
import java.util.List;

/**
 * Heldur utan um stig og haesta stigafjolda i leiknum.
 */
public class Stigakerfi {
    private int currentScore;
    private int highScore;
    private final List<StigaListener> listeners = new ArrayList<>();

    /**
     * Baetir stigum vid nuverandi stigafjolda og uppfaerir haestu stig ef tharf.
     *
     * @param points stig sem a ad baeta vid
     */
    public void addPoints(int points) {
        currentScore += points;

        if (currentScore > highScore) {
            highScore = currentScore;
        }

        notifyListeners();
    }

    /**
     * Endurstillir nuverandi stigafjolda.
     */
    public void resetCurrentScore() {
        currentScore = 0;
        notifyListeners();
    }

    /**
     * Skilar nuverandi stigafjolda.
     *
     * @return nuverandi stig
     */
    public int getCurrentScore() {
        return currentScore;
    }

    /**
     * Skilar haesta stigafjolda.
     *
     * @return haestu stig
     */
    public int getHighScore() {
        return highScore;
    }

    /**
     * Baetir vid observer sem faer skilabod thegar stig breytast.
     *
     * @param listener listener sem a ad baeta vid
     */
    public void addListener(StigaListener listener) {
        listeners.add(listener);
    }

    /**
     * Fjarlaegir observer ur lista.
     *
     * @param listener listener sem a ad fjarlaegja
     */
    public void removeListener(StigaListener listener) {
        listeners.remove(listener);
    }

    /**
     * Laetur alla observera vita ad stig hafi breyst.
     */
    private void notifyListeners() {
        for (StigaListener listener : listeners) {
            listener.stigBreytt(currentScore, highScore);
        }
    }
}
