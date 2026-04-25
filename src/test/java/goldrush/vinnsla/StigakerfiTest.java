package goldrush.vinnsla;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StigakerfiTest {

    @Test
    void shouldStartWithZeroScoreAndZeroHighScore() {
        Stigakerfi stigakerfi = new Stigakerfi();

        assertEquals(0, stigakerfi.getCurrentScore());
        assertEquals(0, stigakerfi.getHighScore());
    }

    @Test
    void shouldAddPointsToCurrentScore() {
        Stigakerfi stigakerfi = new Stigakerfi();

        stigakerfi.addPoints(5);

        assertEquals(5, stigakerfi.getCurrentScore());
    }

    @Test
    void shouldUpdateHighScoreWhenCurrentScoreIsHigher() {
        Stigakerfi stigakerfi = new Stigakerfi();

        stigakerfi.addPoints(10);

        assertEquals(10, stigakerfi.getHighScore());
    }

    @Test
    void shouldResetCurrentScoreButKeepHighScore() {
        Stigakerfi stigakerfi = new Stigakerfi();
        stigakerfi.addPoints(10);

        stigakerfi.resetCurrentScore();

        assertEquals(0, stigakerfi.getCurrentScore());
        assertEquals(10, stigakerfi.getHighScore());
    }

    @Test
    void shouldNotifyListenerWhenScoreChanges() {
        Stigakerfi stigakerfi = new Stigakerfi();
        int[] observedScore = new int[1];
        int[] observedHighScore = new int[1];

        stigakerfi.addListener((stig, haestuStig) -> {
            observedScore[0] = stig;
            observedHighScore[0] = haestuStig;
        });

        stigakerfi.addPoints(7);

        assertEquals(7, observedScore[0]);
        assertEquals(7, observedHighScore[0]);
    }

    @Test
    void shouldNotNotifyRemovedListener() {
        Stigakerfi stigakerfi = new Stigakerfi();
        int[] notificationCount = new int[1];
        StigaListener listener = (stig, haestuStig) -> notificationCount[0]++;

        stigakerfi.addListener(listener);
        stigakerfi.removeListener(listener);
        stigakerfi.addPoints(3);

        assertEquals(0, notificationCount[0]);
    }
}
