package goldrush.vidmot.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.util.Objects;

/**
 * Leikmaður á leikborðinu sem notandi stýrir með örvatökkum.
 */
public class Grafari extends Rectangle {

    /**
     * Smiður til að upphafsstilla grafara
     */
    public Grafari(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/goldrush/grafari-view.fxml"));
        fxmlLoader.setClassLoader(getClass().getClassLoader());
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e){
            throw new RuntimeException(e);
        }

        setLayoutX(50);
        setLayoutY(50);
    }

    /**
     * Setur mynd a grafara.
     *
     * @param selectedCharacter valinn karakter
     */
    public void setImage(String selectedCharacter){
        if (selectedCharacter != null) {
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/goldrush/myndir/" + selectedCharacter + ".png")));
            setFill(new ImagePattern(image));
            System.out.println(selectedCharacter + " valin!");
        } else {
            System.out.println("Enginn karakter valinn :(");
        }
    }
}
