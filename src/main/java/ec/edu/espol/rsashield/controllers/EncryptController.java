/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.rsashield.controllers;

import ec.edu.espol.rsashield.App;
import ec.edu.espol.rsashield.Encryption;
import ec.edu.espol.rsashield.App;
import ec.edu.espol.rsashield.FileHandler;
import ec.edu.espol.rsashield.RSAEncryption;
import java.io.IOException;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author User Dell
 */
public class EncryptController implements Initializable {
    
    Encryption encryption;

    @FXML
    TextField textFieldPrime1;
    @FXML
    TextField textFieldPrime2;
    @FXML
    TextArea textAreaMessage;
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
        bp.getChildren().add(0, imageView);

    }


    @FXML
    public void encrypt(ActionEvent event) {
        String message = textAreaMessage.getText();
        FileHandler.storePassword(message);
        try {
            App.setRoot("start");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
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
