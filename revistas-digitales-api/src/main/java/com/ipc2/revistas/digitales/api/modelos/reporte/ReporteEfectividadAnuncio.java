/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.revistas.digitales.api.modelos.reporte;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author melvin
 */
public class ReporteEfectividadAnuncio {

    private String nombreAnunciante;
    private int idAnuncio;
    private String tipoAnuncio;
    private List<String> pathsMostrados;
    private int cantidadMostrado;

    // Constructor
    public ReporteEfectividadAnuncio(String nombreAnunciante, int idAnuncio, String tipoAnuncio, String pathsMostradosStr, int cantidadMostrado) {
        this.nombreAnunciante = nombreAnunciante;
        this.idAnuncio = idAnuncio;
        this.tipoAnuncio = tipoAnuncio;
        // Convertir la cadena concatenada en una lista de paths
        this.pathsMostrados = Arrays.asList(pathsMostradosStr.split(", "));
        this.cantidadMostrado = cantidadMostrado;
    }

    public String getNombreAnunciante() {
        return nombreAnunciante;
    }

    public void setNombreAnunciante(String nombreAnunciante) {
        this.nombreAnunciante = nombreAnunciante;
    }

    public int getIdAnuncio() {
        return idAnuncio;
    }

    public void setIdAnuncio(int idAnuncio) {
        this.idAnuncio = idAnuncio;
    }

    public String getTipoAnuncio() {
        return tipoAnuncio;
    }

    public void setTipoAnuncio(String tipoAnuncio) {
        this.tipoAnuncio = tipoAnuncio;
    }

    public List<String> getPathsMostrados() {
        return pathsMostrados;
    }

    public void setPathsMostrados(List<String> pathsMostrados) {
        this.pathsMostrados = pathsMostrados;
    }

    public int getCantidadMostrado() {
        return cantidadMostrado;
    }

    public void setCantidadMostrado(int cantidadMostrado) {
        this.cantidadMostrado = cantidadMostrado;
    }
    
    

}
