package lk.ijse.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class DecryptFormController {
    public Button btnEncrypt;
    public TextArea txtToDecrypt;
    public TextArea txtDecryptedText;
    public TextField txtEncryptKey;

    public void initialize() {
        Platform.runLater(()->txtToDecrypt.requestFocus());
    }

    public void btnEncrypt_onAction(ActionEvent actionEvent) {
    }
}
