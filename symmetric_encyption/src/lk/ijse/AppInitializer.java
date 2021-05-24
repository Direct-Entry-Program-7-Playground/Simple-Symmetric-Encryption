package lk.ijse;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        URL root = this.getClass().getResource("/lk/ijse/view/MainForm.fxml");
        Parent load = FXMLLoader.load(root);
        Scene scene = new Scene(load);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Simple Symmetric Encryption");
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}
