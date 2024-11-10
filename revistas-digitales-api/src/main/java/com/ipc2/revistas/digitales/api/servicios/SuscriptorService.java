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
import com.ipc2.revistas.digitales.api.validadores.ValidadorComentarioLike;

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
    private ValidadorComentarioLike validadorComentarioLike = new ValidadorComentarioLike();

    //Obtener revistas disponibles que estan para una suscripcion
    public List<Revista> obtenerRevistasParaSuscriptor(String nombreUsuario) {

        List<String> revistasSuscritas = suscriptorDB.obtenerRevistasSuscritas(nombreUsuario);
        List<Revista> revistasDisponibles = revistaDB.obtenerRevistasDisponibles(revistasSuscritas);

        revistasDisponibles = validadorComentarioLike.agregarComentariosYLikes(revistasDisponibles);

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
        revistasSuscritas = validadorComentarioLike.agregarComentariosYLikes(revistasSuscritas);

        return revistasSuscritas;
    }

    public boolean darLikeRevista(String nombreRevista, String nombreSuscriptor) {
        
        // Por si hay algun error al obtener el nombre del usuario desde el token
        if (nombreSuscriptor == null) {
            return false;
        }

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

    public List<Revista> revistasCategoria(String categoria, String suscriptor) {

        List<Revista> revistas = obtenerRevistasParaSuscriptor(suscriptor); //Las que estan disponibles
        List<String> categoriasAMostrar = revistaDB.obtenerRevistasPorCategoria(categoria);
        //Filtrar las revistas que estan disponibles y que pertenecen a la categoria
        revistas.removeIf(revista -> !categoriasAMostrar.contains(revista.getNombre()));

        return revistas;
        
    }
}
