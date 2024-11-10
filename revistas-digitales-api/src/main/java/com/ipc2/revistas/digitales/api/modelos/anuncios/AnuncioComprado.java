/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.revistas.digitales.api.modelos.anuncios;

import java.time.LocalDate;

/**
 *
 * @author melvin
 */
public class AnuncioComprado {

    private  int id;
    private  String nombreAnunciante;
    private  LocalDate fechaPago;
    private  double pago;
    private  String tipoAnuncio;

    public AnuncioComprado(int id, String nombreAnunciante, LocalDate fechaPago, double pago, String tipoAnuncio) {
        this.id = id;
        this.nombreAnunciante = nombreAnunciante;
        this.fechaPago = fechaPago;
        this.pago = pago;
        this.tipoAnuncio = tipoAnuncio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreAnunciante() {
        return nombreAnunciante;
    }

    public void setNombreAnunciante(String nombreAnunciante) {
        this.nombreAnunciante = nombreAnunciante;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }

    public double getPago() {
        return pago;
    }

    public void setPago(double pago) {
        this.pago = pago;
    }

    public String getTipoAnuncio() {
        return tipoAnuncio;
    }

    public void setTipoAnuncio(String tipoAnuncio) {
        this.tipoAnuncio = tipoAnuncio;
    }

    
    
}
