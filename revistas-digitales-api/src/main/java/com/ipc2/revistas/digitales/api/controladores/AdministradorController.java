/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.revistas.digitales.api.controladores;

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

    private AdministradorService administradorService = new AdministradorService();

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
}
