package com.example.ejercicioa;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloApplication extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ejercicioA.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 610, 520);
        stage.setMinHeight(500);
        stage.setMinWidth(600);
        stage.setTitle("Ejercicio A!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }


}