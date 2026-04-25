package goldrush.vinnsla;

/**
 * Moguleg erfidleikastig i leiknum.
 */
public enum Erfidleikastig {
    AUDVELT(1),
    MIDLUNGS(2),
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
