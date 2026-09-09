package com.asignacion.controller;

import com.asignacion.App;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistroEstudiantesController {

    @FXML private BorderPane panelEstudiantes;
    @FXML private TextField txtCarnet;
    @FXML private TextField txtNombres;
    @FXML private TextField txtApellidos;
    @FXML private TextField txtCarrera;
    @FXML private TextField txtCorreo;
    @FXML private TextArea areaRegistros;

    @FXML
    private void guardarEstudiante() {
        if (hayCamposVacios()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Datos incompletos",
                    "Todos los campos son obligatorios.");
            return;
        }

        if (!txtCorreo.getText().trim().matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
            mostrarAlerta(Alert.AlertType.WARNING, "Correo no válido",
                    "Ingrese una dirección de correo válida.");
            txtCorreo.requestFocus();
            return;
        }

        String registro = String.format(
                "Carnet: %s%nNombre completo: %s %s%nCarrera: %s%nCorreo: %s%n%s%n",
                txtCarnet.getText().trim(),
                txtNombres.getText().trim(),
                txtApellidos.getText().trim(),
                txtCarrera.getText().trim(),
                txtCorreo.getText().trim(),
                "----------------------------------------");

        areaRegistros.appendText(registro);
        mostrarAlerta(Alert.AlertType.INFORMATION, "Registro guardado",
                "El estudiante fue agregado correctamente.");
        limpiarCampos();
    }

    @FXML
    private void limpiarCampos() {
        txtCarnet.clear();
        txtNombres.clear();
        txtApellidos.clear();
        txtCarrera.clear();
        txtCorreo.clear();
        txtCarnet.requestFocus();
    }

    @FXML
    private void volverAlMenu() {
        try {
            Stage stage = (Stage) panelEstudiantes.getScene().getWindow();
            App.mostrarVista(stage, "/fxml/menu-principal.fxml", "Asignación Evaluada #1", 900, 600);
        } catch (IOException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error de navegación",
                    "No fue posible regresar al menú principal.");
        }
    }

    private boolean hayCamposVacios() {
        return txtCarnet.getText().trim().isEmpty()
                || txtNombres.getText().trim().isEmpty()
                || txtApellidos.getText().trim().isEmpty()
                || txtCarrera.getText().trim().isEmpty()
                || txtCorreo.getText().trim().isEmpty();
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo, mensaje, ButtonType.OK);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
