package goldrush.app;

/**
 * Geymir valið fyrir mismunandi fxml skrár, þannig það er hægt að velja á milli view
 */
public enum View {
    /** Upphafsskjár forritsins. */
    START("/goldrush/start-view.fxml"),
    /** Erfiðleikavalsskjár. */
    ERFIDLEIKI("/goldrush/erfidleiki-view.fxml"),
    /** Karaktervalsskjár. */
    KARAKTER("/goldrush/karakter-view.fxml"),
    /** Leikregluskjár. */
    LEIKREGLUR("/goldrush/leikreglur-view.fxml"),
    /** Aðalleikborðsskjár. */
    LEIKBORD("/goldrush/gold-rush-view.fxml");

    private final String fileName;

    View(String fileName){
        this.fileName = fileName;
    }

    /**
     * Skilar slóð á FXML skrá fyrir þetta view.
     *
     * @return slóð á FXML skrá
     */
    public String getFileName(){
        return fileName;
    }
}
