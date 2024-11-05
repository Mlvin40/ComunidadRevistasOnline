/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.revistas.digitales.api.dabase.anuncios;

import com.ipc2.revistas.digitales.api.dabase.DataSourceDBSingleton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author melvin
 */
public class AnuncioVideoDB {

    private Connection connection;

    public AnuncioVideoDB() {
        try {
            this.connection = DataSourceDBSingleton.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Método para insertar un anuncio de video
    public boolean crearAnuncioVideo(String urlVideo, String nombreAnunciante, LocalDate fechaInicio, int duracionDias) {
        String sql = "INSERT INTO anuncios (tipo_anuncio, url_video, nombre_anunciante, fecha_inicio, duracion_dias, fecha_expiracion)"
                + "VALUES (?, ?, ?, ?, ?, DATE_ADD(?, INTERVAL ? DAY))";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, "VIDEO");
            pstmt.setString(2, urlVideo);
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

    public boolean actualizarAnuncioVideo(int idAnuncio, String urlVideo, boolean activo) {
        String sql = "UPDATE anuncios SET url_video = ?, activo = ? WHERE id_anuncio = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, urlVideo); // Establece la URL del video
            pstmt.setBoolean(2, activo); // Establece el estado activo
            pstmt.setInt(3, idAnuncio); // Establece el idAnuncio

            int filasActualizadas = pstmt.executeUpdate(); // Ejecuta la actualización

            return filasActualizadas > 0; // Retorna verdadero si se actualizó al menos una fila
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Retorna falso en caso de error o si no se actualizó nada
    }
}
