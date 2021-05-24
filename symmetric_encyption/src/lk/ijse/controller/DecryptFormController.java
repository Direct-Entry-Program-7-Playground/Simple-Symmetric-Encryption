package lk.ijse.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import lk.ijse.crypto.DEP7Crypto;

public class DecryptFormController {
    public Button btnEncrypt;
    public TextArea txtToDecrypt;
    public TextField txtEncryptKey;
    public TextArea txtOriginal;
    public Button btnPasteCypher;

    public void initialize() {
        Platform.runLater(() -> txtToDecrypt.requestFocus());
    }

    public void btnEncrypt_onAction(ActionEvent actionEvent) {
        String cipherText = txtToDecrypt.getText().trim();
        String key = txtEncryptKey.getText().trim();

        if (cipherText.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please enter valid text to encrypt", ButtonType.OK).show();
            txtToDecrypt.requestFocus();
            return;
        }

        if (key.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please enter valid key", ButtonType.OK).show();
            txtEncryptKey.requestFocus();
            return;
        }

        txtOriginal.setText(DEP7Crypto.decrypt(cipherText, key));

    }

    // Paste btn
    public void btnPasteCypher_onAction(ActionEvent actionEvent) {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        txtToDecrypt.setText(clipboard.getString());
    }
}
