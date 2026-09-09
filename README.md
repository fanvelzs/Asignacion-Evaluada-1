# Asignación Evaluada #1

Aplicación de escritorio construida con JavaFX y FXML para registrar estudiantes y consultar información de películas.

## Requisitos

- JDK 17 o superior.
- Apache Maven 3.8 o superior.

## Ejecución

Desde la carpeta raíz del proyecto:

```bash
mvn clean javafx:run
```

También puede importarse como proyecto Maven en IntelliJ IDEA, Eclipse o NetBeans y ejecutar la clase `com.asignacion.App`.
La clase `App` es un lanzador Java convencional, por lo que puede ejecutarse con
el botón verde del IDE sin configurar manualmente el `module-path` de JavaFX.

## Funcionalidades

- Menú principal con tres menús y navegación entre pantallas.
- Menú contextual mediante clic derecho en la pantalla principal.
- Formulario de estudiantes con cinco campos, validación, limpieza y presentación de registros en un `TextArea`.
- Formulario de películas con cinco campos, validación, datos iniciales en un `TableView` y carga de datos al seleccionar una fila.
- Alertas informativas, de validación y de confirmación.
