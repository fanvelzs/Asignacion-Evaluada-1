package com.asignacion.controller;

import com.asignacion.App;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuPrincipalController {

    @FXML
    private BorderPane panelPrincipal;

    @FXML
    private ContextMenu menuContextual;

    @FXML
    private void mostrarMenuContextual(ContextMenuEvent event) {
        menuContextual.show(panelPrincipal, event.getScreenX(), event.getScreenY());
        event.consume();
    }

    @FXML
    private void abrirRegistroEstudiantes() {
        cambiarVista("/fxml/registro-estudiantes.fxml", "Registro de estudiantes", 900, 650);
    }

    @FXML
    private void abrirRegistroPeliculas() {
        cambiarVista("/fxml/registro-peliculas.fxml", "Registro de películas", 1050, 700);
    }

    @FXML
    private void mostrarDesarrollador() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información del desarrollador");
        alert.setHeaderText("Asignación Evaluada #1");
        alert.setContentText("Desarrollado por: fanvelzs\n"
                + "Asignatura: Desarrollo de Aplicaciones de Escritorio\n"
                + "Tecnología: JavaFX y FXML");
        alert.showAndWait();
    }

    @FXML
    private void mostrarAyuda() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ayuda");
        alert.setHeaderText("¿Cómo utilizar la aplicación?");
        alert.setContentText("Seleccione una opción del menú Catálogo o haga clic derecho "
                + "sobre el área principal para acceder a los formularios.");
        alert.showAndWait();
    }

    @FXML
    private void salirAplicacion(ActionEvent event) {
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION,
                "¿Desea cerrar la aplicación?", ButtonType.YES, ButtonType.NO);
        confirmacion.setTitle("Confirmar salida");
        confirmacion.setHeaderText(null);
        confirmacion.showAndWait().ifPresent(respuesta -> {
            if (respuesta == ButtonType.YES) {
                Platform.exit();
            }
        });
    }

    private void cambiarVista(String recurso, String titulo, double ancho, double alto) {
        try {
            Stage stage = (Stage) panelPrincipal.getScene().getWindow();
            App.mostrarVista(stage, recurso, titulo, ancho, alto);
        } catch (IOException e) {
            e.printStackTrace();
            mostrarError("No fue posible abrir la pantalla solicitada.");
        }
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR, mensaje, ButtonType.OK);
        alert.setTitle("Error de navegación");
        alert.setHeaderText("Ocurrió un error");
        alert.showAndWait();
    }
}
