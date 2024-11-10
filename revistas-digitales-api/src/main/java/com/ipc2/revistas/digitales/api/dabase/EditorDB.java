package com.ipc2.revistas.digitales.api.dabase;

import com.ipc2.revistas.digitales.api.modelos.revista.Revista;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EditorDB {

    // Método para obtener revistas por autor
    public List<Revista> obtenerRevistasPorAutor(String idAutor) {
        List<Revista> revistas = new ArrayList<>();
        String consulta = "SELECT * FROM revistas WHERE autor = ?";

        try (Connection connection = DataSourceDB.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(consulta)) {
            statement.setString(1, idAutor);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    // Casteo de la fecha de creación a LocalDate
                    String fechaString = resultSet.getString("fecha_creacion");
                    LocalDate fechaCreacion = fechaString != null ? LocalDate.parse(fechaString) : null; // Manejar posibles valores nulos

                    Revista revista = new Revista(
                            resultSet.getString("nombre_revista"),
                            resultSet.getString("descripcion"),
                            resultSet.getString("categoria"),
                            fechaCreacion,
                            resultSet.getString("autor"),
                            resultSet.getDouble("costo"),
                            resultSet.getBoolean("estado_comentar"),
                            resultSet.getBoolean("estado_megusta"),
                            resultSet.getBoolean("estado_suscribirse")
                    );
                    revistas.add(revista);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener las revistas para el autor '" + idAutor + "': " + e.getMessage());
            e.printStackTrace();
        }

        return revistas;
    }

    public Revista obtenerRevistaPorNombre(String nombreRevista) {
        String consulta = "SELECT * FROM revistas WHERE nombre_revista = ?";
        try (Connection connection = DataSourceDB.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(consulta)) {
            statement.setString(1, nombreRevista);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {

                    //Casteo de la fecha de creacion a localdate
                    String fechaString = resultSet.getString("fecha_creacion");
                    LocalDate fechaCreacion = fechaString != null ? LocalDate.parse(fechaString) : null; // Manejar posibles valores nulos

                    Revista revista = new Revista(
                            resultSet.getString("nombre_revista"),
                            resultSet.getString("descripcion"),
                            resultSet.getString("categoria"),
                            fechaCreacion,
                            resultSet.getString("autor"),
                            resultSet.getDouble("costo"),
                            resultSet.getBoolean("estado_comentar"),
                            resultSet.getBoolean("estado_megusta"),
                            resultSet.getBoolean("estado_suscribirse")
                    );
                    return revista;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar revista: " + e.getMessage());
        }
        return null;
    }

    public boolean actualizarRevista(Revista revista) {
        String consulta = "UPDATE revistas SET descripcion = ?, categoria = ?, estado_comentar = ?, estado_megusta = ?, estado_suscribirse = ? WHERE nombre_revista = ?";
        try (Connection connection = DataSourceDB.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(consulta)) {
            statement.setString(1, revista.getDescripcion());
            statement.setString(2, revista.getCategoria());
            statement.setBoolean(3, revista.isEstadoComentar());
            statement.setBoolean(4, revista.isEstadoMeGusta());
            statement.setBoolean(5, revista.isEstadoSuscribirse());
            statement.setString(6, revista.getNombre());

            // Ejecutar la actualización y obtener el número de filas afectadas
            int filasAfectadas = statement.executeUpdate();

            // Retornar true si se actualizó al menos una fila, false en caso contrario
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar revista: " + e.getMessage());
            return false; // Retornar false en caso de excepción
        }
    }

}
