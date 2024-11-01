/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.revistas.digitales.api.controladores;

import com.ipc2.revistas.digitales.api.modelos.usuarios.Usuario;
import com.ipc2.revistas.digitales.api.servicios.ConfiguracionUsuarioService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author melvin
 */
@Path("/configuracion-usuario")
public class ConfiguracionUsuarioController {

    private ConfiguracionUsuarioService configuracionUsuarioService = new ConfiguracionUsuarioService();

    public ConfiguracionUsuarioController() {
        this.configuracionUsuarioService = new ConfiguracionUsuarioService();
    }

    @GET
    @Path("/obtener-datos-usuario")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerDatosUsuario(@QueryParam("nombreUsuario") String nombreUsuario) {
        Usuario usuario = configuracionUsuarioService.obtenerUsuario(nombreUsuario);
        if (usuario != null) {
            return Response.ok(usuario).build(); // Retorna el objeto usuario como respuesta
        } else {
            return Response.status(Response.Status.NOT_FOUND).build(); // Usuario no encontrado
        }
    }
}
