/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.rsashield.controllers;

import ec.edu.espol.rsashield.App;
import ec.edu.espol.rsashield.FileHandler;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author User Dell
 */
public class DecryptController implements Initializable {

    @FXML
    TextField textFieldExponentD;
    @FXML
    TextField textFieldModulusN;
    @FXML
    TextArea textAreaDecryptMessages;
    @FXML
    Button buttonDecrypt;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void decrypt(ActionEvent event){
        String StringD = textFieldExponentD.getText();
        String StringN = textFieldModulusN.getText();
        String message = FileHandler.retrievePassword(StringD, StringN);
        textAreaDecryptMessages.setText(message);
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
