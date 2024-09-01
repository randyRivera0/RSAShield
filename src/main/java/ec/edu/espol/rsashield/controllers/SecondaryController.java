package ec.edu.espol.rsashield.controllers;

import ec.edu.espol.rsashield.App;
import java.io.IOException;
import javafx.fxml.FXML;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}
