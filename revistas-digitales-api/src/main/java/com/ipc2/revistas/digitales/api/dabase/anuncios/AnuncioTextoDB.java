/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.revistas.digitales.api.dabase.anuncios;

import com.ipc2.revistas.digitales.api.dabase.DataSourceDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author melvin
 */
public class AnuncioTextoDB extends AnuncioDB {

    
    // Método para insertar un anuncio de texto
    public boolean crearAnuncioTexto(String contenidoTexto, String nombreAnunciante, LocalDate fechaInicio, int duracionDias) {
        String sql = "INSERT INTO anuncios (tipo_anuncio, contenido_texto, nombre_anunciante, fecha_inicio, duracion_dias, fecha_expiracion) "
                + "VALUES (?, ?, ?, ?, ?, DATE_ADD(?, INTERVAL ? DAY))";
        try (Connection connection = DataSourceDB.getInstance().getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, "TEXTO");
            pstmt.setString(2, contenidoTexto);
            pstmt.setString(3, nombreAnunciante);
            pstmt.setDate(4, java.sql.Date.valueOf(fechaInicio));  // Convertir LocalDate a java.sql.Date
            pstmt.setInt(5, duracionDias);
            pstmt.setDate(6, java.sql.Date.valueOf(fechaInicio));
            pstmt.setInt(7, duracionDias); // Usar directamente el número de días
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizarAnuncioTexto(int idAnuncio, String contenidoTexto, boolean activo) {
        String sql = "UPDATE anuncios SET contenido_texto = ?, activo = ? WHERE id_anuncio = ?";

        try (Connection connection = DataSourceDB.getInstance().getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, contenidoTexto); // Set contenidoTexto
            pstmt.setBoolean(2, activo); // Set activo
            pstmt.setInt(3, idAnuncio); // Set idAnuncio

            int filasActualizadas = pstmt.executeUpdate(); // Ejecutar la actualización

            return filasActualizadas > 0; // Retorna verdadero si se actualizó al menos una fila
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Retorna falso en caso de error o si no se actualizó nada
    }

}
