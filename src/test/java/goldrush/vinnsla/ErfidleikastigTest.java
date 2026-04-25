package goldrush.vinnsla;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ErfidleikastigTest {

    @Test
    void shouldReturnCorrectEnemyCountForAudvelt() {
        assertEquals(1, Erfidleikastig.AUDVELT.getFjoldiOvina());
    }

    @Test
    void shouldReturnCorrectEnemyCountForMidlungs() {
        assertEquals(2, Erfidleikastig.MIDLUNGS.getFjoldiOvina());
    }

    @Test
    void shouldReturnCorrectEnemyCountForErfitt() {
        assertEquals(3, Erfidleikastig.ERFITT.getFjoldiOvina());
    }
}
