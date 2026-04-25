package goldrush.vinnsla;

/**
 * Heldur utan um stig og haesta stigafjolda i leiknum.
 */
public class Stigakerfi {
    private int currentScore;
    private int highScore;

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
    }

    /**
     * Endurstillir nuverandi stigafjolda.
     */
    public void resetCurrentScore() {
        currentScore = 0;
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
}
