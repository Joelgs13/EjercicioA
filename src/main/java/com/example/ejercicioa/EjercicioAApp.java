package com.example.ejercicioa;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * La clase `HelloApplication` es la clase principal de la aplicación JavaFX.
 * Se encarga de cargar y mostrar la interfaz de usuario definida en el archivo FXML "ejercicioA.fxml".
 * También configura la ventana principal de la aplicación, como el tamaño mínimo y el título.
 */
public class EjercicioAApp extends Application {

    /**
     * El metodo `start` es el punto de entrada para la ejecución de la aplicación JavaFX.
     * Este metodo se ejecuta cuando se inicia la aplicación y configura la ventana principal,
     * cargando el archivo FXML y estableciendo la escena con su tamaño y otros parámetros.
     *
     * @param stage El escenario principal de la aplicación, que representa la ventana.
     * @throws IOException Si ocurre un error al cargar el archivo FXML.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(EjercicioAApp.class.getResource("ejercicioA.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 610, 520);
        stage.setMinHeight(500);
        stage.setMinWidth(600);
        stage.setTitle("Ejercicio A!");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * El metodo `main` es el punto de entrada estándar para ejecutar la aplicación.
     * Llama al metodo `launch`, que inicia el ciclo de vida de la aplicación JavaFX.
     *
     * @param args Los argumentos de la línea de comandos, si los hay.
     */
    public static void main(String[] args) {
        launch();
    }
}
