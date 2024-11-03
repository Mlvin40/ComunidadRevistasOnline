/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.revistas.digitales.api.servicios;

import com.ipc2.revistas.digitales.api.dabase.CarteraDB;
import com.ipc2.revistas.digitales.api.dabase.UsuarioDB;
import com.ipc2.revistas.digitales.api.modelos.usuarios.Administrador;
import com.ipc2.revistas.digitales.api.modelos.usuarios.Anunciante;
import com.ipc2.revistas.digitales.api.modelos.usuarios.Editor;
import com.ipc2.revistas.digitales.api.modelos.usuarios.Suscriptor;
import com.ipc2.revistas.digitales.api.modelos.usuarios.Usuario;
import com.ipc2.revistas.digitales.api.util.GeneradorToken;

import java.sql.SQLException;

/**
 *
 * @author melvin
 */

public class UsuarioService {

    private UsuarioDB usuarioDB = new UsuarioDB();
    private CarteraDB carteraDB = new CarteraDB();

    public void registrarUsuario(Usuario usuario) throws SQLException {

        boolean agregarCartera = false;

        switch (usuario.getRol().toString()) {
            case "SUSCRIPTOR":
                usuario = new Suscriptor(usuario.getNombreUsuario(), usuario.getContrasena());
                break;
            case "EDITOR":
                usuario = new Editor(usuario.getNombreUsuario(), usuario.getContrasena());
                agregarCartera = true;
                
                break;
            case "ANUNCIANTE":
                usuario = new Anunciante(usuario.getNombreUsuario(), usuario.getContrasena());
                agregarCartera = true;
              
                break;
            case "ADMINISTRADOR":
                usuario = new Administrador(usuario.getNombreUsuario(), usuario.getContrasena());
                break;
            default:
                //Error
                break;
        }

        if (usuarioDB.registrarUsuario(usuario)) {
            if (agregarCartera) {
                carteraDB.crearCartera(usuario);
            }
        }
    }
 
    public String obtenerToken(Usuario usuario){
     
        String token = null;
        Usuario usuarioLog = autenticarUsuario(usuario.getNombreUsuario(), usuario.getContrasena());

        //ver si el usuario obtenido no es nulo, entonces se crea un token
        if(usuarioLog != null){
            // Crear JWT
            GeneradorToken generadorToken = new GeneradorToken();
            token = generadorToken.crearTokenJWT(usuarioLog);
            return token;
        }
        return null;
    }
    
    private Usuario autenticarUsuario(String nombreUsuario, String contrasena) {
       return usuarioDB.iniciarSesion(nombreUsuario, contrasena);
    }
    
}
