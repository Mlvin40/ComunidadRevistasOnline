/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.revistas.digitales.api.servicios;

import com.ipc2.revistas.digitales.api.dabase.ComentarioDB;
import com.ipc2.revistas.digitales.api.dabase.EditorDB;
import com.ipc2.revistas.digitales.api.dabase.MeGustaDB;
import com.ipc2.revistas.digitales.api.dabase.RevistaDB;
import com.ipc2.revistas.digitales.api.modelos.revista.Comentario;
import com.ipc2.revistas.digitales.api.modelos.revista.Comentario;
import com.ipc2.revistas.digitales.api.modelos.revista.Revista;
import com.ipc2.revistas.digitales.api.modelos.revista.Revista;
import jakarta.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author melvin
 */
public class RevistaService {

    private RevistaDB revistaDB = new RevistaDB();
    private ComentarioDB comentarioDB = new ComentarioDB();
    private MeGustaDB meGustaDB = new MeGustaDB();

    public boolean crearRevista(String nombre, String descripcion, String categoria, String fechaCreacionStr, String autor) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaCreacion;

        try {
            fechaCreacion = LocalDate.parse(fechaCreacionStr, formatter);
        } catch (DateTimeParseException e) {

            return false;
        }

        // crear una posible revista
        Revista revista = new Revista(nombre, descripcion, categoria, fechaCreacion, autor);
        // se verifica todo lo necesario para ver si una revista se crea o no
        return revistaDB.crearRevista(revista);

    }

    public List<Revista> obtenerRevistasPorAutor(String autor) {
        EditorDB editorDB = new EditorDB();

        List<Revista> revistas = editorDB.obtenerRevistasPorAutor(autor);

        for (Revista revista : revistas) {
            int cantidadLikes = meGustaDB.obtenerCantidadMeGustaPorRevista(revista.getNombre());
            List<Comentario> comentarios = comentarioDB.obtenerComentariosPorRevista(revista.getNombre());

            // Añadir cantidad de likes y comentarios a la revista
            revista.setLikes(cantidadLikes);
            revista.setComentarios(comentarios);
        }

        return revistas;
    }

    public Revista obtenerRevistaPorNombre(String nombre) {
        Revista revista = revistaDB.obtenerRevista(nombre);
        int cantidadLikes = meGustaDB.obtenerCantidadMeGustaPorRevista(revista.getNombre());
        List<Comentario> comentarios = comentarioDB.obtenerComentariosPorRevista(revista.getNombre());

        // Añadir cantidad de likes y comentarios a la revista para no de error el frontend por campos vacios
        revista.setLikes(cantidadLikes);
        revista.setComentarios(comentarios);

        return revista;
    }

    public boolean actualizarRevista(Revista revistaEditable) { 
        EditorDB editorDB = new EditorDB();
        boolean seActualizo = editorDB.actualizarRevista(revistaEditable);  
        return seActualizo;
    }
}
