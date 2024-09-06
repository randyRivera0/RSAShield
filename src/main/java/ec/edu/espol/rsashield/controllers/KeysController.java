/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.rsashield.controllers;

import ec.edu.espol.rsashield.App;
import ec.edu.espol.rsashield.Encryption;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author User Dell
 */
public class KeysController implements Initializable {
    
    Encryption encryption;
    
    @FXML
    TextField textFieldP;
    @FXML
    TextField textFieldQ;
    @FXML
    Text textN;
    @FXML
    Text textPhi;
    @FXML
    Text textE;
    @FXML
    Text textD;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        encryption = App.getEncryption();
        textFieldP.setText(Encryption.getP().toString());
        textFieldQ.setText(Encryption.getQ().toString());
        textN.setText(Encryption.getKeyPair().getPrivateKey().getN().toString());
        textPhi.setText(Encryption.getPhi().toString());
        textE.setText(Encryption.getE().toString());
        textD.setText(Encryption.getD().toString());
        
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
