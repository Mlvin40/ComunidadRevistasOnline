package com.ipc2.revistas.digitales.api.modelos.usuarios;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Seguridad {

    // Instancia de PasswordEncoder (BCrypt)
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * Este metodo encripta la contrasena con BCrypt y la retorna
     * @param contrasena
     * @return
     */
    public String encriptarContrasena(String contrasena) {
        return passwordEncoder.encode(contrasena); // Encripta la contraseña con BCrypt
    }

    /**
     * Verifica si la contrasena ingresada coincide con el hash almacenado
     * @param contrasena Contraseña original
     * @param hash Hash almacenado
     * @return true si coinciden, false si no
     */
    public boolean verificarContrasena(String contrasena, String hash) {
        return passwordEncoder.matches(contrasena, hash); // Verifica si la contraseña coincide
    }
}
