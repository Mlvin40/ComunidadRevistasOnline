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
public class Publicacion {

    private String nombreRevista;
    private String descripcion;
    private LocalDate fechaPublicacion;
    private byte[] archivoPDF;

    public Publicacion(String nombreRevista, String descripcion, LocalDate fechaPublicacion, byte[] archivoPDF) {
        this.nombreRevista = nombreRevista;
        this.descripcion = descripcion;
        this.fechaPublicacion = fechaPublicacion;
        this.archivoPDF = archivoPDF;
    }
    
    public String getNombreRevista() {
        return nombreRevista;
    }

    public void setNombreRevista(String nombreRevista) {
        this.nombreRevista = nombreRevista;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public byte[] getArchivoPDF() {
        return archivoPDF;
    }

    public void setArchivoPDF(byte[] archivoPDF) {
        this.archivoPDF = archivoPDF;
    }

}
