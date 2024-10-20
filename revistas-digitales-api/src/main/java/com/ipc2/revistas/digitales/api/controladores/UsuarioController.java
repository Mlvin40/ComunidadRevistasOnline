/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.revistas.digitales.api.controladores;

import com.ipc2.revistas.digitales.api.modelos.usuarios.Usuario;
import com.ipc2.revistas.digitales.api.servicios.UsuarioService;
import jakarta.inject.Inject;
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

    private UsuarioService usuarioService;

    public UsuarioController() {
        this.usuarioService = new UsuarioService(); // Instanciación manual
    }

    @POST
    @Path("/registro")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response registrarUsuario(Usuario usuario) {
        // Validación básica de los datos recibidos
        if (usuario.getNombreUsuario() == null || usuario.getNombreUsuario().isEmpty()
                || usuario.getContrasena() == null || usuario.getContrasena().isEmpty()
                || usuario.getRol() == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Todos los campos son obligatorios").build();
        }

        try {
            usuarioService.registrarUsuario(usuario); // Registrar el usuario en la base de datos
            return Response.ok("Usuario registrado exitosamente").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al registrar el usuario DESDE EL BE").build();
        }
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginUsuario(Usuario usuario) {
        // Validación básica de los datos recibidos
        if (usuario.getNombreUsuario() == null || usuario.getContrasena() == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Nombre de usuario y contraseña son obligatorios").build();
        }

        try {
            Usuario authenticatedUser = usuarioService.autenticarUsuario(usuario.getNombreUsuario(), usuario.getContrasena());
            if (authenticatedUser != null) {
                // Aquí es donde puedes generar un token JWT
                String jwtToken = generateJWT(authenticatedUser);
                return Response.ok("{\"token\": \"" + jwtToken + "\"}").build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED).entity("Credenciales inválidas").build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error en el servidor").build();
        }
    }

    // Método para generar un token JWT (puedes personalizarlo)
    private String generateJWT(Usuario usuario) {
        // Aquí usarías una librería como jjwt para generar un token JWT
        // Por ejemplo:
        // return Jwts.builder()
        //          .setSubject(usuario.getNombreUsuario())
        //          .setIssuedAt(new Date())
        //          .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
        //          .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
        //          .compact();
        return "tokenEjemplo"; // Token de ejemplo (deberías reemplazarlo con lógica real)
    }
}
