/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.revistas.digitales.api.modelos.revista;

import java.time.LocalDate;

/**
 *
 * @author melvin
 */
public class Suscripcion {

    private int id;
    private String nombreUsuario;
    private String nombreRevista;
    private LocalDate fechaSuscripcion;
    private boolean estado;

    public Suscripcion(int id, String nombreUsuario, String nombreRevista, LocalDate fechaSuscripcion, boolean estado) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.nombreRevista = nombreRevista;
        this.fechaSuscripcion = fechaSuscripcion;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombreRevista() {
        return nombreRevista;
    }

    public void setNombreRevista(String nombreRevista) {
        this.nombreRevista = nombreRevista;
    }

    public LocalDate getFechaSuscripcion() {
        return fechaSuscripcion;
    }

    public void setFechaSuscripcion(LocalDate fechaSuscripcion) {
        this.fechaSuscripcion = fechaSuscripcion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    
    

}
