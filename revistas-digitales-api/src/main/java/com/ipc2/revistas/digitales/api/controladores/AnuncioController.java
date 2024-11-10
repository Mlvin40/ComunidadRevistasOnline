/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.revistas.digitales.api.controladores;

import com.ipc2.revistas.digitales.api.dabase.anuncios.AnuncioDB;
import com.ipc2.revistas.digitales.api.modelos.anuncios.Anuncio;
import com.ipc2.revistas.digitales.api.servicios.AnuncioService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
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
            @FormDataParam("totalAPagar") double totalAPagar) {

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

    //Este metodo obtiene todos los anuncios validos
    @GET
    @Path("/anunciosActivos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerAnuncios() {
        List<Anuncio> anuncios = anuncioService.obtenerTodosLosAnuncios();
        // Retornar la lista de anuncios en una respuesta JSON
        return Response.ok(anuncios).build();
    }

    @GET
    @Path("/anunciosPorUsuario")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerAnunciosPorAnunciante(@QueryParam("nombreAnunciante") String nombreAnunciante) {
        List<Anuncio> anuncios = anuncioService.obtenerAnunciosPorAnunciante(nombreAnunciante);
        return Response.ok(anuncios).build();
    }

    @GET
    @Path("/anuncioPorId/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerAnuncioPorId(@PathParam("id") int id) {

        Anuncio anuncio = anuncioService.obtenerAnuncioPorId(id);
        if (anuncio != null) {
            return Response.ok(anuncio).build(); // Retorna el anuncio con estado 200
        } else {
            return Response.status(Response.Status.NOT_FOUND) // Retorna 404 si no se encuentra
                    .entity("Anuncio no encontrado")
                    .build();
        }
    }

    @PUT
    @Path("/actualizarAnuncio")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarAnuncio(
            @FormDataParam("idAnuncio") int idAnuncio,
            @FormDataParam("tipoAnuncio") String tipoAnuncio,
            @FormDataParam("contenidoTexto") String contenidoTexto,
            @FormDataParam("imagen") InputStream imagenInputStream,
            @FormDataParam("urlVideo") String urlVideo,
            @FormDataParam("activo") boolean activo,
            @FormDataParam("vencido") boolean vencido) {

        boolean anuncioActualizado = anuncioService.actualizarAnuncio(idAnuncio, tipoAnuncio, contenidoTexto, imagenInputStream, urlVideo, activo, vencido);

        if (anuncioActualizado) {
            Map<String, Object> response = new HashMap<>(); // Crear un mapa para la respuesta
            response.put("mensaje", "Anuncio actualizado con éxito.");
            response.put("exito", true);
            return Response.ok(response).build();

        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Error al actualizar el anuncio: El anuncio no fue encontrado o no se pudo actualizar.")
                    .build();
        }
    }

    @GET
    @Path("/anuncioRandom")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerAnuncioRandom() {
        Anuncio anuncio = anuncioService.obtnerAnuncioAleatorio();

        if (anuncio != null) {
            return Response.ok(anuncio).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("No hay anuncios disponibles").build();
        }
    }

    @POST
    @Path("/guardarAnuncioEfecivo")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response guardarAnuncioMostrado(
            @FormDataParam("idanuncio") int idAnuncio,
            @FormDataParam("tipoAnuncio") String tipoAnuncio,
            @FormDataParam("nombreAnunciante") String nombreAnunciante,
            @FormDataParam("pathMostrado") String pathMostrado) {
        // Lógica para insertar el anuncio en la base de datos
        boolean exito = anuncioService.guardarAnuncioMostrado(idAnuncio, tipoAnuncio, nombreAnunciante, pathMostrado);

        if (exito) {
            return Response.status(Response.Status.CREATED).entity("Anuncio guardado exitosamente").build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al guardar el anuncio").build();
        }
    }

}
