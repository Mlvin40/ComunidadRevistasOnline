/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.revistas.digitales.api.servicios;

import com.ipc2.revistas.digitales.api.dabase.anuncios.*;
import com.ipc2.revistas.digitales.api.modelos.anuncios.Anuncio;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author melvin
 */
public class AnuncioService {

    private final AnuncioDB anuncioDB = new AnuncioDB();
    private final AnuncioTextoDB anuncioTextoDB = new AnuncioTextoDB();
    private final AnuncioTextoImagenDB anuncioTextoImagenDB = new AnuncioTextoImagenDB();
    private final AnuncioVideoDB anuncioVideoDB = new AnuncioVideoDB();
    private final ValidacionesAnuncioDB validacionesAnuncioDB = new ValidacionesAnuncioDB();

    public boolean comprarAnuncio(String anuciante, String tipoAnuncio, String contenidoTexto, InputStream imagenInputStream, String urlVideo, Integer duracion, String fechaInicio, Integer totalAPagar) {
        //Paso numero 1: Converir la fecha a localdate y darle formato yyyy-MM-dd
        LocalDate fechaInicioLocalDate = LocalDate.parse(fechaInicio);

        switch (tipoAnuncio) {
            case "TEXTO":
                return anuncioTextoDB.crearAnuncioTexto(contenidoTexto, anuciante, fechaInicioLocalDate, duracion);
            case "TEXTO_IMAGEN":

                return anuncioTextoImagenDB.crearAnuncioTextoImagen(contenidoTexto, imagenInputStream, anuciante, fechaInicioLocalDate, duracion);
            case "VIDEO":
                return anuncioVideoDB.crearAnuncioVideo(urlVideo, anuciante, fechaInicioLocalDate, duracion);
            default:
                return false;
        }
    }

    // Método para obtener todos los anuncios que se van a mostrar, entonces se deben de quitar los desactivados y vencidos
    public List<Anuncio> obtenerTodosLosAnuncios() {
        List<Anuncio> anuncios = anuncioDB.obtenerTodosLosAnuncios();

        // Eliminar las instancias de anuncios que no estén activos o que ya hayan vencido
        anuncios.removeIf(anuncio -> !anuncio.isActivo() || anuncio.isVencido());

        return anuncios;
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
                return anuncioTextoDB.actualizarAnuncioTexto(idAnuncio, contenidoTexto, activo);
            case "TEXTO_IMAGEN":
                return anuncioTextoImagenDB.actualizarAnuncioTextoImagen(idAnuncio, contenidoTexto, imagenInputStream, activo);
            case "VIDEO":
                return anuncioVideoDB.actualizarAnuncioVideo(idAnuncio, urlVideo, activo);
            default:
                return false;
        }
    }
}
