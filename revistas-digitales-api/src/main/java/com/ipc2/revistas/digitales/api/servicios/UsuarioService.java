/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.revistas.digitales.api.servicios;

import com.ipc2.revistas.digitales.api.dabase.UsuarioDB;
import com.ipc2.revistas.digitales.api.modelos.usuarios.Usuario;
import java.sql.SQLException;

/**
 *
 * @author melvin
 */
public class UsuarioService {
    
    private UsuarioDB usuarioDB = new UsuarioDB();
        
    public void registrarUsuario(Usuario usuario) throws SQLException {
        usuarioDAO.insertarUsuario(usuario);
    }
    
    public Usuario autenticarUsuario(String username, String password) throws SQLException {
        Usuario usuario = usuarioDAO.buscarUsuarioPorUsername(username);
        if (usuario != null && usuario.getPassword().equals(password)) {
            return usuario; // Aquí puedes devolver un token JWT
        }
        return null;
    }

}
