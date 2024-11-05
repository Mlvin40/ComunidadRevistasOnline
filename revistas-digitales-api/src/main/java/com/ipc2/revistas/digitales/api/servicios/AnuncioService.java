/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.revistas.digitales.api.servicios;

import com.ipc2.revistas.digitales.api.dabase.anuncios.AnuncioDB;
import com.ipc2.revistas.digitales.api.modelos.anuncios.Anuncio;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List<Anuncio> obtenerTodosLosAnuncios() {
        return anuncioDB.obtenerTodosLosAnuncios();
    }

    public List<Anuncio> obtenerAnunciosPorAnunciante(String nombreAnunciante) {
        return anuncioDB.obtenerAnunciosPorAnunciante(nombreAnunciante);
    }

    public Anuncio obtenerAnuncioPorId(int id) {
        return anuncioDB.obtenerAnuncioPorId(id);
    }

    public boolean actualizarAnuncio(int idAnuncio, String tipoAnuncio, String contenidoTexto, InputStream imagenInputStream, String urlVideo, boolean activo, boolean vencido) {
        
        //Esta validacion sirve para que un anuncio que ya vencio no pueda ser editado.
        if (vencido) {
            return false;
        }
        
        // Si el anuncio no esta vencido se procede a actualizarlo.
        switch (tipoAnuncio) {
            case "TEXTO":
                return anuncioDB.actualizarAnuncioTexto(idAnuncio, contenidoTexto, activo);
            case "TEXTO_IMAGEN":
                return anuncioDB.actualizarAnuncioTextoImagen(idAnuncio, contenidoTexto, imagenInputStream, activo);
            case "VIDEO":
                return anuncioDB.actualizarAnuncioVideo(idAnuncio, urlVideo, activo);
            default:
                return false;
        }
    }
}
