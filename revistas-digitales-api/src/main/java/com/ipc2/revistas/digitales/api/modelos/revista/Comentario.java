package com.ipc2.revistas.digitales.api.modelos.revista;

import java.time.LocalDate;

public class Comentario {

    private int id;
    private String nombreRevista;
    private String nombreUsuario;
    private String comentario;
    private LocalDate fechaComentario;

    //Constructor para recuperar un comentario de la DB
    public Comentario(int id, String nombreRevista, String nombreUsuario, String comentario, LocalDate fechaComentario) {
        this.id = id;
        this.nombreRevista = nombreRevista;
        this.nombreUsuario = nombreUsuario;
        this.comentario = comentario;
        this.fechaComentario = fechaComentario;
    }

    public String getNombreRevista() {
        return nombreRevista;
    }

    public void setNombreRevista(String nombreRevista) {
        this.nombreRevista = nombreRevista;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFechaComentario() {
        return fechaComentario;
    }

    public void setFechaComentario(LocalDate fechaComentario) {
        this.fechaComentario = fechaComentario;
    }

}
