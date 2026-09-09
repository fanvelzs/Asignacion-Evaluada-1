package com.asignacion;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clase interna de arranque de JavaFX.
 *
 * App se mantiene como un lanzador Java convencional para evitar el error
 * "JavaFX runtime components are missing" al ejecutarla directamente desde
 * IntelliJ IDEA.
 */
public class JavaFxApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        App.mostrarVista(stage, "/fxml/menu-principal.fxml",
                "Asignación Evaluada #1", 900, 600);
    }

    static void iniciar(String[] args) {
        launch(JavaFxApplication.class, args);
    }
}
