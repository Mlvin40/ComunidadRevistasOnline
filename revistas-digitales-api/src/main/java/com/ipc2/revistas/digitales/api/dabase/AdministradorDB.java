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

    public double obtenerPrecioGlobalRevista() {
        String consulta = "SELECT costo FROM precio_global_revistas";
        try (PreparedStatement statement = connection.prepareStatement(consulta)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getDouble("costo");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar precio de revistas: " + e.getMessage());
        }
        return 0;
    }

    public boolean actualizarPrecioGlobalRevista(double precio) {
        String consulta = "UPDATE precio_global_revistas SET costo = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(consulta);
            statement.setDouble(1, precio);
            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar precio de revistas: " + e.getMessage());
            return false;
        }
    }

    // Método para actualizar el precio de un anuncio
    public boolean actualizarPrecioAnuncios(String tipo, double nuevoPrecio) {
        String sql = "UPDATE precio_anuncio_dia SET costo = ? WHERE tipo_anuncio = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            // Establecer los parámetros
            pstmt.setDouble(1, nuevoPrecio);
            pstmt.setString(2, tipo);

            // Ejecutar la actualización
            int filasAfectadas = pstmt.executeUpdate();

            // Si se actualizó al menos una fila, la actualización fue exitosa
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
