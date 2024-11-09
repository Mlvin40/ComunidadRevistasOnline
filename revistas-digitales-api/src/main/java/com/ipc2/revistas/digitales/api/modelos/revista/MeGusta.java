package com.ipc2.revistas.digitales.api.modelos.revista;

import java.time.LocalDate;

public class MeGusta {

    private int id;
    private String nombreRevista;
    private String nombreUsuario;
    private LocalDate fechaMeGusta;

    //Constructor para recuperar un Me Gusta de la DB
    public MeGusta(int id, String nombreRevista, String nombreUsuario, LocalDate fechaMeGusta) {
        this.id = id;
        this.nombreRevista = nombreRevista;
        this.nombreUsuario = nombreUsuario;
        this.fechaMeGusta = fechaMeGusta;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFechaMeGusta() {
        return fechaMeGusta;
    }

    public void setFechaMeGusta(LocalDate fechaMeGusta) {
        this.fechaMeGusta = fechaMeGusta;
    }

}
