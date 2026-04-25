package goldrush.vinnsla;

/**
 * Moguleg erfidleikastig i leiknum.
 */
public enum Erfidleikastig {
    /** Auðvelt stig - einn óvinur. */
    AUDVELT(1),
    /** Miðlungs stig - tveir óvinar. */
    MIDLUNGS(2),
    /** Erfitt stig - þrír óvinar. */
    ERFITT(3);

    private final int fjoldiOvina;

    /**
     * Smiður fyrir erfidleikastig.
     *
     * @param fjoldiOvina fjoldi ovina sem tilheyrir erfidleikastiginu
     */
    Erfidleikastig(int fjoldiOvina) {
        this.fjoldiOvina = fjoldiOvina;
    }

    /**
     * Skilar fjolda ovina fyrir erfidleikastigid.
     *
     * @return fjoldi ovina
     */
    public int getFjoldiOvina() {
        return fjoldiOvina;
    }
}
