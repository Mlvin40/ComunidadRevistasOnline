package com.ipc2.revistas.digitales.api.dabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MeGustaDB {

    private Connection connection;

    public MeGustaDB() {
        try {
            this.connection = DataSourceDBSingleton.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int obtenerCantidadMeGustaPorRevista(String nombreRevista) {
        int cantidadMeGusta = 0;
        String consulta = "SELECT COUNT(*) FROM me_gusta WHERE nombre_revista = ?";

        try (PreparedStatement stmt = connection.prepareStatement(consulta)) {
            stmt.setString(1, nombreRevista);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cantidadMeGusta = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cantidadMeGusta;
    }

    // Método para dar like a una revista
    public void darLike(String nombreRevista, String nombreUsuario) throws SQLException {
        // Verificar si el usuario ya ha dado like a esta revista
        if (haDadoLike(nombreRevista, nombreUsuario)) {
            System.out.println("El usuario ya ha dado like a esta revista.");
            return;
        }

        // Insertar el registro de like en la tabla me_gusta
        String consulta = "INSERT INTO me_gusta (nombre_revista, nombre_usuario) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(consulta)) {
            statement.setString(1, nombreRevista);
            statement.setString(2, nombreUsuario);
            statement.executeUpdate();
        }
    }

    // Verificar si un usuario ya ha dado like a una revista
    private boolean haDadoLike(String nombreRevista, String nombreUsuario) throws SQLException {
        String consulta = "SELECT COUNT(*) FROM me_gusta WHERE nombre_revista = ? AND nombre_usuario = ?";

        try (PreparedStatement statement = connection.prepareStatement(consulta)) {
            statement.setString(1, nombreRevista);
            statement.setString(2, nombreUsuario);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    // si la cantidad de registros es mayor a 0, significa que el usuario ya ha dado like
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

}
