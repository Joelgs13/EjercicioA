package com.example.ejercicioa;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * Controlador de la interfaz de encuestas que gestiona la interacción
 * con los elementos de la vista. Permite al usuario ingresar datos como
 * profesión, número de hermanos, deportes y calificaciones de ciertas
 * actividades, y valida los datos antes de mostrarlos o generar mensajes
 * de error si los datos son incorrectos.
 */
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

    /**
     * Inicializa los componentes de la vista, como el ComboBox de edad, la lista
     * de deportes y deshabilita la lista de deportes si el CheckBox "¿Practica algún deporte?"
     * no está seleccionado.
     */
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

    /**
     * Habilita o deshabilita la lista de deportes según si el CheckBox "¿Practica algún deporte?"
     * está seleccionado o no.
     */
    @FXML
    private void habilitaLista() {
        SportList.setDisable(!chk_practica.isSelected());
    }

    /**
     * Valida los datos ingresados por el usuario, asegurándose de que los campos requeridos estén
     * completos y correctos. Si hay errores, se muestran en una alerta; si no hay errores,
     * se muestra un resumen de los datos ingresados. ambos en ventanas emergentes
     */
    @FXML
    private void validarYMostrarDatos() {
        StringBuilder errores = new StringBuilder();

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

    /**
     * Muestra un mensaje de error en una ventana emergente si los datos ingresados
     * no son correctos u estan vacios
     *
     * @param mensaje El mensaje de error a mostrar.
     */
    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error en los datos");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    /**
     * Muestra un mensaje de información en una ventana emergente si los datos ingresados
     * son válidos y correctos, primero comprobando la anterior pantalla
     *
     * @param titulo El título de la alerta informativa.
     * @param mensaje El mensaje a mostrar en la alerta.
     */
    private void mostrarInformacion(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    /**
     * Cierra la aplicación cuando se hace clic en cancelar
     */
    @FXML
    private void cerrarAplicacion() {
        Stage stage = (Stage) btn_cancelar.getScene().getWindow();
        stage.close();
    }
}
