package goldrush.vidmot.controller;

import goldrush.app.View;
import goldrush.app.ViewSwitcher;
import goldrush.vinnsla.Erfidleikastig;
import goldrush.vinnsla.Leikur;

public class ErfidleikiController {

    private Leikur leikur;

    /**
     * Smiður til að setja upp leikborð
     */
    public ErfidleikiController() {
    }

    /**
     * Setur leik til ad deila milli controllera.
     * @param leikur leikur
     */
    public void setLeikur(Leikur leikur) {
        this.leikur = leikur;
        if (this.leikur != null) {
            this.leikur.setErfidleikastig(Erfidleikastig.AUDVELT);
        }
    }

    public void setErfidleikastig(Erfidleikastig erfidleikastig) {
        if (leikur != null) {
            leikur.setErfidleikastig(erfidleikastig);
        }
    }

    /**
     * Þegar ýtt er á "Tilbaka" hnappinn fer notandinn til baka á forsíðu
     */
    public void onTilbaka() {
        ViewSwitcher.switchTo(View.START);
    }

    /**
     * Ef notandinn velur "auðvelt" erfiðleikastig þá er óvinafjöldinn einn
     */
    public void onAudvelt() {
        setErfidleikastig(Erfidleikastig.AUDVELT);
        ViewSwitcher.switchTo(View.KARAKTER);
        KarakterController karakterController = (KarakterController) ViewSwitcher.lookup(View.KARAKTER);
        karakterController.setLeikur(leikur);
    }

    /**
     * Ef notandinn velur "miðlungs" erfiðleikastig þá er óvinafjöldinn tveir
     */
    public void onMidlungs() {
        setErfidleikastig(Erfidleikastig.MIDLUNGS);
        ViewSwitcher.switchTo(View.KARAKTER);
        KarakterController karakterController = (KarakterController) ViewSwitcher.lookup(View.KARAKTER);
        karakterController.setLeikur(leikur);
    }

    /**
     * Ef notandinn velur "erfitt" erfiðleikastig þá er óvinafjöldinn þrír
     */
    public void onErfitt() {
        setErfidleikastig(Erfidleikastig.ERFITT);
        ViewSwitcher.switchTo(View.KARAKTER);
        KarakterController karakterController = (KarakterController) ViewSwitcher.lookup(View.KARAKTER);
        karakterController.setLeikur(leikur);
    }

}
