package goldrush.vinnsla;

/**
 * Heldur utan um stodu leiksins.
 */
public class Leikur {
    private final Stigakerfi stigakerfi;
    private String selectedCharacter;
    private Erfidleikastig erfidleikastig;

    /**
     * Smiður sem byrjar leik i upphafsstodu.
     */
    public Leikur() {
        this.stigakerfi = new Stigakerfi();
        this.selectedCharacter = "Mario";
        this.erfidleikastig = Erfidleikastig.AUDVELT;
    }

    /**
     * Stillir valinn karakter fyrir leikinn.
     *
     * @param selectedCharacter nafn a völdum karakter
     */
    public void setSelectedCharacter(String selectedCharacter) {
        this.selectedCharacter = selectedCharacter;
    }

    /**
     * Skilar völdum karakter.
     *
     * @return valinn karakter
     */
    public String getSelectedCharacter() {
        return selectedCharacter;
    }

    /**
     * Stillir erfidleikastig leiksins.
     *
     * @param erfidleikastig erfidleikastig leiksins
     */
    public void setErfidleikastig(Erfidleikastig erfidleikastig) {
        this.erfidleikastig = erfidleikastig;
    }

    /**
     * Skilar erfidleikastigi leiksins.
     *
     * @return erfidleikastig leiksins
     */
    public Erfidleikastig getErfidleikastig() {
        return erfidleikastig;
    }

    /**
     * Baetir stigum vid nuverandi stigafjolda.
     *
     * @param stig stig sem a ad baeta vid
     */
    public void baetaVidStigum(int stig) {
        stigakerfi.addPoints(stig);
    }

    /**
     * Naer i nuverandi stigafjolda.
     *
     * @return nuverandi stigafjoldi
     */
    public int getStigin() {
        return stigakerfi.getCurrentScore();
    }

    /**
     * Naer i haesta stigafjolda.
     *
     * @return haestu stig
     */
    public int getHaestuStig() {
        return stigakerfi.getHighScore();
    }

    public void endurstillaStig() {
        stigakerfi.resetCurrentScore();
    }
}
