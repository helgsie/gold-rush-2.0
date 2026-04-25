package goldrush.vidmot.controller;

import goldrush.app.View;
import goldrush.app.ViewSwitcher;
import goldrush.vidmot.dialog.AdvorunDialog;
import goldrush.vidmot.view.Leikbord;
import goldrush.vinnsla.Klukka;
import goldrush.vinnsla.Leikur;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Objects;
import java.util.Optional;

public class GoldController {
    public MenuBar menustyring;
    @FXML
    private MenuController menustyringController;
    @FXML
    private Label fxTimi;
    @FXML
    private Label fxStig;
    @FXML
    private Leikbord leikbord;
    private Leikur leikur;
    private Klukka klukka;
    private Timeline countUpTimeline;

    /**
     * Smiður til að setja upp leikborð
     */
    public GoldController() {
        this.leikbord = new Leikbord();
        this.leikur = new Leikur();
    }

    /**
     * Setur leik til ad deila sameiginlegri leikstodu milli hluta forritsins.
     *
     * @param leikur leikur
     */
    public void setLeikur(Leikur leikur) {
        this.leikur = leikur;
        leikbord.setLeikur(leikur);
    }

    /**
     * Upphafsstilling til að tengja saman leikbord og menustyring
     * við GoldController
     */
    @FXML
    public void initialize() {
        menustyringController.setGoldController(this);
        leikbord.setGoldController(this);
        leikbord.setLeikur(leikur);
        klukka = new Klukka();
    }

    /**
     * Adferd til ad upphafsstilla timann i leikbordi.
     */
    public void startCountUp() {
        klukka.startCountUp();
        fxTimi.setText(klukka.getFormattedTime());

        if (countUpTimeline != null) {
            countUpTimeline.stop();
            countUpTimeline.getKeyFrames().clear();
        }

        countUpTimeline = new Timeline();
        countUpTimeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), event -> {
            klukka.tick();
            fxTimi.setText(klukka.getFormattedTime());
        });
        countUpTimeline.getKeyFrames().add(keyFrame);
        countUpTimeline.play();
    }

    /**
     * Uppfaerir stig i leikbordi.
     *
     * @param points stig sem a ad baeta vid. Ef gildid er 0 eru nuverandi stig endurstillt.
     */
    public void updatePoints(int points) {
        if (points == 0) {
            leikur.endurstillaStig();
        } else {
            leikur.baetaVidStigum(points);
        }

        fxStig.setText(String.valueOf(leikur.getStigin()));
    }

    /**
     * Ef leikmaður deyr í leikborði þá núllstillast stigin og tíminn.
     * Alert gluggi kemur upp
     * @param varstDrepinn - hvort leikmaður deyr í leikborði
     */
    public void leikLokid(String varstDrepinn) {
        klukka.stopCountUp();
        if (countUpTimeline != null) {
            countUpTimeline.stop();
        }
        synaAlert(varstDrepinn);
    }

    /**
     * Alert gluggi kemur upp með skilaboð um að leiknum sé lokið, stigafjölda
     * og tíma
     * @param skilabod astaeda af hverju leik var tapad
     */
    private void synaAlert(String skilabod) {
        Platform.runLater(() ->{
            AdvorunDialog alert = new AdvorunDialog("Leik lokið", skilabod, "Stigin þín: " + leikur.getStigin() + " | Hæsti stigafjöldi: " + leikur.getHaestuStig() +
                    "\nTiminn þinn: " + klukka.getFormattedTime());

            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/goldrush/myndir/Icon.jpg"))));

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == AdvorunDialog.BTYPE) {
                hreinsaBord();
                updatePoints(0);
                leikbord.hefjaAfram();
            } else {
                hreinsaBord();
                updatePoints(0);
                ViewSwitcher.switchTo(View.START);
            }
        });
    }

    /**
     * Skilar leikborði
     * @return leikbord
     */
    public Leikbord getLeikbord() {
        return leikbord;
    }

    /**
     * Eyðir öllu af leikborði
     */
    public void hreinsaBord() {
        leikbord.hreinsaBord();
    }
}
