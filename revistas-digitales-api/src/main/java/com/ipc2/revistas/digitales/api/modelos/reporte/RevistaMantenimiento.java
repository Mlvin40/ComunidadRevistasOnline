/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.revistas.digitales.api.modelos.reporte;

import java.time.LocalDate;

/**
 *
 * @author melvin
 */
public class RevistaMantenimiento {
    
    private String nombreRevista;
    private double costoDiario;
    private LocalDate fechaCreacion;
    private int diasTranscurridos;
    private double costoTotalRevista;

    public RevistaMantenimiento(String nombreRevista, double costoDiario, LocalDate fechaCreacion, int diasTranscurridos, double costoTotalRevista) {
        this.nombreRevista = nombreRevista;
        this.costoDiario = costoDiario;
        this.fechaCreacion = fechaCreacion;
        this.diasTranscurridos = diasTranscurridos;
        this.costoTotalRevista = costoTotalRevista;
    }
    
    
    
    

    public String getNombreRevista() {
        return nombreRevista;
    }

    public void setNombreRevista(String nombreRevista) {
        this.nombreRevista = nombreRevista;
    }

    public double getCostoDiario() {
        return costoDiario;
    }

    public void setCostoDiario(double costoDiario) {
        this.costoDiario = costoDiario;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getDiasTranscurridos() {
        return diasTranscurridos;
    }

    public void setDiasTranscurridos(int diasTranscurridos) {
        this.diasTranscurridos = diasTranscurridos;
    }

    public double getCostoTotalRevista() {
        return costoTotalRevista;
    }

    public void setCostoTotalRevista(double costoTotalRevista) {
        this.costoTotalRevista = costoTotalRevista;
    }
    
    
    
    
    
}
