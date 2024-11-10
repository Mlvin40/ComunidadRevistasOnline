package com.ipc2.revistas.digitales.api.dabase;

import com.ipc2.revistas.digitales.api.modelos.revista.Comentario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComentarioDB {


    public List<Comentario> obtenerTodosLosComentarios() {
        List<Comentario> comentarios = new ArrayList<>();
        String query = "SELECT id_comentario, nombre_revista, nombre_usuario, comentario, fecha_comentario FROM comentarios";

        try (Connection connection = DataSourceDB.getInstance().getConnection();
                PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Comentario comentario = new Comentario(
                        rs.getInt("id_comentario"),
                        rs.getString("nombre_revista"),
                        rs.getString("nombre_usuario"),
                        rs.getString("comentario"),
                        rs.getDate("fecha_comentario").toLocalDate()
                );
                // Añadir el comentario a la lista de comentarios
                comentarios.add(comentario);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener todos los comentarios: " + e.getMessage());
        }
        return comentarios;
    }

    // Método para obtener los comentarios por revista
    public List<Comentario> obtenerComentariosPorRevista(String nombreRevista) {
        List<Comentario> comentarios = new ArrayList<>();
        String query = "SELECT id_comentario, nombre_revista, nombre_usuario, comentario, fecha_comentario "
                + "FROM comentarios WHERE nombre_revista = ?";

        try (Connection connection = DataSourceDB.getInstance().getConnection();
                PreparedStatement stmt = connection.prepareStatement(query)) {
            // Establecer el parámetro primero
            stmt.setString(1, nombreRevista);

            // Ahora ejecutar la consulta
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // Crear un objeto Comentario con los datos obtenidos de la consulta
                    Comentario comentario = new Comentario(
                            rs.getInt("id_comentario"),
                            rs.getString("nombre_revista"),
                            rs.getString("nombre_usuario"),
                            rs.getString("comentario"),
                            rs.getDate("fecha_comentario").toLocalDate()
                    );
                    // Añadir el comentario a la lista de comentarios
                    comentarios.add(comentario);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener comentarios de la revista '" + nombreRevista + "': " + e.getMessage());
        }
        return comentarios;
    }

    public void guardarComentario(String nombreRevista, String nombreUsuario, String comentarioTexto) {
        String consulta = "INSERT INTO comentarios (nombre_revista, nombre_usuario, comentario) VALUES (?, ?, ?)";

        // Verificar si se puede comentar en la revista, si devuelve false, no se puede comentar
        if (!sePuedeComentar(nombreRevista)) {
            return;
        }

        try (Connection connection = DataSourceDB.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(consulta)) {
            statement.setString(1, nombreRevista);
            statement.setString(2, nombreUsuario);
            statement.setString(3, comentarioTexto);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean sePuedeComentar(String nombreRevista) {
        String sql = "SELECT estado_comentar FROM revistas WHERE nombre_revista = ?";
        try (Connection connection = DataSourceDB.getInstance().getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, nombreRevista);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {

                    // Retornar el valor de estado_comentar
                    return rs.getBoolean("estado_comentar");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Si la revista no existe o hay un error, no se puede comentar
        return false;
    }

}
