/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.revistas.digitales.api.controladores;

import com.ipc2.revistas.digitales.api.modelos.revista.Revista;
import com.ipc2.revistas.digitales.api.servicios.ReporteAdministradorService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 *
 * @author melvin
 */
@Path("/reportesAdministrador")
public class ReporteAdministradorController {

    private ReporteAdministradorService reporteAdministradorService = new ReporteAdministradorService();

    @GET
    @Path("/revistasMasComentadas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerTop5RevistasMasGustadas() {
        List<Revista> suscripciones = reporteAdministradorService.revistasMasComentadas();
        return Response.ok(suscripciones).build();
    }
    
}
