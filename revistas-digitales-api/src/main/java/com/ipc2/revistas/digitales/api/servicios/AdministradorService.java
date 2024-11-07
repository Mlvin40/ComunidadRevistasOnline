/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.revistas.digitales.api.servicios;

import com.ipc2.revistas.digitales.api.dabase.AdministradorDB;
import com.ipc2.revistas.digitales.api.dabase.ComentarioDB;
import com.ipc2.revistas.digitales.api.dabase.MeGustaDB;
import com.ipc2.revistas.digitales.api.dabase.RevistaDB;
import com.ipc2.revistas.digitales.api.modelos.revista.Comentario;
import com.ipc2.revistas.digitales.api.modelos.revista.Revista;
import java.util.List;

/**
 *
 * @author melvin
 */
public class AdministradorService {

    private AdministradorDB administradorDB = new AdministradorDB();
    private MeGustaDB meGustaDB = new MeGustaDB();
    private ComentarioDB comentarioDB = new ComentarioDB();

    public boolean actualizarPrecioRevista(String nombreRevista, Double nuevoPrecio) {

        // Validar que el precio que se quiere actualizar no sea negativo
        if (nuevoPrecio < 0) {
            return false;
        }
        return administradorDB.actualizarPrecioRevista(nombreRevista, nuevoPrecio);
    }

    public List<Revista> obtenerRevistas() {
        // Obtener la lista de revistas
        List<Revista> revistas = administradorDB.obtenerTodasLasRevistas();

        for (Revista revista : revistas) {
            int cantidadLikes = meGustaDB.obtenerCantidadMeGustaPorRevista(revista.getNombre());
            List<Comentario> comentarios = comentarioDB.obtenerComentariosPorRevista(revista.getNombre());

            // Añadir cantidad de likes y comentarios a la revista
            revista.setLikes(cantidadLikes);
            revista.setComentarios(comentarios);
        }
        
        return revistas;
        
    }
}
