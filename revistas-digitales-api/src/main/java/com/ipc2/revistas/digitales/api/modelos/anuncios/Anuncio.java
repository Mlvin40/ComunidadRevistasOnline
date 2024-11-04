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
public class Anuncio {

    private int idAnuncio;               // ID del anuncio
    private String tipoAnuncio;          // Tipo de anuncio (TEXTO, TEXTO_IMAGEN, VIDEO)
    private String contenidoTexto;        // Contenido del anuncio en texto
    private byte[] imagen;                // Imagen del anuncio (si aplica)
    private String urlVideo;              // URL del video (si aplica)
    private String nombreAnunciante;      // Nombre del anunciante
    private LocalDate fechaInicio;        // Fecha de inicio del anuncio
    private String duracion;              // Duración del anuncio (1_DIA, 3_DIAS, 1_SEMANA, 2_SEMANAS)
    private LocalDate fechaExpiracion;    // Fecha de expiración del anuncio
    private boolean vencido;              // Si el anuncio está vencido
    private boolean activo;               // Si el anuncio está activo

    // Constructor Para recuperar un anuncio en la base de datos. 
    public Anuncio(int idAnuncio, String tipoAnuncio, String contenidoTexto, byte[] imagen,
            String urlVideo, String nombreAnunciante, LocalDate fechaInicio,
            String duracion, LocalDate fechaExpiracion, boolean vencido, boolean activo) {
        this.idAnuncio = idAnuncio;
        this.tipoAnuncio = tipoAnuncio;
        this.contenidoTexto = contenidoTexto;
        this.imagen = imagen;
        this.urlVideo = urlVideo;
        this.nombreAnunciante = nombreAnunciante;
        this.fechaInicio = fechaInicio;
        this.duracion = duracion;
        this.fechaExpiracion = fechaExpiracion;
        this.vencido = vencido;
        this.activo = activo;
    }

    // Getters y Setters
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

    public String getContenidoTexto() {
        return contenidoTexto;
    }

    public void setContenidoTexto(String contenidoTexto) {
        this.contenidoTexto = contenidoTexto;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getUrlVideo() {
        return urlVideo;
    }

    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }

    public String getNombreAnunciante() {
        return nombreAnunciante;
    }

    public void setNombreAnunciante(String nombreAnunciante) {
        this.nombreAnunciante = nombreAnunciante;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public LocalDate getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(LocalDate fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public boolean isVencido() {
        return vencido;
    }

    public void setVencido(boolean vencido) {
        this.vencido = vencido;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}

