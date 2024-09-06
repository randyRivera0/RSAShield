/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.rsashield.controllers;

import ec.edu.espol.rsashield.App;
import ec.edu.espol.rsashield.Encryption;
import ec.edu.espol.rsashield.RSAEncryption;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author User Dell
 */
public class StartController implements Initializable {
    
    Encryption encryption;
    
    @FXML
    BorderPane bp;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        encryption = App.getEncryption();
        
        Image image = new Image(getClass().getResourceAsStream("/img/fondorsa.jpg"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(680);
        imageView.setFitHeight(480);
        bp.getChildren().add(0, imageView); // Agregar imagen al fondo
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
    
    @FXML
    public void getKeys(ActionEvent event){
        try {
            App.setRoot("keys");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
        
}
