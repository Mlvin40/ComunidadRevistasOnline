/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.revistas.digitales.api.controladores;

import com.ipc2.revistas.digitales.api.dabase.AnuncioDB;
import com.ipc2.revistas.digitales.api.servicios.AnuncioService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import org.glassfish.jersey.media.multipart.FormDataParam;

/**
 *
 * @author melvin
 */
@Path("/anuncios")
public class AnuncioController {

    private final AnuncioService anuncioService = new AnuncioService();

    @GET
    @Path("/preciosAnuncio")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPreciosAnuncios() {

        AnuncioDB anuncioDB = new AnuncioDB();

        Map<String, Double> precios = new HashMap<>();
        precios.put("texto", anuncioDB.obtenerCostoAnuncioDia("TEXTO"));
        precios.put("textoImagen", anuncioDB.obtenerCostoAnuncioDia("TEXTO_IMAGEN"));
        precios.put("video", anuncioDB.obtenerCostoAnuncioDia("VIDEO"));
        return Response.ok(precios).build();

    }

    
    @POST
    @Path("/comprarAnuncio")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response comprarAnuncio(
            @FormDataParam("nombreAnunciante") String anuciante,
            @FormDataParam("tipoAnuncio") String tipoAnuncio,
            @FormDataParam("contenidoTexto") String contenidoTexto,
            @FormDataParam("imagen") InputStream imagenInputStream,
            @FormDataParam("urlVideo") String urlVideo,
            @FormDataParam("duracion") Integer duracion,
            @FormDataParam("fechaInicio") String fechaInicio,
            @FormDataParam("totalAPagar") Integer totalAPagar) {

        
        boolean anuncioComprado = anuncioService.comprarAnuncio(anuciante, tipoAnuncio, contenidoTexto, imagenInputStream, urlVideo, duracion, fechaInicio, totalAPagar);

        // Si el booleano es verdadero, se envía un mensaje de éxito
        if (anuncioComprado) {
            return Response.status(Response.Status.CREATED)
                    .entity("{\"mensaje\":\"Anuncio comprado con éxito\"}")
                    .build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"mensaje\":\"Error al comprar el anuncio\"}")
                    .build();
        }
    }

    
}
