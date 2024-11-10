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
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author melvin
 */
@Path("/usuarios")
public class UsuarioController {

    private UsuarioService usuarioService;
    
    public UsuarioController() {
        this.usuarioService = new UsuarioService();
    }

    @POST
    @Path("/registro")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrarUsuario(Usuario usuario) {

        // Validación básica de los datos recibidos
        if (usuario.getNombreUsuario() == null || usuario.getNombreUsuario().isEmpty()
                || usuario.getContrasena() == null || usuario.getContrasena().isEmpty()
                || usuario.getRol() == null) {

            // Devuelve un error en formato JSON
            Map<String, String> response = new HashMap<>();
            response.put("error", "Todos los campos son obligatorios");
            return Response.status(Response.Status.BAD_REQUEST).entity(response).build();
        }

        try {
            usuarioService.registrarUsuario(usuario); // Registrar el usuario en la base de datos

            // Devuelve una respuesta de éxito en formato JSON
            Map<String, String> response = new HashMap<>();
            response.put("message", "Usuario registrado exitosamente");
            return Response.ok(response).build();
        } catch (SQLException e) {
            // Devuelve un error en formato JSON
            Map<String, String> response = new HashMap<>();
            response.put("error", "Error al registrar el usuario desde el BE");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(response).build();
        }
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginUsuario(Usuario usuario) {
        
        // Intenta obtener un token
        String tokenObtenido = usuarioService.obtenerToken(usuario);

        if (tokenObtenido != null) {
            
            // Responder con el token tanto en el encabezado como en el cuerpo
            return Response.ok()
                    .header("Authorization", "Bearer " + tokenObtenido)
                    .entity("{\"token\":\"" + tokenObtenido + "\"}")
                    .build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Credenciales incorrectas").build();
        }
    }
}
