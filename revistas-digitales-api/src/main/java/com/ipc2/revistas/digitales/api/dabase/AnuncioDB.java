/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.revistas.digitales.api.dabase;

import com.ipc2.revistas.digitales.api.dabase.DataSourceDBSingleton;
import java.io.InputStream;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author melvin
 */
public class AnuncioDB {

    private Connection connection;

    public AnuncioDB() {
        try {
            this.connection = DataSourceDBSingleton.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Método para insertar un anuncio de texto
    public boolean crearAnuncioTexto(String contenidoTexto, String nombreAnunciante, LocalDate fechaInicio, int duracionDias) {
        String sql = "INSERT INTO anuncios (tipo_anuncio, contenido_texto, nombre_anunciante, fecha_inicio, duracion_dias, fecha_expiracion) "
                + "VALUES (?, ?, ?, ?, ?, DATE_ADD(?, INTERVAL ? DAY))";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
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

    // Método para insertar un anuncio de texto e imagen
    public boolean crearAnuncioTextoImagen(String contenidoTexto, InputStream imagen, String nombreAnunciante, LocalDate fechaInicio, int duracionDias) {
        String sql = "INSERT INTO anuncios (tipo_anuncio, contenido_texto, imagen, nombre_anunciante, fecha_inicio, duracion_dias, fecha_expiracion) "
                + "VALUES (?, ?, ?, ?, ?, ?, DATE_ADD(?, INTERVAL ? DAY))";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, "TEXTO_IMAGEN");
            pstmt.setString(2, contenidoTexto);
            pstmt.setBlob(3, imagen);
            pstmt.setString(4, nombreAnunciante);
            pstmt.setDate(5, java.sql.Date.valueOf(fechaInicio));  // Convertir LocalDate a java.sql.Date
            pstmt.setInt(6, duracionDias);
            pstmt.setDate(7, java.sql.Date.valueOf(fechaInicio));
            pstmt.setInt(8, duracionDias); // Usar directamente el número de días
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
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

    // Método para obtener el costo de un anuncio por día
    public double obtenerCostoAnuncioDia(String tipoAnuncio) {
        String sql = "SELECT costo FROM precio_anuncio_dia WHERE tipo_anuncio = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, tipoAnuncio);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("costo");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }
}
