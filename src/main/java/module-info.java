module ec.edu.espol.rsashield {
    requires javafx.controls;
    requires javafx.fxml;

    // Export the main package to make it accessible to other modules
    exports ec.edu.espol.rsashield;

    // Open the packages containing FXML files and controllers to JavaFX
    opens ec.edu.espol.rsashield to javafx.fxml;
    opens ec.edu.espol.rsashield.controllers to javafx.fxml;
}
