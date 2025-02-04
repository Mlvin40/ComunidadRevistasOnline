package com.ipc2.revistas.digitales.api.dabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class PagoAnuncioDB {

    public void registrarPagoAnuncio(String nombreAnunciante, LocalDate fechaPago, Double pago, String tipoAnuncio) {
        String consulta = "INSERT INTO pago_anuncios (nombre_anunciante, fecha_pago, pago, tipo_anuncio) VALUES (?, ?, ?, ?)";

        try (Connection connection = DataSourceDB.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(consulta)) {
            statement.setString(1, nombreAnunciante);
            statement.setDate(2, java.sql.Date.valueOf(fechaPago));
            statement.setDouble(3, pago);
            statement.setString(4, tipoAnuncio);
            
            statement.executeUpdate();
            System.out.println("Pago registrado exitosamente para el anunciante: " + nombreAnunciante);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al registrar el pago para el anunciante: " + nombreAnunciante);
        }
    }

}
