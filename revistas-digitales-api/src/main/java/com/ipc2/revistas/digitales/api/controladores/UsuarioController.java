/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.revistas.digitales.api.controladores;

import com.ipc2.revistas.digitales.api.modelos.usuarios.Usuario;
import com.ipc2.revistas.digitales.api.servicios.UsuarioService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.sql.SQLException;

/**
 *
 * @author melvin
 */
@Path("/usuarios")
public class UsuarioController {
        private UsuarioService usuarioService = new UsuarioService();

    @POST
    @Path("/registro")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response registrarUsuario(Usuario usuario) {
        try {
            usuarioService.registrarUsuario(usuario);
            return Response.ok("Usuario registrado exitosamente").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al registrar el usuario").build();
        }
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response loginUsuario(Usuario usuario) {
        try {
            Usuario authenticatedUser = usuarioService.autenticarUsuario(usuario.getNombreUsuario(), usuario.getContrasena());
            if (authenticatedUser != null) {
                return Response.ok("Login exitoso").build(); // Aquí debes devolver un token JWT
            } else {
                return Response.status(Response.Status.UNAUTHORIZED).entity("Credenciales inválidas").build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error en el servidor").build();
        }
    }
}

