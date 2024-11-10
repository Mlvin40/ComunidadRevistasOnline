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
    //private static final String SECRET_KEY = "clave_usr_revistas_online"; //Llave secreta para los tokens

    // Para iniciar sesion
    public String crearTokenJWT(Usuario usuario) {
        System.out.println("Rol del usuario al generar el token: " + usuario.getRol());
        return Jwts.builder()
                .setSubject(usuario.getNombreUsuario()) //En esta parte se agrega el nombre de usuario al token
                .claim("rol", usuario.getRol())
                .setIssuedAt(new Date()) // Fecha de emisión
                .setExpiration(new Date(System.currentTimeMillis() + 10800000)) // Expira en 3 horaS
                .signWith(SECRET_KEY) // Firma con la llave secreta
                .compact();

    }
}
