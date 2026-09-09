package com.asignacion;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public final class App {

    private App() {
    }

    public static void mostrarVista(Stage stage, String recurso, String titulo,
                                    double ancho, double alto) throws IOException {
        FXMLLoader loader = new FXMLLoader(obtenerRecurso(recurso));
        Parent root = loader.load();
        Scene scene = new Scene(root, ancho, alto);
        scene.getStylesheets().add(obtenerRecurso("/css/estilos.css").toExternalForm());

        stage.setTitle(titulo);
        stage.setScene(scene);
        stage.setMinWidth(760);
        stage.setMinHeight(540);
        stage.centerOnScreen();
        stage.show();
    }

    private static URL obtenerRecurso(String ruta) throws IOException {
        URL recurso = App.class.getResource(ruta);
        if (recurso == null) {
            throw new IOException("No se encontró el recurso: " + ruta);
        }
        return recurso;
    }

    public static void main(String[] args) {
        JavaFxApplication.iniciar(args);
    }
}
