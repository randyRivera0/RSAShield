package ec.edu.espol.rsashield;

import static ec.edu.espol.rsashield.FileHandler.askForPrime;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigInteger;

/**
 * JavaFX App
 */

public class App extends Application {

    private static Scene scene;
    private static Encryption encryption;

    public static Encryption getEncryption() {
        return encryption;
    }
    
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("start"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        Encryption encryption = new Encryption();
        BigInteger p = new BigInteger("11");
        BigInteger q = new BigInteger("13");
        Encryption.setP(p);
        Encryption.setQ(q);
        Encryption.setKeyPair(RSAEncryption.generateKeyPair(p, q));
        launch();
    }
}

