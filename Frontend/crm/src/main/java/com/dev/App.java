package com.dev;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("CRM");
        stage.setWidth(1000);
        stage.setHeight(720);
        
        Parent root = FXMLLoader.load(
            getClass().getResource("/registration.fxml") // или App.class.getResource(...)
        );

        Scene scene = new Scene(root);

        scene.getStylesheets().add(
            getClass().getResource("/style.css").toExternalForm()
        );

        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}