/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.revistas.digitales.api.dabase;

import com.ipc2.revistas.digitales.api.modelos.revista.Suscripcion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author melvin
 */
public class SuscripcionDB {

    public List<Suscripcion> obtenerTodasSuscripciones() {
        List<Suscripcion> suscripciones = new ArrayList<>();
        String consulta = "SELECT id_suscripcion, nombre_usuario, nombre_revista, fecha_suscripcion, estado FROM suscripciones";

        // Aseguramos que el ResultSet solo se usa dentro del try-with-resources
        try (Connection connection = DataSourceDB.getInstance().getConnection();
                PreparedStatement stmt = connection.prepareStatement(consulta); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                // Crear objeto Suscripcion
                Suscripcion suscripcion = new Suscripcion(
                        rs.getInt("id_suscripcion"),
                        rs.getString("nombre_usuario"),
                        rs.getString("nombre_revista"),
                        rs.getDate("fecha_suscripcion").toLocalDate(), // Conversión correcta a LocalDate
                        rs.getBoolean("estado")
                );
                // Añadir la suscripción a la lista
                suscripciones.add(suscripcion);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener todas las suscripciones: " + e.getMessage());
        }

        return suscripciones;
    }

    public List<Suscripcion> obtenerSuscripcionesPorRevista(String nombreRevista) {
        List<Suscripcion> suscripciones = new ArrayList<>();
        String query = "SELECT id_suscripcion, nombre_usuario, nombre_revista, fecha_suscripcion, estado "
                + "FROM suscripciones WHERE nombre_revista = ?";

        try (Connection connection = DataSourceDB.getInstance().getConnection();
                PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nombreRevista);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Suscripcion suscripcion = new Suscripcion(
                            rs.getInt("id_suscripcion"),
                            rs.getString("nombre_usuario"),
                            rs.getString("nombre_revista"),
                            rs.getDate("fecha_suscripcion").toLocalDate(),
                            rs.getBoolean("estado")
                    );
                    suscripciones.add(suscripcion);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener suscripciones para la revista '" + nombreRevista + "': " + e.getMessage());
        }
        return suscripciones;
    }

}
