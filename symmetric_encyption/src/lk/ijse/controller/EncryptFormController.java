package lk.ijse.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.*;


public class EncryptFormController {
    public Button btnEncrypt;
    public TextArea txtToEncrypt;
    public TextField txtEncryptKey;
    public TextArea txtEncryptedText;

    public void initialize() {
        Platform.runLater(() -> txtToEncrypt.requestFocus());

    }

    public void btnEncrypt_onAction(ActionEvent actionEvent) {

        String text = txtToEncrypt.getText() == null ? "" : txtToEncrypt.getText();
        String key = txtEncryptKey.getText() == null ? "" : txtEncryptKey.getText();

        if (text.trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please enter valid text to encrypt", ButtonType.OK).show();
            txtToEncrypt.requestFocus();
            return;
        }

        if (key.trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please enter valid key", ButtonType.OK).show();
            txtEncryptKey.requestFocus();
            return;
        }

        text += key;
        String reversedText = "";
        int textLength = text.length();
        for (int i = textLength - 1; i >= 0; i--) {
            reversedText += text.charAt(i);
        }

        String cipherText = "";
        int reversedTextLength = reversedText.length();
        for (int i = 0; i < reversedTextLength; i++) {
            int charCode = reversedText.charAt(i);
            charCode += 10;
            char newChar = (char) charCode;
            cipherText += newChar;
        }

        txtEncryptedText.setText(cipherText);
    }
}
