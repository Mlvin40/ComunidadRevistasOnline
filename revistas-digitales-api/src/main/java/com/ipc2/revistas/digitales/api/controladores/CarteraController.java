/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.revistas.digitales.api.controladores;

import com.ipc2.revistas.digitales.api.dabase.CarteraDB;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Map;

/**
 *
 * @author melvin
 */
@Path("/carteras")
public class CarteraController {

    private CarteraDB carteraDB;

    public CarteraController() {
        this.carteraDB = new CarteraDB();
    }

    @GET
    @Path("/{nombreUsuario}")
    public Response obtenerSaldo(@PathParam("nombreUsuario") String nombreUsuario) {
        double saldoCartera = carteraDB.obtenerSaldoActual(nombreUsuario);
        return Response.ok(saldoCartera).build();
    }

    //Con este metodo por medio del nombre de usuario recargamos una billetera
    @POST
    @Path("/{nombreUsuario}/{rol}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response recargarCartera(@PathParam("nombreUsuario") String nombreUsuario,
            @PathParam("rol") String rol,
            @FormParam("cantidadRecarga") double cantidadRecarga) {

        if (cantidadRecarga <= 0) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error", "La cantidad de recarga debe ser mayor a 0."))
                    .build();
        }

        try {
            carteraDB.recargarCartera(nombreUsuario, cantidadRecarga);
            return Response.ok(Map.of("message", "Cartera recargada exitosamente")).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("error", "Error inesperado: " + e.getMessage()))
                    .build();
        }
    }
}
