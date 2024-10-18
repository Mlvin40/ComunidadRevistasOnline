package com.ipc2.revistas.digitales.api.modelos.usuarios;

public class Suscriptor extends Usuario {

    public Suscriptor(String nombreUsuario, String contrasena) {
        super(nombreUsuario, contrasena, Rol.SUSCRIPTOR);
    }
}
