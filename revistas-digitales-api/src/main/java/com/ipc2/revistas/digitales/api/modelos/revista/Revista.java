package com.ipc2.revistas.digitales.api.modelos.revista;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ipc2.revistas.digitales.api.dabase.RevistaDB;
import java.time.LocalDate;
import java.util.List;

public class Revista {

    private String nombre;
    //la descripcion de la revista es el contenido de la revista
    private String descripcion;
    private String categoria;
    private LocalDate fechaCreacion;
    private String autor;
    private double costo;
    private boolean estadoComentar;
    private boolean estadoMeGusta;
    private boolean estadoSuscribirse;

    //Atributos que se asignan con datos de la base de datos
    private int likes;
    private List<Comentario> comentarios;

    //Constructor para crear una revista
    public Revista(String nombre, String descripcion, String categoria, LocalDate fechaCreacion, String autor) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        //la categoria debe de guardarse todo en minisculas para facilitar la busqueda
        this.categoria = categoria.toLowerCase();
        this.fechaCreacion = fechaCreacion;
        this.autor = autor;
        this.costo = establecerCosto();
    }

    //Constructor para recuperar una revista de la base de datos
    public Revista(String nombre, String descripcion, String categoria, LocalDate fechaCreacion, String autor, double costo, boolean estadoComentar, boolean estadoMeGusta, boolean estadoSuscribirse) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.fechaCreacion = fechaCreacion;
        this.autor = autor;
        this.costo = costo;
        this.estadoComentar = estadoComentar;
        this.estadoMeGusta = estadoMeGusta;
        this.estadoSuscribirse = estadoSuscribirse;

    }
    
    // Constructor personalizado con @JsonCreator
    @JsonCreator
    public Revista(
            @JsonProperty("nombre") String nombre,
            @JsonProperty("descripcion") String descripcion,
            @JsonProperty("categoria") String categoria,
            @JsonProperty("estadoComentar") boolean estadoComentar,
            @JsonProperty("estadoMeGusta") boolean estadoMeGusta,
            @JsonProperty("estadoSuscribirse") boolean estadoSuscribirse) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.estadoComentar = estadoComentar;
        this.estadoMeGusta = estadoMeGusta;
        this.estadoSuscribirse = estadoSuscribirse;
    }

    private double establecerCosto() {
        RevistaDB revistaDB = new RevistaDB();
        return revistaDB.establecerPrecioRevistaGlobal();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public boolean isEstadoComentar() {
        return estadoComentar;
    }

    public void setEstadoComentar(boolean estadoComentar) {
        this.estadoComentar = estadoComentar;
    }

    public boolean isEstadoMeGusta() {
        return estadoMeGusta;
    }

    public void setEstadoMeGusta(boolean estadoMeGusta) {
        this.estadoMeGusta = estadoMeGusta;
    }

    public boolean isEstadoSuscribirse() {
        return estadoSuscribirse;
    }

    public void setEstadoSuscribirse(boolean estadoSuscribirse) {
        this.estadoSuscribirse = estadoSuscribirse;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public int getNumeroComentarios() {
        return comentarios.size();
    }

}
