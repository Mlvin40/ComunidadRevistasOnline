/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.revistas.digitales.api.dabase;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author melvin
 */
public class PublicacionDB {

    private Connection connection;

    public PublicacionDB() {
        try {
            this.connection = DataSourceDBSingleton.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    
    //metodo boolean para saber si se creo la publicacion
    public boolean crearPublicacion(String nombreRevista, String tituloPublicacion, InputStream pdfPublicacion) {
        String consulta = "INSERT INTO publicaciones (nombre_revista, descripcion , pdf_publicacion) VALUES (?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(consulta);
            statement.setString(1, nombreRevista);
            statement.setString(2, tituloPublicacion);
            statement.setBlob(3, pdfPublicacion);

            // Si la inserción fue exitosa, executeUpdate devuelve el número de filas afectadas
            int filasAfectadas = statement.executeUpdate();

            // Si se afectó al menos una fila, devuelve true, significa que la inserción fue exitosa
            return filasAfectadas > 0;
        } catch (SQLException e) {
            // Si ocurre una excepción, puedes manejarla y devolver false
            System.out.println("Error al registrar publicacion: " + e.getMessage());
            return false;
        }
    }
}
