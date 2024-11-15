/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.revistas.digitales.api.controladores;

import com.ipc2.revistas.digitales.api.dabase.reportes.TablaReporteGanancia;
import com.ipc2.revistas.digitales.api.modelos.anuncios.AnuncioComprado;
import com.ipc2.revistas.digitales.api.modelos.reporte.ReporteEfectividadAnuncio;
import com.ipc2.revistas.digitales.api.modelos.response.ExitoResponse;
import com.ipc2.revistas.digitales.api.modelos.revista.Revista;
import com.ipc2.revistas.digitales.api.servicios.AdministradorService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 *
 * @author melvin
 */
@Path("/administrador")
public class AdministradorController {

    private final AdministradorService administradorService = new AdministradorService();

    @GET
    @Path("/obtenerRevistas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerRevistas() {
        List<Revista> revistas = administradorService.obtenerRevistas();
        return Response.ok(revistas).build();
    }

    // Metodo para actualizar el precio de una revista en la base de datos 
    @POST
    @Path("/actualizarPrecioRevista")
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarPrecioRevista(
            @QueryParam("nombreRevista") String nombreRevista,
            @QueryParam("nuevoPrecio") Double nuevoPrecio) {

        boolean actualizado = administradorService.actualizarPrecioRevista(nombreRevista, nuevoPrecio);

        //Enviar al respuesta en un formato JSON
        ExitoResponse exitoResponse = new ExitoResponse();
        if (actualizado) {
            return Response.ok(exitoResponse).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Error al actualizar el precio.")
                    .build();
        }
    }

    @GET
    @Path("/obtenerCostoGlobalRevista")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerCostoGlobal() {
        double precioActual = administradorService.precioGlobalRevistas();
        return Response.ok(precioActual).build();

    }

    @POST
    @Path("/actualizarCostoGlobalRevista")
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarCostoGlobal(@QueryParam("nuevoPrecio") Double nuevoPrecio) {

        boolean actualizado = administradorService.actualizarCostoGlobal(nuevoPrecio);
        if (actualizado) {
            ExitoResponse exitoResponse = new ExitoResponse();
            return Response.ok(exitoResponse).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al actualizar el precio.")
                    .build();
        }
    }

    @POST
    @Path("/actualizarCostoAnuncio")
    public Response actualizarCostoAnuncio(
            @QueryParam("tipoAnuncio") String tipoAnuncio,
            @QueryParam("nuevoCosto") double nuevoCosto) {

        // Llamar al método para actualizar el precio del anuncio en la base de datos
        boolean exito = administradorService.actualizarPrecioAnuncios(tipoAnuncio, nuevoCosto);

        if (exito) {
            ExitoResponse exitoResponse = new ExitoResponse();
            return Response.ok(exitoResponse).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al actualizar el costo.")
                    .build();
        }
    }

    @GET
    @Path("/costoOcultacionAnuncio")
    public Response obtenerCostoOcultacion() {
        double costoActual = administradorService.obtenerCostoOcultacion();
        return Response.ok(costoActual).build();
    }

    @POST
    @Path("/ActualizarcostoOcultacionAnuncio")
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarCostoOcultacion(@QueryParam("nuevoPrecio") Double nuevoPrecio) {

        boolean actualizado = administradorService.actualizarCostoOcultacion(nuevoPrecio);
        if (actualizado) {
            ExitoResponse exitoResponse = new ExitoResponse();
            return Response.ok(exitoResponse).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al actualizar el precio.")
                    .build();
        }
    }

    @GET
    @Path("/reporteAnunciosFiltro")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerReporteAnunciosFiltro(
            @QueryParam("fechaInicio") String fechaInicio,
            @QueryParam("fechaFin") String fechaFin,
            @QueryParam("tipoAnuncio") String tipoAnuncio) {

        List<AnuncioComprado> anunciosFiltrados = administradorService.obtenerAnunciosCompradosFiltro(fechaInicio, fechaFin, tipoAnuncio);
        return Response.ok(anunciosFiltrados).build();
    }

    @GET
    @Path("/reporteGanancias")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerReporteGanancias(
            @QueryParam("fechaInicio") String fechaInicio,
            @QueryParam("fechaFin") String fechaFin) {

        TablaReporteGanancia reporte = administradorService.obtenerReporteGanancias(fechaInicio, fechaFin);

        return Response.ok(reporte).build();
    }

    @GET
    @Path("/reporteEfectividad")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerReporteEfectividad(
            @QueryParam("fechaInicio") String fechaInicio,
            @QueryParam("fechaFin") String fechaFin) {

        List<ReporteEfectividadAnuncio> reporte = administradorService.obtenerReporteEfectividad(fechaInicio, fechaFin);

        return Response.ok(reporte).build();
    }
}
