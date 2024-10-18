package com.ipc2.revistas.digitales.api.modelos.usuarios;

public class Anunciante extends Usuario {

    public Anunciante(String nombreUsuario, String contrasena) {
        super(nombreUsuario, contrasena, Rol.ANUNCIANTE);
    }
}
