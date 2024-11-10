/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.revistas.digitales.api.servicios;

import com.ipc2.revistas.digitales.api.dabase.AdministradorDB;
import com.ipc2.revistas.digitales.api.dabase.reportes.ReporteGananciasDB;
import com.ipc2.revistas.digitales.api.dabase.reportes.TablaReporteGanancia;
import com.ipc2.revistas.digitales.api.modelos.anuncios.AnuncioComprado;
import com.ipc2.revistas.digitales.api.modelos.reporte.RevistaMantenimiento;
import com.ipc2.revistas.digitales.api.modelos.revista.Revista;
import com.ipc2.revistas.digitales.api.validadores.ValidadorComentarioLike;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    public List<AnuncioComprado> obtenerAnunciosCompradosFiltro(String fechaInicio, String fechaFin, String tipoAnuncio) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");  // Ajusta el patrón a yyyy-MM-dd
        LocalDate fechaIn = LocalDate.parse(fechaInicio, formatter);
        LocalDate fechaFn = LocalDate.parse(fechaFin, formatter);

        return administradorDB.obtenerAnunciosCompradosFiltro(fechaIn, fechaFn, tipoAnuncio);
    }

    public TablaReporteGanancia obtenerReporteGanancias(String fechaInicio, String fechaFin) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaIn = LocalDate.parse(fechaInicio, formatter);
        LocalDate fechaFn = LocalDate.parse(fechaFin, formatter);

        ReporteGananciasDB reporteGananciasDB = new ReporteGananciasDB();
        return reporteGananciasDB.obtenertTabla(fechaIn, fechaFn);

    }
}
