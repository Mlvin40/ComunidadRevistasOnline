/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.revistas.digitales.api.validadores;

import com.ipc2.revistas.digitales.api.dabase.ComentarioDB;
import com.ipc2.revistas.digitales.api.dabase.MeGustaDB;
import com.ipc2.revistas.digitales.api.modelos.revista.Comentario;
import com.ipc2.revistas.digitales.api.modelos.revista.Revista;
import java.util.List;

/**
 *
 * @author melvin
 */
public class ValidadorComentarioLike {

    private MeGustaDB meGustaDB = new MeGustaDB();
    private ComentarioDB comentarioDB = new ComentarioDB();

    //Este metodo se utiliza para agregarle los atrubutos like, comentario a un grupo de revistas
    public List<Revista> agregarComentariosYLikes(List<Revista> revistas) {
        for (Revista revista : revistas) {
            int cantidadLikes = meGustaDB.obtenerCantidadMeGustaPorRevista(revista.getNombre());
            List<Comentario> comentarios = comentarioDB.obtenerComentariosPorRevista(revista.getNombre());

            // Añadir cantidad de likes y comentarios a la revista
            revista.setLikes(cantidadLikes);
            revista.setComentarios(comentarios);
        }

        return revistas;

    }

    //Este metodo obtiene los comentarios y likes de una sola revista
    public Revista comentariosLikesIndividual(Revista revista) {
        int cantidadLikes = meGustaDB.obtenerCantidadMeGustaPorRevista(revista.getNombre());
        List<Comentario> comentarios = comentarioDB.obtenerComentariosPorRevista(revista.getNombre());

        revista.setLikes(cantidadLikes);
        revista.setComentarios(comentarios);

        return revista;

    }
}
