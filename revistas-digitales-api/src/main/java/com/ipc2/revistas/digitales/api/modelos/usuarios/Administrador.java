package com.ipc2.revistas.digitales.api.modelos.usuarios;

public class Administrador extends Usuario {

    public Administrador(String nombreUsuario, String contrasena) {
        super(nombreUsuario, contrasena, Rol.ADMINISTRADOR);
    }
}