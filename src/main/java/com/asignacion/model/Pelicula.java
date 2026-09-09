package com.asignacion.model;

public class Pelicula {
    private final String titulo;
    private final String director;
    private final String genero;
    private final String anio;
    private final String duracion;

    public Pelicula(String titulo, String director, String genero, String anio, String duracion) {
        this.titulo = titulo;
        this.director = director;
        this.genero = genero;
        this.anio = anio;
        this.duracion = duracion;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDirector() {
        return director;
    }

    public String getGenero() {
        return genero;
    }

    public String getAnio() {
        return anio;
    }

    public String getDuracion() {
        return duracion;
    }
}
