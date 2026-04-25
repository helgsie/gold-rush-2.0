package goldrush.vidmot.controller;

import goldrush.app.View;
import goldrush.app.ViewSwitcher;
import goldrush.vinnsla.Leikur;

/**
 * Controller fyrir skjáinn sem sýnir karakterval.
 */
public class KarakterController {
    /**
     * Smiður fyrir JavaFX controller.
     */
    public KarakterController() {
    }

    private Leikur leikur;

    /**
     * Setur leik til ad deila milli controllera.
     * @param leikur leikur
     */
    public void setLeikur(Leikur leikur) {
        this.leikur = leikur;
    }

    /**
     * Velur Mario og fer i leikinn
     */
    public void onMario() {
        hefjaLeikMedKarakter("Mario");
    }

    /**
     * Velur Luigi og fer i leikinn
     */
    public void onLuigi() {
        hefjaLeikMedKarakter("Luigi");
    }

    /**
     * Velur Peach og fer i leikinn
     */
    public void onPeach() {
        hefjaLeikMedKarakter("Peach");
    }

    /**
     * Velur Daisy og fer i leikinn
     */
    public void onDaisy() {
        hefjaLeikMedKarakter("Daisy");
    }

    /**
     * Stillir valinn karakter, opnar leikskja og byrjar leikinn.
     *
     * @param karakter valinn karakter
     */
    private void hefjaLeikMedKarakter(String karakter) {
        if (leikur == null) {
            leikur = new Leikur();
        }

        leikur.setSelectedCharacter(karakter);
        ViewSwitcher.switchTo(View.LEIKBORD);

        GoldController goldController = (GoldController) ViewSwitcher.lookup(View.LEIKBORD);
        goldController.setLeikur(leikur);
        goldController.getLeikbord().hefjaAfram();
    }

    /**
     * Fer til baka i erfidleikaval
     */
    public void onTilBaka() {
        ViewSwitcher.switchTo(View.ERFIDLEIKI);
    }
}
