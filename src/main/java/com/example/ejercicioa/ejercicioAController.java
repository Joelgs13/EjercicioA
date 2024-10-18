package com.example.ejercicioa;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class ejercicioAController {

    @FXML
    private ListView<String> SportList;

    @FXML
    private ComboBox<String> cb_edad;

    @FXML
    private CheckBox chk_practica;

    @FXML
    private TextField tf_profesion;

    @FXML
    private TextField tf_Nhermanos;

    @FXML
    private Slider sldr_compras;

    @FXML
    private Slider sldr_television;

    @FXML
    private Slider sldr_cine;

    @FXML
    private Button btn_aceptar;

    @FXML
    private Button btn_cancelar;

    @FXML
    public void initialize() {
        ObservableList<String> edades = FXCollections.observableArrayList(
                "Menores de 18", "Entre 18 y 30", "Entre 31 y 50", "Entre 51 y 70", "Mayores de 70"
        );
        cb_edad.setItems(edades);

        ObservableList<String> deportes = FXCollections.observableArrayList(
                "Tenis", "Fútbol", "Baloncesto", "Natación", "Ciclismo", "Otro"
        );
        SportList.setItems(deportes);

        SportList.setDisable(true);

        cb_edad.setTooltip(new Tooltip("rango de edad al que pertenece"));
        SportList.setTooltip(new Tooltip("deporte preferido si practica alguno de los siguientes"));
    }

    @FXML
    private void habilitaLista() {
        SportList.setDisable(!chk_practica.isSelected());
    }

    @FXML
    private void validarYMostrarDatos() {
        StringBuilder errores = new StringBuilder();

        //VA AÑADIENDO TROZOS DE CADENA CORRESPONDIENTE A LOS ERRORES ENCONTRADOS

        String profesion = tf_profesion.getText().trim();
        if (profesion.isEmpty()) {
            errores.append("Profesion no puede estar vacío.\n");
        }

        String hermanosText = tf_Nhermanos.getText().trim();
        int numHermanos = -1;
        try {
            numHermanos = Integer.parseInt(hermanosText);
            if (numHermanos < 0) {
                errores.append("El número de hermanos no puede ser negativo.\n");
            }
        } catch (NumberFormatException e) {
            errores.append("numero de hermanos no es un numero.\n");
        }

        if (chk_practica.isSelected() && SportList.getSelectionModel().isEmpty()) {
            errores.append("Debido que marcó que practica algun deporte, marque tambien su deporte favorito.\n");
        }
        //si hay errores muestra error
        if (errores.length() > 0) {
            mostrarError(errores.toString());
            return;
        }

        String edadSeleccionada = cb_edad.getSelectionModel().getSelectedItem();
        String deporteSeleccionado = chk_practica.isSelected() ? SportList.getSelectionModel().getSelectedItem() : "No practica deportes";
        double compras = sldr_compras.getValue();
        double television = sldr_television.getValue();
        double cine = sldr_cine.getValue();

        String mensaje = String.format("Profesión: %s\nNúmero de hermanos: %d\nEdad: %s\nDeporte: %s\nCompras: %.1f\nVerTelevisión: %.1f\nIr al cine: %.1f",
                profesion, numHermanos, edadSeleccionada, deporteSeleccionado, compras, television, cine);

        mostrarInformacion("Datos ingresados correctamente", mensaje);
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error en los datos");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarInformacion(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    private void cerrarAplicacion() {
        Stage stage = (Stage) btn_cancelar.getScene().getWindow();
        stage.close();
    }
}
