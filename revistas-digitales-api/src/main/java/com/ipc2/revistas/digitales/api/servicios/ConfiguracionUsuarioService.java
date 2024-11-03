/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.revistas.digitales.api.servicios;

import com.ipc2.revistas.digitales.api.dabase.UsuarioDB;
import com.ipc2.revistas.digitales.api.modelos.usuarios.Usuario;

/**
 *
 * @author melvin
 */
public class ConfiguracionUsuarioService {

    public Usuario obtenerUsuario(String nombreUsuario) {

        UsuarioDB usuarioDB = new UsuarioDB();
        Usuario usuario = usuarioDB.obtenerUsuario(nombreUsuario);

        //si la base de datos no encuentra a un usuario, retorna nulo
        return usuario;
    }
    
    
    
}
