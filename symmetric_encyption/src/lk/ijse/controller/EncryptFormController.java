package lk.ijse.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import lk.ijse.crypto.DEP7Crypto;


public class EncryptFormController {
    public Button btnEncrypt;
    public TextArea txtToEncrypt;
    public TextField txtEncryptKey;
    public TextArea txtCypher;
    public Button btnCopyCypher;

    public void initialize() {
        Platform.runLater(() -> txtToEncrypt.requestFocus());

    }

    public void btnEncrypt_onAction(ActionEvent actionEvent) {

        String text = txtToEncrypt.getText().trim();
        String key = txtEncryptKey.getText().trim();

        if (text.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please enter valid text to encrypt", ButtonType.OK).show();
            txtToEncrypt.requestFocus();
            return;
        }

        if (key.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please enter valid key", ButtonType.OK).show();
            txtEncryptKey.requestFocus();
            return;
        }

        txtCypher.setText(DEP7Crypto.encrypt(text, key));
    }

    // Copy btn
    public void btnCopyCypher_onAction(ActionEvent actionEvent) {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        content.putString(txtCypher.getText());
        clipboard.setContent(content);
    }
}
