package goldrush.vidmot.view;

import goldrush.vinnsla.Erfidleikastig;
import goldrush.vinnsla.Leikur;
import goldrush.vidmot.controller.GoldController;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.control.MenuBar;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Leikborð sem inniheldur grafara, gull og óvini. 
 * Sýnir keyrslu leiks.
 */
public class Leikbord extends Pane {

    @FXML
    private GoldController goldController;
    private Erfidleikastig erfidleikastig = Erfidleikastig.AUDVELT;
    private Leikur leikur;
    private Grafari grafari;
    private long lastUpdateTime = 0;
    private static final long UPDATE_INTERVAL = 16_666_666;
    private boolean isMovingUp = false;
    private boolean isMovingDown = false;
    private boolean isMovingLeft = false;
    private boolean isMovingRight = false;
    private AnimationTimer gullLoop;
    private AnimationTimer ovinurLoop;
    private Timeline ovinurDropper;
    @FXML
    public MenuBar menustyring;
    private static final double SPEED = 5.0;
    private final List<Gull> gulls = new ArrayList<>();
    private final ObservableList<Ovinur> ovinur = FXCollections.observableArrayList();
    public static final String VARST_DREPINN = "Bowser náði þér.";

    /**
     * Setter fyrir GoldController.
     *
     * @param goldController controller leikborðsins
     */
    public void setGoldController(GoldController goldController) {
        this.goldController = goldController;
    }

    /**
     * Setur leik til ad nota sameiginlega leikstodu.
     *
     * @param leikur leikur
     */
    public void setLeikur(Leikur leikur) {
        this.leikur = leikur;
        if (leikur != null) {
            setErfidleikastig(leikur.getErfidleikastig());
        }
    }

    /**
     * Setur inn erfidleikastig leiksins.
     *
     * @param erfidleikastig erfidleikastig leiksins
     */
    public void setErfidleikastig(Erfidleikastig erfidleikastig) {
        this.erfidleikastig = erfidleikastig;
    }

    /**
     * Skilar fjolda ovina.
     *
     * @return fjoldi ovina
     */
    public int getFjoldiOvina() {
        return erfidleikastig.getFjoldiOvina();
    }

    /**
     * Smiður til að upphafsstilla leikborð
     */
    public Leikbord() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/goldrush/leikbord-view.fxml"));
        fxmlLoader.setClassLoader(getClass().getClassLoader());
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setFocusTraversable(true);
        requestFocus();
    }

    private double randomX(double width) {
        double minX = 0;
        double maxX = getWidth() - width;
        return Math.random() * (maxX - minX) + minX;
    }

    private double randomY(double height) {
        double minY = menustyring != null ? menustyring.getHeight() : 0;
        double maxY = getHeight() - height;
        return Math.random() * (maxY - minY) + minY;
    }

    /**
     * Setur óvin á leikborðið
     */
    public void startOvinur() {
        Duration ovinurInterval = Duration.seconds(1);
        ovinurDropper = new Timeline(new KeyFrame(ovinurInterval, event -> dropOvinur()));
        ovinurDropper.play();
        ovinurLoop = new AnimationTimer() {
            @Override
            public void handle(long l) {
                ovinurDrepur();
                updateGrafariPosition();
            }
        };
        ovinurLoop.start();

        setOnKeyPressed(this::handleKeyPress);
        setOnKeyReleased(this::handleKeyRelease);
    }

    /**
     * Passar að óvinir halda ekki áfram að birtast á leikborði
     */
    public void stopOvinur() {
        if (ovinurDropper != null) {
            ovinurDropper.stop();
        }
        for (Ovinur o : ovinur) {
            o.stop();
        }
    }

    /**
     * Setur óvin á leikborði
     */
    private void dropOvinur() {
        for (int i = 0; i < getFjoldiOvina(); i++) {
            Ovinur ovinur1 = new Ovinur();
            ovinur.add(ovinur1);
            getChildren().add(ovinur1);

            ovinur1.setLayoutX(randomX(ovinur1.getWidth()));
            ovinur1.setLayoutY(randomY(ovinur1.getHeight()));
        }
    }

    /**
     * Ef grafari rekst á óvin þá stöðvast leikurinn
     */
    public void ovinurDrepur() {
        for (Ovinur o : ovinur) {
            if (o.isCollidingWithGrafari(grafari)) {
                goldController.leikLokid(VARST_DREPINN);
                System.out.println("Óvinur drap þig");
                if (ovinurLoop != null) {
                    ovinurLoop.stop();
                    ovinurLoop = null;
                }

                setOnKeyPressed(null);
                setOnKeyReleased(null);

                isMovingUp = false;
                isMovingDown = false;
                isMovingLeft = false;
                isMovingRight = false;
            }
        }
    }

    /**
     * Animation fyrir gullið í leikborði
     */
    public void startGullDropper() {
        gullLoop = new AnimationTimer() {
            @Override
            public void handle(long l) {
                grafaGull();
            }
        };
        gullLoop.start();

        Platform.runLater(this::dropGull);
    }

    /**
     * Passar að aðeins eitt gull myndast í einu á leikborði
     */
    public void stopGullDropper() {
        if (gullLoop != null) {
            gullLoop.stop();
            gullLoop = null;
        }
    }

    /**
     * Staðsetur gull á leikborði
     */
    private void dropGull() {
        Gull gull = new Gull();
        gulls.add(gull);
        getChildren().add(gull);

        gull.setLayoutX(randomX(gull.getWidth()));
        gull.setLayoutY(randomY(gull.getHeight()));
    }

    /**
     * Þegar grafari grefur gull þá hverfur gullið af leikborði og stigin uppfærast
     */
    public void grafaGull() {
        Bounds grafariBounds = grafari.getBoundsInParent();
        boolean gullGrafid = false;

        Iterator<Gull> iterator = gulls.iterator();
        while (iterator.hasNext()) {
            Gull gull = iterator.next();
            Bounds gullBounds = gull.getBoundsInParent();

            if (grafariBounds.intersects(gullBounds)) {
                iterator.remove();
                getChildren().remove(gull);
                goldController.updatePoints(1);
                gullGrafid = true;
            }
        }
        if (gulls.isEmpty() && gullGrafid) {
            dropGull();
        }
    }

    /**
     * Stillir hvernig grafari hreyfir sig miðað við hvaða örvatakka er ýtt á.
     *
     * @param event - Hvernig grafari hreyfir sig
     */
    private void handleKeyPress(KeyEvent event) {
        long currentTime = System.nanoTime();
        if (currentTime - lastUpdateTime < UPDATE_INTERVAL) {
            return;
        }
        switch (event.getCode()) {
            case UP -> isMovingUp = true;
            case DOWN -> isMovingDown = true;
            case LEFT -> isMovingLeft = true;
            case RIGHT -> isMovingRight = true;
            default -> {
            }
        }
        updateGrafariPosition();
        lastUpdateTime = currentTime;
    }

    /**
     * Stöðvar grafarann þegar sleppt er örvatökkunum
     *
     * @param event örvatakkinn sem hefur verið sleppt
     */
    private void handleKeyRelease(KeyEvent event) {
        switch (event.getCode()) {
            case UP -> isMovingUp = false;
            case DOWN -> isMovingDown = false;
            case LEFT -> isMovingLeft = false;
            case RIGHT -> isMovingRight = false;
            default -> {
            }
        }
    }

    /**
     * Aðferð til að grafari fari ekki útfyrir leikborð
     *
     * @param x - x hnit á leikborði
     * @param y - y hnit á leikborði
     * @return true er grafari er inná leikborði, annars false
     */
    private boolean erLoglegt(double x, double y) {
        return x >= 0 && y >= 0 && x <= getWidth() - grafari.getWidth() && y < getHeight() - grafari.getHeight();
    }

    /**
     * Uppfærir staðsetningu á grafara
     */
    private void updateGrafariPosition() {
        if (isMovingUp) {
            if (erLoglegt(grafari.getLayoutX(), grafari.getLayoutY() - SPEED)) {
                grafari.setLayoutY(grafari.getLayoutY() - SPEED);
            }
        }
        if (isMovingDown) {
            if (erLoglegt(grafari.getLayoutX(), grafari.getLayoutY() + SPEED)) {
                grafari.setLayoutY(grafari.getLayoutY() + SPEED);
            }
        }
        if (isMovingLeft) {
            if (erLoglegt(grafari.getLayoutX() - SPEED, grafari.getLayoutY())) {
                grafari.setLayoutX(grafari.getLayoutX() - SPEED);
            }
        }
        if (isMovingRight) {
            if (erLoglegt(grafari.getLayoutX() + SPEED, grafari.getLayoutY())) {
                grafari.setLayoutX(grafari.getLayoutX() + SPEED);
            }
        }
    }

    /**
     * Stöðvar leik og hreinsar allt af leikborði
     */
    public void hreinsaBord() {
        if (gullLoop != null) {
            gullLoop.stop();
            gullLoop = null;
        }
        if (ovinurLoop != null) {
            ovinurLoop.stop();
            ovinurLoop = null;
        }
        stopGullDropper();
        stopOvinur();
        for (Ovinur o : ovinur) {
            o.stop();
        }
        getChildren().removeAll(gulls);
        gulls.clear();
        getChildren().removeIf(node -> node instanceof Ovinur);
        ovinur.clear();
        getChildren().remove(grafari);

        setOnKeyPressed(null);
        setOnKeyReleased(null);

        isMovingUp = false;
        isMovingDown = false;
        isMovingLeft = false;
        isMovingRight = false;
    }

    /**
     * Byrjar leik, setur grafara, gull og óvin á leikborð.
     * Byrjar að telja tímann
     */
    public void hefjaAfram() {
        grafari = new Grafari();
        if (leikur != null) {
            setErfidleikastig(leikur.getErfidleikastig());
        }
        grafari.setImage(leikur != null ? leikur.getSelectedCharacter() : "Mario");
        getChildren().add(grafari);
        startGullDropper();
        startOvinur();
        goldController.startCountUp();

    }
}
