/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.revistas.digitales.api.servicios;

import com.ipc2.revistas.digitales.api.dabase.PublicacionDB;
import com.ipc2.revistas.digitales.api.modelos.revista.Publicacion;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author melvin
 */
public class PublicacionService {

    private PublicacionDB publicacionDB = new PublicacionDB();

    public boolean guardarPublicacion(String nombreRevista, String descripcion, InputStream archivoPDFStream, LocalDate fechaPublicacion) {
        if (nombreRevista == null || descripcion == null || archivoPDFStream == null || fechaPublicacion == null) {
            return false;
        }

        return publicacionDB.crearPublicacion(nombreRevista, descripcion, archivoPDFStream, fechaPublicacion);

    }

    public List<Publicacion> obtenerPublicacionesPorRevista(String nombreRevista) {
        List<Publicacion> publicaciones = publicacionDB.obtenerPublicacionesRevista(nombreRevista);
        return publicaciones;
        
    }
}
