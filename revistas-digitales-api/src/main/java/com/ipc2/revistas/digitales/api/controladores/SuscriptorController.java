/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.revistas.digitales.api.controladores;

import com.ipc2.revistas.digitales.api.dabase.ComentarioDB;
import com.ipc2.revistas.digitales.api.dabase.MeGustaDB;
import com.ipc2.revistas.digitales.api.dabase.RevistaDB;
import com.ipc2.revistas.digitales.api.dabase.SuscriptorDB;
import com.ipc2.revistas.digitales.api.modelos.revista.Comentario;
import com.ipc2.revistas.digitales.api.modelos.revista.Revista;
import com.ipc2.revistas.digitales.api.servicios.SuscriptorService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.glassfish.jersey.media.multipart.FormDataParam;

/**
 *
 * @author melvin
 */
@Path("/suscriptorFunciones")
public class SuscriptorController {

    private SuscriptorService suscriptorService = new SuscriptorService();

    @GET
    @Path("/revistasDisponibles")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerRevistasDisponibles(@QueryParam("usuario") String nombreUsuario) {

        List<Revista> revistasDisponibles = suscriptorService.obtenerRevistasParaSuscriptor(nombreUsuario);

        return Response.ok(revistasDisponibles).build();
    }

    @POST
    @Path("/suscribirseRevista")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response suscribirseRevista(
            @FormDataParam("nombreRevista") String nombreRevista,
            @FormDataParam("fechaSuscripcion") String fechaSuscripcion,
            @FormDataParam("usuario") String nombreUsuario) {

        boolean suscripcionExitosa = suscriptorService.suscribirARevista(nombreUsuario, nombreRevista, fechaSuscripcion);

        Map<String, Object> responseMap = new HashMap<>();

        if (suscripcionExitosa) {
            responseMap.put("mensaje", "Suscripción exitosa.");
            return Response.ok(responseMap).build();
        } else {
            responseMap.put("mensaje", "Error al procesar la suscripción.");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(responseMap).build();
        }
    }

    @GET
    @Path("/revistasSuscriptor")  // Con este metodo se obtienen las revistas suscritas por un usuario
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerRevistasSuscritas(@QueryParam("usuario") String nombreUsuario) {

        List<Revista> revistas = suscriptorService.obtenerRevistasSuscritasPorUsuario(nombreUsuario);
        return Response.ok(revistas).build();
    }

    @POST
    @Path("/darLike")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response darLike(
            @FormDataParam("nombreRevista") String nombreRevista,
            @FormDataParam("nombreSuscriptor") String nombreSuscriptor) {
        boolean likeExitoso = suscriptorService.darLikeRevista(nombreRevista, nombreSuscriptor);

        Map<String, Object> responseMap = new HashMap<>();
        if (likeExitoso) {
            responseMap.put("mensaje", "Like exitoso.");
            return Response.ok(responseMap).build();
        } else {
            responseMap.put("mensaje", "Error al procesar el like.");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(responseMap).build();
        }

    }

    @POST
    @Path("/comentarRevista")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response comentarRevista(
            @FormDataParam("nombreRevista") String nombreRevista,
            @FormDataParam("textoComentario") String textoComentario,
            @FormDataParam("nombreSuscriptor") String nombreSuscriptor) {

        suscriptorService.realizarComentario(nombreRevista, textoComentario, nombreSuscriptor);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("mensaje", "Solicitud Realizada");
        return Response.ok(responseMap).build();
    }

    @GET
    @Path("/revistasPorCategoria")
    @Produces(MediaType.APPLICATION_JSON)
    public Response revistasPorCategoria(
            @QueryParam("categoriaBuscar") String categoria,
            @QueryParam("usuario") String nombreUsuario) {

        List<Revista> revistas = suscriptorService.revistasCategoria(categoria, nombreUsuario);
        return Response.ok(revistas).build();
        
    }
}
