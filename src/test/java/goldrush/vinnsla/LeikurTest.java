package goldrush.vinnsla;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LeikurTest {

    @Test
    void shouldHaveDefaultCharacterMario() {
        Leikur leikur = new Leikur();

        assertEquals("Mario", leikur.getSelectedCharacter());
    }

    @Test
    void shouldSetSelectedCharacter() {
        Leikur leikur = new Leikur();

        leikur.setSelectedCharacter("Luigi");

        assertEquals("Luigi", leikur.getSelectedCharacter());
    }

    @Test
    void shouldHaveDefaultDifficultyAudvelt() {
        Leikur leikur = new Leikur();

        assertEquals(Erfidleikastig.AUDVELT, leikur.getErfidleikastig());
    }

    @Test
    void shouldSetDifficulty() {
        Leikur leikur = new Leikur();

        leikur.setErfidleikastig(Erfidleikastig.ERFITT);

        assertEquals(Erfidleikastig.ERFITT, leikur.getErfidleikastig());
    }

    @Test
    void shouldAddPointsThroughLeikur() {
        Leikur leikur = new Leikur();

        leikur.baetaVidStigum(4);

        assertEquals(4, leikur.getStigin());
    }

    @Test
    void shouldResetScoreButKeepHighScore() {
        Leikur leikur = new Leikur();
        leikur.baetaVidStigum(9);

        leikur.endurstillaStig();

        assertEquals(0, leikur.getStigin());
        assertEquals(9, leikur.getHaestuStig());
    }

    @Test
    void shouldNotifyListenerThroughLeikur() {
        Leikur leikur = new Leikur();
        int[] observedScore = new int[1];
        int[] observedHighScore = new int[1];

        leikur.addStigaListener((stig, haestuStig) -> {
            observedScore[0] = stig;
            observedHighScore[0] = haestuStig;
        });

        leikur.baetaVidStigum(6);

        assertEquals(6, observedScore[0]);
        assertEquals(6, observedHighScore[0]);
    }
}
