package goldrush.app;

/**
 * Geymir valið fyrir mismunandi fxml skrár, þannig það er hægt að velja á milli view
 */
public enum View {
    START("/goldrush/start-view.fxml"),
    ERFIDLEIKI("/goldrush/erfidleiki-view.fxml"),
    KARAKTER("/goldrush/karakter-view.fxml"),
    LEIKREGLUR("/goldrush/leikreglur-view.fxml"),
    LEIKBORD("/goldrush/gold-rush-view.fxml");

    private final String fileName;

    View(String fileName){
        this.fileName = fileName;
    }
    public String getFileName(){
        return fileName;
    }
}
