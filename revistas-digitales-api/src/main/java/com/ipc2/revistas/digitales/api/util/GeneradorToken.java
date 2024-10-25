/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.revistas.digitales.api.util;

import com.ipc2.revistas.digitales.api.modelos.usuarios.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;

/**
 *
 * @author melvin
 */
public class GeneradorToken {

    // Llave secreta para firmar el token
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256); 
    
    // Para iniciar sesion
    public String crearTokenJWT(Usuario usuario) {
        return Jwts.builder()
                .setSubject(usuario.getNombreUsuario()) // Añade el nombre de usuario
                .claim("rol", usuario.getRol()) // Añade el rol como una claim
                .setIssuedAt(new Date()) // Fecha de emisión
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // Expira en 1 hora
                .signWith(SECRET_KEY) // Firma con la clave secreta
                .compact();
    }
}
