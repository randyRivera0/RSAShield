/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.rsashield.controllers;

import ec.edu.espol.rsashield.App;
import ec.edu.espol.rsashield.FileHandler;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author User Dell
 */
public class EncryptController implements Initializable {

    @FXML
    TextField textFieldPrime1;
    @FXML
    TextField textFieldPrime2;
    @FXML
    TextArea textAreaMessage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //
    }

    @FXML
    public void encrypt(ActionEvent event) {
        BigInteger p = new BigInteger(textFieldPrime1.getText());
        BigInteger q = new BigInteger(textFieldPrime2.getText());
        String message = textAreaMessage.getText();
        FileHandler.storePassword(p, q, message);
    }

    @FXML
    private void returnButton(ActionEvent event) {
        try {
            App.setRoot("start");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
