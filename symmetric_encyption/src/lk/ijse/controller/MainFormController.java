package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class MainFormController {
    public Button btnEncryptForm;
    public Button btnDecryptForm;

    public void btnEncryptForm_onAction(ActionEvent actionEvent) throws IOException {
        formLoad("/lk/ijse/view/EncryptForm.fxml", "Encryption");
    }

    public void btnDecryptForm_onAction(ActionEvent actionEvent) throws IOException {
        formLoad("/lk/ijse/view/DecryptForm.fxml", "Decryption");
    }

    public void formLoad(String location, String title) throws IOException {
        URL root = getClass().getResource(location);
        Parent load = FXMLLoader.load(root);
        Stage stage = new Stage();
        Scene scene = new Scene(load);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.centerOnScreen();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
                (btnDecryptForm.getScene().getWindow())
        );
        stage.show();
    }
}
