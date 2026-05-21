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
        stage.setWidth(1200);
        stage.setHeight(800);
        
        Parent loginRoot = FXMLLoader.load(
            getClass().getResource("/views/pages/tasks-list.fxml")
        );

        Scene scene = new Scene(loginRoot);

        scene.getStylesheets().add(
            getClass().getResource("/style.css").toExternalForm()
        );

        // Http
        // .request("/api/tasks/create")
        // .post("")
        // .auth("")
        // .onResponse(response -> {
        //     System.out.println(response.body());
        //     System.out.println(response.statusCode());
        // })
        // .send();

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}