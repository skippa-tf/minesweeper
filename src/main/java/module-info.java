module ca.jasoncodes.minesweeper {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens ca.jasoncodes.minesweeper to javafx.fxml;
    exports ca.jasoncodes.minesweeper;
}