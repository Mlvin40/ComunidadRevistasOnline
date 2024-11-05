/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.revistas.digitales.api.dabase.anuncios;

import com.ipc2.revistas.digitales.api.dabase.DataSourceDBSingleton;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author melvin
 */
public class AnuncioTextoImagenDB {

    private Connection connection;

    public AnuncioTextoImagenDB() {
        try {
            this.connection = DataSourceDBSingleton.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
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

    public boolean actualizarAnuncioTextoImagen(int idAnuncio, String contenidoTexto, InputStream imagenInputStream, boolean activo) {
        // Crea una consulta SQL que solo actualizará los campos que se deben modificar
        StringBuilder sql = new StringBuilder("UPDATE anuncios SET contenido_texto = ?, activo = ?");

        // Agrega el campo de imagen a la consulta solo si no es nulo
        if (imagenInputStream != null) {
            sql.append(", imagen = ?");
        }

        sql.append(" WHERE id_anuncio = ?");

        try (PreparedStatement pstmt = connection.prepareStatement(sql.toString())) {
            pstmt.setString(1, contenidoTexto); // Establece el contenidoTexto
            pstmt.setBoolean(2, activo); // Establece el estado activo

            int parameterIndex = 3; // Índice del siguiente parámetro
            // Solo establece el parámetro de la imagen si no es nula
            if (imagenInputStream != null) {
                pstmt.setBlob(parameterIndex++, imagenInputStream); // Establece la imagen como un InputStream
            }
            pstmt.setInt(parameterIndex, idAnuncio); // Establece el idAnuncio

            int filasActualizadas = pstmt.executeUpdate(); // Ejecuta la actualización

            return filasActualizadas > 0; // Retorna verdadero si se actualizó al menos una fila
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Retorna falso en caso de error o si no se actualizó nada
    }

}
