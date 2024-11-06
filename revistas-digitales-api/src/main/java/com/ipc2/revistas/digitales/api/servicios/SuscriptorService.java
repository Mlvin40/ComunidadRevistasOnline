/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.revistas.digitales.api.servicios;

import com.ipc2.revistas.digitales.api.dabase.ComentarioDB;
import com.ipc2.revistas.digitales.api.dabase.MeGustaDB;
import com.ipc2.revistas.digitales.api.dabase.RevistaDB;
import com.ipc2.revistas.digitales.api.dabase.SuscriptorDB;
import com.ipc2.revistas.digitales.api.modelos.revista.Comentario;
import com.ipc2.revistas.digitales.api.modelos.revista.Revista;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author melvin
 */
public class SuscriptorService {

    private SuscriptorDB suscriptorDB = new SuscriptorDB();
    private RevistaDB revistaDB = new RevistaDB();
    private ComentarioDB comentarioDB = new ComentarioDB();
    private MeGustaDB meGustaDB = new MeGustaDB();

    public List<Revista> obtenerRevistasParaSuscriptor(String nombreUsuario) {

        List<String> revistasSuscritas = suscriptorDB.obtenerRevistasSuscritas(nombreUsuario);
        List<Revista> revistasDisponibles = revistaDB.obtenerRevistasDisponibles(revistasSuscritas);

        revistasDisponibles = agregarComentariosYLikes(revistasDisponibles);

        return revistasDisponibles;
    }

    public boolean suscribirARevista(String nombreUsuario, String nombreRevista, String fechaSuscripcion) {
        LocalDate fechaDate = LocalDate.parse(fechaSuscripcion);

        // Validar si el usuario ya está suscrito a la revista
        if (suscriptorDB.verificarEstaSuscrito(nombreUsuario, nombreRevista)) {
            return false;
        }
        // Crear la nueva suscripción
        return suscriptorDB.suscribirUsuarioARevista(nombreUsuario, nombreRevista, fechaDate);
    }
    
   
    //Metodo que devuelve las revistas a las que el usuario esta suscrito y puede visualizar
    public List<Revista> obtenerRevistasSuscritasPorUsuario(String nombreUsuario) {

        List<Revista> revistasSuscritas = suscriptorDB.obtenerRevistasPorSuscriptor(nombreUsuario);
        revistasSuscritas = agregarComentariosYLikes(revistasSuscritas);

        return revistasSuscritas;
    }

    //Este metodo se utiliza para agregarle los atrubutos like, comentario a un grupo de revistas
    private List<Revista> agregarComentariosYLikes(List<Revista> revistas) {
        for (Revista revista : revistas) {
            int cantidadLikes = meGustaDB.obtenerCantidadMeGustaPorRevista(revista.getNombre());
            List<Comentario> comentarios = comentarioDB.obtenerComentariosPorRevista(revista.getNombre());

            // Añadir cantidad de likes y comentarios a la revista
            revista.setLikes(cantidadLikes);
            revista.setComentarios(comentarios);
        }
        return revistas;
    }

    public boolean darLikeRevista(String nombreRevista, String nombreSuscriptor) {
        try {
            meGustaDB.darLike(nombreRevista, nombreSuscriptor);
            return true;
        } catch (SQLException e) {
            return false;
        }

    }

    public void realizarComentario(String nombreRevista, String textoComentario, String nombreSuscriptor) {

        //almacenar el comentario en la base de datos
        ComentarioDB comentarioDB = new ComentarioDB();
        comentarioDB.guardarComentario(nombreRevista, nombreSuscriptor, textoComentario);

    }
}
