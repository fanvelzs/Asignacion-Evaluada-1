package com.asignacion;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

/** Verificación sencilla de que todas las vistas FXML pueden construirse. */
public final class FxmlSmokeCheck {

    private FxmlSmokeCheck() {
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch terminado = new CountDownLatch(1);
        AtomicReference<Throwable> error = new AtomicReference<>();

        Platform.startup(() -> { });
        Platform.runLater(() -> {
            try {
                verificar("/fxml/menu-principal.fxml");
                verificar("/fxml/registro-estudiantes.fxml");
                verificar("/fxml/registro-peliculas.fxml");
            } catch (Throwable throwable) {
                error.set(throwable);
                throwable.printStackTrace();
            } finally {
                terminado.countDown();
                Platform.exit();
            }
        });

        terminado.await();
        if (error.get() != null) {
            System.exit(1);
        }
    }

    private static void verificar(String recurso) throws Exception {
        FXMLLoader loader = new FXMLLoader(App.class.getResource(recurso));
        loader.load();
        System.out.println("FXML correcto: " + recurso);
    }
}
