package goldrush.vidmot.view;

import goldrush.app.View;
import goldrush.app.ViewSwitcher;
import goldrush.vidmot.controller.GoldController;
import javafx.fxml.FXML;

/**
 * Skjár sem útskýrir hvernig leikurinn virkar.
 */
public class Leikreglur {
    @FXML
    private GoldController goldController;

    /**
     * Setter fyrir goldController
     * @param goldController controller fyrir leikborðið
     */
    public void setGoldController(GoldController goldController) {
        this.goldController = goldController;
    }

    /**
     * Skiptir View yfir í síðasta View glugga
     */
    @FXML
    private void onHeim(){
        ViewSwitcher.switchTo(ViewSwitcher.getLastView());
        if (ViewSwitcher.getLastView() == View.LEIKBORD) {
            setGoldController(goldController);
            goldController.getLeikbord().stopOvinur();
        }
    }
}
