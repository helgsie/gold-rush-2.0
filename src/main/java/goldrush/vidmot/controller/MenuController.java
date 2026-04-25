package goldrush.vidmot.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import goldrush.app.View;
import goldrush.app.ViewSwitcher;

import java.util.Objects;
/**
 * Klasinn sér um menubar fyrir ofan leikborðið
 */
public class MenuController {
    public MenuBar menuBar;

    @FXML
    private GoldController goldController;
    /**
     * GoldController stjórnar þessum klasa
     */
    public void setGoldController(GoldController goldController){
        this.goldController = goldController;
    }

    @FXML
    private RadioMenuItem nyrLeikur, lokaGlugga, umforrit, leikreglur;
    /**
     * Ef Nýr leikur er valið, sem er undir Skrá þá fer notandinn aftur í valmyndina fyrir erfiðleikastig
     * og borðið er hreinsað
     */
    @FXML
    public void onNyrLeikur(ActionEvent event) {
        RadioMenuItem menuItem = (RadioMenuItem) event.getSource();
        if (menuItem.isSelected()){
            nyrLeikur.setSelected(false);
        }

        System.out.println("Nýr Leikur");
        ButtonType response = showConfirmation(
                "Byrja upp á nýtt",
                "Ertu viss um að þú viljir byrja upp á nýtt?"
        );

        if (response.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            goldController.hreinsaBord();
            goldController.updatePoints(0);
            ViewSwitcher.switchTo(View.ERFIDLEIKI);
        }
    }
    /**
     * Ef Loka er valið, sem er undir Skrá þá fer notandinn aftur í valmyndina start og borðið er hreinsað
     */
    public void onLokaPressed(ActionEvent event) {
        RadioMenuItem menuItem = (RadioMenuItem) event.getSource();
        if (menuItem.isSelected()){
            lokaGlugga.setSelected(false);
        }

        ButtonType response = showConfirmation(
                "Staðfesting til að hætta leik",
                "Ertu viss um að þú viljir hætta?"
        );

        if (response.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            goldController.hreinsaBord();
            goldController.updatePoints(0);
            ViewSwitcher.switchTo(View.START);
        }
    }
    /**
     * Ef Um forritið er valið, sem er undir Hjálp þá birtist upplýsinga alert sem segir notandanum
     * hver bjó til leikinn
     */
    @FXML
    private void onUmForritid(ActionEvent event){
        RadioMenuItem menuItem = (RadioMenuItem) event.getSource();
        if (menuItem.isSelected()){
            umforrit.setSelected(false);
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Um Forritið");
        alert.setHeaderText(null);
        alert.setContentText("Þetta er leikurinn Gold Rush. \nUnnið af: Sigrún Edda, Helga Björg, Kristín Fríða, Elma Karen og Sylvía Hanna \nÁrtal: 2024");

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/goldrush/myndir/Icon.jpg"))));


        alert.showAndWait();
    }
    /**
     * Ef Leikreglur er valið, sem er undir Hjálp þá birtist leikreglur-view.fxml sem segir notandanum hvernig
     * leikurinn virkar
     */
    @FXML
    private void onLeikreglur(ActionEvent event){
        RadioMenuItem menuItem = (RadioMenuItem) event.getSource();
        if (menuItem.isSelected()){
            leikreglur.setSelected(false);
        }

        System.out.println("Leikreglur display!");
        ViewSwitcher.switchTo(View.LEIKREGLUR);
    }
    /**
     * Sýnir staðfestingar alert með sameiginlegu útliti.
     * @param title titill
     * @param header header texti
     * @return valinn ButtonType
     */
    private ButtonType showConfirmation(String title, String header) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText("Veldu OK til að hætta, eða Cancel til að halda áfram");

        ButtonType buttonOK = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().setAll(buttonOK, ButtonType.CANCEL);

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/goldrush/myndir/Icon.jpg"))));

        return alert.showAndWait().orElse(ButtonType.CANCEL);
    }
}
