/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.revistas.digitales.api.controladores;

import com.ipc2.revistas.digitales.api.dabase.RevistaDB;
import com.ipc2.revistas.digitales.api.modelos.errores.ErrorResponse;
import com.ipc2.revistas.digitales.api.modelos.revista.Comentario;
import com.ipc2.revistas.digitales.api.modelos.revista.Revista;
import com.ipc2.revistas.digitales.api.servicios.RevistaService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
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
@Path("/revistas")
public class RevistaController {

    private RevistaService revistaService = new RevistaService();

    @POST
    @Path("/crearRevista")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response crearRevista(
            @FormDataParam("nombre") String nombre,
            @FormDataParam("descripcion") String descripcion,
            @FormDataParam("categoria") String categoria,
            @FormDataParam("fechaCreacion") String fechaCreacionStr,
            @FormDataParam("autor") String autor) {

        boolean creada = revistaService.crearRevista(nombre, descripcion, categoria, fechaCreacionStr, autor);

        // Crear un objeto de respuesta
        Map<String, Object> response = new HashMap<>();
        if (creada) {
            response.put("message", "Revista creada exitosamente");
            response.put("status", "success");
            return Response.status(Response.Status.CREATED).entity(response).build();
        } else {
            response.put("message", "Error al crear revista");
            response.put("status", "error");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(response).build();
        }
    }

    @GET
    @Path("/obtenerRevistasPorAutor")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerRevistasPorAutor(@QueryParam("autor") String autor) {

        try {
            List<Revista> revistas = revistaService.obtenerRevistasPorAutor(autor);
            // Verifica si no se encontraron revistas para el autor especificado
            if (revistas == null || revistas.isEmpty()) {
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("mensaje", "No se encontraron revistas para el autor especificado.");
                return Response.status(Response.Status.NOT_FOUND).entity(errorResponse).build();
            }

            // En caso de éxito, devuelve la lista de revistas
            return Response.ok(revistas).build();
        } catch (Exception e) {
            // En caso de error general, devuelve un mensaje de error en JSON
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("mensaje", "Ocurrió un error al obtener las revistas.");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorResponse).build();
        }
    }

    @GET
    @Path("/obtenerRevistaPorNombre") // Método para mostrar la revista a editar
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerRevistaPorNombre(@QueryParam("nombre") String nombre) {

        // Intenta obtener la revista por nombre
        Revista revista = revistaService.obtenerRevistaPorNombre(nombre);

        if (revista != null) {
            return Response.ok(revista).build(); // Devuelve la revista si se encuentra
        } else {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("mensaje", "Revista no encontrada");
            return Response.status(Response.Status.NOT_FOUND).entity(errorResponse).build();
        }
    }
    
    @PUT
    @Path("/actualizarRevista") // Ruta para actualizar una revista
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarRevista(Revista revistaEditable) {
        
        // Verifica que la revista no sea nula
        if (revistaEditable == null) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("mensaje", "Datos de la revista no pueden estar vacíos.");
            return Response.status(Response.Status.BAD_REQUEST).entity(errorResponse).build();
        }

        // Intenta actualizar la revista
        boolean actualizado = revistaService.actualizarRevista(revistaEditable);

        if (actualizado) {
            return Response.ok().build(); // Devuelve 200 OK si se actualizó
        } else {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("mensaje", "Error al actualizar la revista.");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorResponse).build();
        }
    }
}
