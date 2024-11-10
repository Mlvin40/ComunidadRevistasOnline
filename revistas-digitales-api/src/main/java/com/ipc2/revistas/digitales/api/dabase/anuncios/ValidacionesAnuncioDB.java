/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.revistas.digitales.api.dabase.anuncios;

import com.ipc2.revistas.digitales.api.dabase.DataSourceDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author melvin
 */
public class ValidacionesAnuncioDB {


    // Método para desactivar un anuncio vencido, comparando la fecha de expiración con la fecha actual
    public void desactivarAnuncioVencido(int idAnuncio) {
        String sql = "UPDATE anuncios SET activo = 0 WHERE fecha_expiracion < CURDATE()";
        try (Connection connection = DataSourceDB.getInstance().getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql)) {
            int filasAfectadas = pstmt.executeUpdate();
            System.out.println("Anuncios desactivados: " + filasAfectadas);
           // return filasAfectadas > 0; // Retorna true si se desactivaron anuncios
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al desactivar anuncios vencidos");
            //return false; // Retorna false si hubo un error
        }
    }

    // Método para verificar si un anuncio está activo
    public boolean verificarAnuncioActivo(int idAnuncio) {
        String sql = "SELECT activo FROM anuncios WHERE id_anuncio = ?";
        try (Connection connection = DataSourceDB.getInstance().getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, idAnuncio);
            try (var rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getBoolean("activo");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
