package goldrush.vinnsla;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KlukkaTest {

    @Test
    void shouldStartAtZeroSeconds() {
        Klukka klukka = new Klukka();

        assertEquals("00:00", klukka.getFormattedTime());
    }

    @Test
    void shouldIncrementTimeWhenRunning() {
        Klukka klukka = new Klukka();

        klukka.startCountUp();
        klukka.tick();
        klukka.tick();

        assertEquals("00:02", klukka.getFormattedTime());
    }

    @Test
    void shouldNotIncrementWhenStopped() {
        Klukka klukka = new Klukka();

        klukka.startCountUp();
        klukka.stopCountUp();
        klukka.tick();

        assertEquals("00:00", klukka.getFormattedTime());
    }

    @Test
    void shouldFormatTimeCorrectly() {
        Klukka klukka = new Klukka();

        klukka.startCountUp();
        for (int i = 0; i < 65; i++) {
            klukka.tick();
        }

        assertEquals("01:05", klukka.getFormattedTime());
    }
}
