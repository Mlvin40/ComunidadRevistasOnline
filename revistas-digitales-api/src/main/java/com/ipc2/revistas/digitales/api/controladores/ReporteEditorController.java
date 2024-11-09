/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.revistas.digitales.api.controladores;

import com.ipc2.revistas.digitales.api.modelos.revista.Comentario;
import com.ipc2.revistas.digitales.api.modelos.revista.Revista;
import com.ipc2.revistas.digitales.api.modelos.revista.Suscripcion;
import com.ipc2.revistas.digitales.api.servicios.ReporteEditorService;
import jakarta.ws.rs.GET;
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
@Path("/reportesEditor")
public class ReporteEditorController {

    private ReporteEditorService reporteEditorService = new ReporteEditorService();

    @GET
    @Path("/obtenerComentarios")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerComentarios() {
        List<Comentario> comentarios = reporteEditorService.obtenerTodosLosComentarios();

        if (comentarios != null) {
            return Response.ok(comentarios).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("No se encontraron comentarios").build();
        }
    }

    @GET
    @Path("/obtenerComentariosFiltro")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerComentariosPorRevista(@QueryParam("nombreRevista") String nombreRevista) {
        List<Comentario> comentarios = reporteEditorService.obtenerComentariosPorRevista(nombreRevista);

        if (comentarios != null && !comentarios.isEmpty()) {
            return Response.ok(comentarios).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("No se encontraron comentarios para la revista: " + nombreRevista).build();
        }
    }
    //----------------------------------------//

    @GET
    @Path("/obtenerSuscripciones")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerSuscripciones() {
        List<Suscripcion> suscripciones = reporteEditorService.obtenerTodasLasSuscripciones();

        if (suscripciones != null) {
            return Response.ok(suscripciones).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("No se encontraron comentarios").build();
        }
    }

    @GET
    @Path("/obtenerSuscripcionesFiltro")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerSuscripcionesPorRevista(@QueryParam("nombreRevista") String nombreRevista) {
        List<Suscripcion> suscripciones = reporteEditorService.obtenerSuscripcionesPorRevista(nombreRevista);

        if (suscripciones != null && !suscripciones.isEmpty()) {
            return Response.ok(suscripciones).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("No se encontraron suscripciones para la revista: " + nombreRevista).build();
        }
    }

    @GET
    @Path("/revistasMasGustadas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerTop5RevistasMasGustadas() {
        List<Revista> suscripciones = reporteEditorService.revistasMasGustadas();
        return Response.ok(suscripciones).build();
    }

}
