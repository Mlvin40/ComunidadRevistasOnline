package com.ipc2.revistas.digitales.api.dabase;

import com.ipc2.revistas.digitales.api.modelos.revista.Revista;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdministradorDB {

    private Connection connection;

    public AdministradorDB() {
        try {
            this.connection = DataSourceDBSingleton.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Revista> obtenerTodasLasRevistas() {
        List<Revista> revistas = new ArrayList<>();
        String consulta = "SELECT * FROM revistas";

        try (PreparedStatement statement = connection.prepareStatement(consulta)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Revista revista = new Revista(
                        resultSet.getString("nombre_revista"),
                        resultSet.getString("descripcion"),
                        resultSet.getString("categoria"),
                        resultSet.getDate("fecha_creacion").toLocalDate(),
                        resultSet.getString("autor"),
                        resultSet.getDouble("costo"),
                        resultSet.getBoolean("estado_comentar"),
                        resultSet.getBoolean("estado_megusta"),
                        resultSet.getBoolean("estado_suscribirse")
                );
                revistas.add(revista);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return revistas;
    }

    public boolean actualizarPrecioRevista(String nombreRevista, double nuevoPrecio) {
        String consulta = "UPDATE revistas SET costo = ? WHERE nombre_revista = ?";

        try (PreparedStatement statement = connection.prepareStatement(consulta)) {
            statement.setDouble(1, nuevoPrecio);
            statement.setString(2, nombreRevista);

            int filasActualizadas = statement.executeUpdate();
            return filasActualizadas > 0;  // Retorna true si se actualizó al menos una fila
        } catch (SQLException e) {
            System.out.println("Error al actualizar precio de revista: " + e.getMessage());
            return false;  // Retorna false si ocurre una excepción
        }
    }

}
