package com.ipc2.revistas.digitales.api.modelos.revista;

public class Comentario {

    
    private String nombreRevista;
    private String nombreUsuario;
    private String comentario;
    private String fechaComentario;

    
    //Constructor para agregar un comentario
    public Comentario(String nombreRevista, String nombreUsuario, String comentario, String fechaComentario) {
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

    public String getFechaComentario() {
        return fechaComentario;
    }

    public void setFechaComentario(String fechaComentario) {
        this.fechaComentario = fechaComentario;
    }
}
