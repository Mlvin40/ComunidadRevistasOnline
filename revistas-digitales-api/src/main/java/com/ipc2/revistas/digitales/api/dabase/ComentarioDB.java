package com.ipc2.revistas.digitales.api.dabase;

import com.ipc2.revistas.digitales.api.modelos.revista.Comentario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComentarioDB {

    private Connection connection;

    public ComentarioDB() {
        try {
            this.connection = DataSourceDBSingleton.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Método para obtener los comentarios por revista
    public List<Comentario> obtenerComentariosPorRevista(String nombreRevista) {
        List<Comentario> comentarios = new ArrayList<>();
        String query = "SELECT nombre_revista, nombre_usuario, comentario, fecha_comentario " +
                "FROM comentarios WHERE nombre_revista = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nombreRevista);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // Crear un objeto Comentario con los datos obtenidos de la consulta
                Comentario comentario = new Comentario(
                        rs.getString("nombre_revista"),
                        rs.getString("nombre_usuario"),
                        rs.getString("comentario"),
                        rs.getString("fecha_comentario")
                );
                // Añadir el comentario a la lista de comentarios
                comentarios.add(comentario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comentarios;
    }


    public void guardarComentario(String nombreRevista, String nombreUsuario, String comentarioTexto) {
        String consulta = "INSERT INTO comentarios (nombre_revista, nombre_usuario, comentario) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(consulta)) {
            statement.setString(1, nombreRevista);
            statement.setString(2, nombreUsuario);
            statement.setString(3, comentarioTexto);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
