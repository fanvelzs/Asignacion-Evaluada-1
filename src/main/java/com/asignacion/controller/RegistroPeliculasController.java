package com.asignacion.controller;

import com.asignacion.App;
import com.asignacion.model.Pelicula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegistroPeliculasController implements Initializable {

    @FXML private BorderPane panelPeliculas;
    @FXML private TextField txtTitulo;
    @FXML private TextField txtDirector;
    @FXML private TextField txtGenero;
    @FXML private TextField txtAnio;
    @FXML private TextField txtDuracion;
    @FXML private TableView<Pelicula> tablaPeliculas;
    @FXML private TableColumn<Pelicula, String> colTitulo;
    @FXML private TableColumn<Pelicula, String> colDirector;
    @FXML private TableColumn<Pelicula, String> colGenero;
    @FXML private TableColumn<Pelicula, String> colAnio;
    @FXML private TableColumn<Pelicula, String> colDuracion;

    private final ObservableList<Pelicula> peliculas = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tablaPeliculas.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colDirector.setCellValueFactory(new PropertyValueFactory<>("director"));
        colGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
        colAnio.setCellValueFactory(new PropertyValueFactory<>("anio"));
        colDuracion.setCellValueFactory(new PropertyValueFactory<>("duracion"));

        peliculas.addAll(
                new Pelicula("Interstellar", "Christopher Nolan", "Ciencia ficción", "2014", "169 min"),
                new Pelicula("Coco", "Lee Unkrich", "Animación", "2017", "105 min"),
                new Pelicula("Parasite", "Bong Joon-ho", "Drama", "2019", "132 min"),
                new Pelicula("The Matrix", "Lana y Lilly Wachowski", "Ciencia ficción", "1999", "136 min")
        );
        tablaPeliculas.setItems(peliculas);

        tablaPeliculas.getSelectionModel().selectedItemProperty().addListener(
                (observable, anterior, seleccionada) -> cargarPelicula(seleccionada));
    }

    private void cargarPelicula(Pelicula pelicula) {
        if (pelicula == null) {
            return;
        }
        txtTitulo.setText(pelicula.getTitulo());
        txtDirector.setText(pelicula.getDirector());
        txtGenero.setText(pelicula.getGenero());
        txtAnio.setText(pelicula.getAnio());
        txtDuracion.setText(pelicula.getDuracion());
    }

    @FXML
    private void procesarPelicula() {
        if (hayCamposVacios()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Datos incompletos",
                    "Debe completar los cinco campos antes de procesar la película.");
            return;
        }

        if (!txtAnio.getText().trim().matches("\\d{4}")) {
            mostrarAlerta(Alert.AlertType.WARNING, "Año no válido",
                    "El año debe contener exactamente cuatro dígitos.");
            txtAnio.requestFocus();
            return;
        }

        String informacion = String.format(
                "Título: %s%nDirector: %s%nGénero: %s%nAño: %s%nDuración: %s",
                txtTitulo.getText().trim(), txtDirector.getText().trim(),
                txtGenero.getText().trim(), txtAnio.getText().trim(),
                txtDuracion.getText().trim());
        mostrarAlerta(Alert.AlertType.INFORMATION, "Información de la película", informacion);
    }

    @FXML
    private void limpiarCampos() {
        txtTitulo.clear();
        txtDirector.clear();
        txtGenero.clear();
        txtAnio.clear();
        txtDuracion.clear();
        tablaPeliculas.getSelectionModel().clearSelection();
        txtTitulo.requestFocus();
    }

    @FXML
    private void volverAlMenu() {
        try {
            Stage stage = (Stage) panelPeliculas.getScene().getWindow();
            App.mostrarVista(stage, "/fxml/menu-principal.fxml", "Asignación Evaluada #1", 900, 600);
        } catch (IOException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error de navegación",
                    "No fue posible regresar al menú principal.");
        }
    }

    private boolean hayCamposVacios() {
        return txtTitulo.getText().trim().isEmpty()
                || txtDirector.getText().trim().isEmpty()
                || txtGenero.getText().trim().isEmpty()
                || txtAnio.getText().trim().isEmpty()
                || txtDuracion.getText().trim().isEmpty();
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo, mensaje, ButtonType.OK);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
