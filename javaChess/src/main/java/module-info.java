module javaChess {
    requires javafx.controls;
    requires javafx.fxml;

    opens javaChess to javafx.fxml;
    exports javaChess;
}