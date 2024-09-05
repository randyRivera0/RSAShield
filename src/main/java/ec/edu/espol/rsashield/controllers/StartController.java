/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.rsashield.controllers;

import ec.edu.espol.rsashield.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author User Dell
 */
public class StartController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void encrypt(ActionEvent event){
        try {
            App.setRoot("encrypt");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    
    @FXML
    public void decrypt(ActionEvent event){
        try {
            App.setRoot("decrypt");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
        
}
