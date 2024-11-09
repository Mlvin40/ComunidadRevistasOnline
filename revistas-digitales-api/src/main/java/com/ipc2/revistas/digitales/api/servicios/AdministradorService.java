/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.revistas.digitales.api.servicios;

import com.ipc2.revistas.digitales.api.dabase.AdministradorDB;
import com.ipc2.revistas.digitales.api.dabase.reportes.GeneradorRevistaPopular;
import com.ipc2.revistas.digitales.api.modelos.reporte.RevistaPopular;
import com.ipc2.revistas.digitales.api.modelos.revista.Revista;
import com.ipc2.revistas.digitales.api.validadores.ValidadorComentarioLike;
import java.util.List;

/**
 *
 * @author melvin
 */
public class AdministradorService {

    private AdministradorDB administradorDB = new AdministradorDB();
    private ValidadorComentarioLike validadorComentarioLike = new ValidadorComentarioLike();
  
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

        //Se agregan los comentarios y likes
        revistas = validadorComentarioLike.agregarComentariosYLikes(revistas);

        return revistas;
    }

    public double precioGlobalRevistas() {
        double precioActual = administradorDB.obtenerPrecioGlobalRevista();
        return precioActual;

    }

    public boolean actualizarCostoGlobal(double nuevoPrecio) {
        if (nuevoPrecio < 0) {
            return false;
        }
        return administradorDB.actualizarPrecioGlobalRevista(nuevoPrecio);
    }

    public boolean actualizarPrecioAnuncios(String tipo, double nuevoPrecio) {

        if (nuevoPrecio < 0) {
            return false;
        }

        return administradorDB.actualizarPrecioAnuncios(tipo, nuevoPrecio);

    }

    public double obtenerCostoOcultacion() {
        double costoActual = administradorDB.obtenerPrecioOcultacionAnuncio();
        return costoActual;
    }

    public boolean actualizarCostoOcultacion(double nuevoPrecio) {
        if (nuevoPrecio < 0) {
            return false;
        }
        return administradorDB.actualizarPrecioOcultacionAnuncio(nuevoPrecio);
    }

}
