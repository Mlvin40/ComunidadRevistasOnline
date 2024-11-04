/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.revistas.digitales.api.controladores;

import com.ipc2.revistas.digitales.api.modelos.revista.Publicacion;
import com.ipc2.revistas.digitales.api.servicios.PublicacionService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

/**
 *
 * @author melvin
 */
@Path("/publicaciones")
public class PublicacionController {

    private PublicacionService publicacionService = new PublicacionService();

    @POST
    @Path("/realizarPublicacion")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response realizarPublicacion(
            @FormDataParam("autor") String autor,
            @FormDataParam("nombreRevista") String nombreRevista,
            @FormDataParam("descripcion") String descripcion,
            @FormDataParam("archivoPDF") InputStream archivoPDFStream,
            @FormDataParam("archivoPDF") FormDataContentDisposition fileDetail,
            @FormDataParam("fechaPublicacion") String fechaPublicacion) {

        // Ver que la fecha de publicacion este en formato yy-mm-dd y convertirla a LocalDate
        LocalDate fechaPublicacionLocal = LocalDate.parse(fechaPublicacion);

        //Llamar a la clase que se encarga de todo
        boolean exito = publicacionService.guardarPublicacion(nombreRevista, descripcion, archivoPDFStream, fechaPublicacionLocal);

        if (exito) {
            return Response.ok().build();
        } else {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("mensaje", "Error al realizar la publicación.");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorResponse).build();
        }
    }

    @GET
    @Path("/listaPublicaciones")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPublicacionesPorRevista(@QueryParam("revista") String nombreRevista) {
        List<Publicacion> publicaciones = publicacionService.obtenerPublicacionesPorRevista(nombreRevista);
        return Response.ok(publicaciones).build();
        
    }
}
