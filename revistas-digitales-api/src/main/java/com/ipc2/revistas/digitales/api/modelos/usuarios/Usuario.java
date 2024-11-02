package com.ipc2.revistas.digitales.api.modelos.usuarios;


public class Usuario {

    private String nombreUsuario;
    private String contrasena;
    private String texto;
    private byte[] fotoPerfil;
    private Rol rol;
    private String fechaCreacion;

    //Constructor para registrar a un usuario nuevo
    public Usuario(String nombreUsuario, String contrasena, Rol rol) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = guardarContrasena(contrasena);
        this.texto = null;
        this.fotoPerfil = null;
        this.rol = rol;
    }

    public Usuario() {
    }

    //Constructor para recuperar a un usuario de la base de datos
    public Usuario(String nombreUsuario, String contrasena, String texto, byte[] fotoPerfil, Rol rol, String fechaCreacion) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.texto = texto;
        this.fotoPerfil = fotoPerfil;
        this.rol = rol;
        this.fechaCreacion = fechaCreacion;
    }

    private String guardarContrasena(String contrasena) {
        Seguridad seguridad = new Seguridad();
        return seguridad.encriptarContrasena(contrasena);
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public byte[] getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(byte[] fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

}
