package com.ipc2.revistas.digitales.api.modelos.usuarios;

public class Editor extends Usuario {

    public Editor(String nombreUsuario, String contrasena) {
        super(nombreUsuario, contrasena, Rol.EDITOR);
    }
}
