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
import java.sql.SQLException;

/**
 *
 * @author melvin
 */

public class UsuarioService {

    private UsuarioDB usuarioDB = new UsuarioDB();
    private CarteraDB carteraDB = new CarteraDB();

    public void registrarUsuario(Usuario usuario) throws SQLException {

        boolean esAnunciante = false;

        switch (usuario.getRol().toString()) {
            case "SUSCRIPTOR":
                usuario = new Suscriptor(usuario.getNombreUsuario(), usuario.getContrasena());
                break;
            case "EDITOR":
                usuario = new Editor(usuario.getNombreUsuario(), usuario.getContrasena());
                break;
            case "ANUNCIANTE":
                usuario = new Anunciante(usuario.getNombreUsuario(), usuario.getContrasena());
                esAnunciante = true;

                break;
            case "ADMINISTRADOR":
                usuario = new Administrador(usuario.getNombreUsuario(), usuario.getContrasena());
                break;
            default:
                //Error
                break;
        }

        if (usuarioDB.registrarUsuario(usuario)) {
            if (esAnunciante) {
                carteraDB.crearCartera(usuario);
            }
        }
        
    }
   
    public Usuario autenticarUsuario(String username, String password) throws SQLException {
        Usuario usuario = usuarioDAO.buscarUsuarioPorUsername(username);
        /*if (usuario != null && usuario.getPassword().equals(password)) {
            return usuario; // Aquí puedes devolver un token JWT
        }
        return null;
    }*/
        return null;
    }

}
