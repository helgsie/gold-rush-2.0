module goldrush {
    requires javafx.controls;
    requires javafx.fxml;

    exports goldrush;
    exports goldrush.app;
    exports goldrush.vinnsla;
    exports goldrush.vidmot.controller;
    exports goldrush.vidmot.dialog;
    exports goldrush.vidmot.view;

    opens goldrush to javafx.fxml;
    opens goldrush.app to javafx.fxml;
    opens goldrush.vidmot.controller to javafx.fxml;
    opens goldrush.vidmot.dialog to javafx.fxml;
    opens goldrush.vidmot.view to javafx.fxml;
}
