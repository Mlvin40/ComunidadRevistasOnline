/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.revistas.digitales.api.dabase;

import com.ipc2.revistas.digitales.api.modelos.revista.Publicacion;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    public boolean crearPublicacion(String nombreRevista, String descripcion, InputStream pdfPublicacion, LocalDate fechaPublicacion) {
        String consulta = "INSERT INTO publicaciones (nombre_revista, descripcion, fecha_publicacion, pdf_publicacion) VALUES (?, ?, ?, ?)";

        // Intentar con try-with-resources para asegurarse de que el PreparedStatement se cierre automáticamente
        try (PreparedStatement statement = connection.prepareStatement(consulta)) {
            statement.setString(1, nombreRevista);
            statement.setString(2, descripcion);
            statement.setDate(3, java.sql.Date.valueOf(fechaPublicacion)); // Convertir LocalDate a java.sql.Date
            statement.setBlob(4, pdfPublicacion);

            // Ejecutar la consulta y verificar si se afectaron filas
            int filasAfectadas = statement.executeUpdate();

            // Retornar true si se afectaron filas, indicando que la publicación fue creada exitosamente
            return filasAfectadas > 0;
        } catch (SQLException e) {
            // Registrar el error con más detalles
            System.err.println("Error al registrar publicación: " + e.getMessage());
            e.printStackTrace(); // Rastro completo de la excepción para más detalles
            return false;
        }
    }

    public List<Publicacion> obtenerPublicacionesRevista(String nombreRevista) {
        List<Publicacion> publicaciones = new ArrayList<>();
        String consulta = "SELECT nombre_revista, descripcion, fecha_publicacion, pdf_publicacion FROM publicaciones WHERE nombre_revista = ?";

        try (PreparedStatement statement = connection.prepareStatement(consulta)) {
            statement.setString(1, nombreRevista);
            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    String rev = resultSet.getString("nombre_revista");
                    String desc = resultSet.getString("descripcion");
                    LocalDate fecha = resultSet.getDate("fecha_publicacion").toLocalDate();
                    byte[] pdf = resultSet.getBytes("pdf_publicacion");

                    // Usando el nuevo constructor para crear la publicación
                    Publicacion publicacion = new Publicacion(rev, desc, fecha, pdf);
                    publicaciones.add(publicacion);
                }
            }
        } catch (SQLException e) {
            // Mejorar manejo de excepciones
            System.err.println("Error al obtener publicaciones: " + e.getMessage());
            e.printStackTrace();  // Imprime el rastro de la excepción para depuración
        }

        return publicaciones;
    }
    
}
