/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.revistas.digitales.api.controladores;

import com.ipc2.revistas.digitales.api.dabase.UsuarioDB;
import com.ipc2.revistas.digitales.api.modelos.usuarios.Usuario;
import com.ipc2.revistas.digitales.api.servicios.ConfiguracionUsuarioService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

/**
 *
 * @author melvin
 */
@Path("/configuracion-usuario")
public class ConfiguracionUsuarioController {

    private ConfiguracionUsuarioService configuracionUsuarioService;

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

    @POST
    @Path("/actualizar-perfil")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarPerfil(
            @FormDataParam("nombre") String nombre,
            @FormDataParam("texto") String texto,
            @FormDataParam("fileObject") InputStream foto,
            @FormDataParam("fileObject") FormDataContentDisposition fileDetails) {

        Map<String, Object> responseMap = new HashMap<>();

        try {
            UsuarioDB usuarioDB = new UsuarioDB();
            usuarioDB.actualizarUsuario(nombre, texto, foto);
            // Agregar mensaje de éxito al responseMap
            responseMap.put("message", "Perfil actualizado correctamente");
            return Response.ok(responseMap).build();
        } catch (Exception e) {
            // Si ocurre algún error, agregar mensaje de error al responseMap
            responseMap.put("message", "Hubo un error al actualizar el perfil");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(responseMap).build();
        }
    }
    
}
