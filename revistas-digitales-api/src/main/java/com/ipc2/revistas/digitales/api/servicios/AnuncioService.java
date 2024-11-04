/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.revistas.digitales.api.servicios;

import com.ipc2.revistas.digitales.api.dabase.AnuncioDB;
import java.io.InputStream;
import java.time.LocalDate;

/**
 *
 * @author melvin
 */
public class AnuncioService {

    private AnuncioDB anuncioDB = new AnuncioDB();

    public boolean comprarAnuncio(String anuciante, String tipoAnuncio, String contenidoTexto, InputStream imagenInputStream, String urlVideo, Integer duracion, String fechaInicio, Integer totalAPagar) {
        //Paso numero 1: Converir la fecha a localdate y darle formato yyyy-MM-dd
        LocalDate fechaInicioLocalDate = LocalDate.parse(fechaInicio);
        

        switch (tipoAnuncio) {
            case "TEXTO":
                return anuncioDB.crearAnuncioTexto(contenidoTexto, anuciante, fechaInicioLocalDate, duracion);
            case "TEXTO_IMAGEN":

                return anuncioDB.crearAnuncioTextoImagen(contenidoTexto, imagenInputStream, anuciante, fechaInicioLocalDate, duracion);
            case "VIDEO":
                return anuncioDB.crearAnuncioVideo(urlVideo, anuciante, fechaInicioLocalDate, duracion);
            default:
                return false;
        }
    }
}
