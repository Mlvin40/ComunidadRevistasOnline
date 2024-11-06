package com.ipc2.revistas.digitales.api.dabase;

import com.ipc2.revistas.digitales.api.modelos.revista.Revista;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SuscriptorDB {

    private static Connection connection;

    public SuscriptorDB() {
        try {
            this.connection = DataSourceDBSingleton.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    // Método para obtener las revistas a las que el usuario está suscrito usando el nombre de usuario
    public List<Revista> obtenerRevistasPorSuscriptor(String nombreUsuario) {
        List<Revista> revistas = new ArrayList<>();

        // Consulta que une las tablas "revistas" y "suscripciones" usando "nombre_usuario" y "nombre_revista"
        String consulta = "SELECT r.* FROM revistas r "
                + "JOIN suscripciones s ON r.nombre_revista = s.nombre_revista "
                + "WHERE s.nombre_usuario = ? AND s.estado = 'ACTIVA'";

        try (PreparedStatement statement = connection.prepareStatement(consulta)) {
            // Establecer el valor del nombre de usuario (suscriptor)
            statement.setString(1, nombreUsuario);

            try (ResultSet resultSet = statement.executeQuery()) {
                // Iterar sobre el resultado y agregar las revistas a la lista
                while (resultSet.next()) {
                    Revista revista = new Revista(
                            resultSet.getString("nombre_revista"),
                            resultSet.getString("descripcion"),
                            resultSet.getString("categoria"),
                            resultSet.getDate("fecha_creacion").toLocalDate(),
                            resultSet.getString("autor"),
                            resultSet.getDouble("costo"),
                            //resultSet.getString("url_pdf"),
                            resultSet.getBoolean("estado_comentar"),
                            resultSet.getBoolean("estado_megusta"),
                            resultSet.getBoolean("estado_suscribirse")
                    );
                    revistas.add(revista);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener revistas por suscriptor: " + e.getMessage());
        }

        return revistas;
    }

    // Método para obtener las revistas suscritas de un usuario
    public List<String> obtenerRevistasSuscritas(String nombreUsuario) {
        List<String> revistasSuscritas = new ArrayList<>();
        String consulta = "SELECT nombre_revista FROM suscripciones WHERE nombre_usuario = ?";

        try (PreparedStatement statement = connection.prepareStatement(consulta)) {
            statement.setString(1, nombreUsuario);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    revistasSuscritas.add(resultSet.getString("nombre_revista"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener revistas suscritas: " + e.getMessage());
        }
        return revistasSuscritas;
    }

    // Método para suscribirse a una revista nueva -- 
    public boolean suscribirUsuarioARevista(String nombreUsuario, String nombreRevista, LocalDate fechaSuscripcion) {
        String consulta = "INSERT INTO suscripciones (nombre_usuario, nombre_revista, fecha_suscripcion) VALUES (?, ?, ?)";

        // Verificar si las suscripciones están permitidas de lo contrario no se puede suscribir
        if (!verificarEstadoSuscripcion(nombreRevista)) {
            System.out.println("No se puede suscribir a la revista: " + nombreRevista);
            return false;
        }

        try (PreparedStatement statement = connection.prepareStatement(consulta)) {
            statement.setString(1, nombreUsuario);
            statement.setString(2, nombreRevista);
            statement.setDate(3, java.sql.Date.valueOf(fechaSuscripcion));

            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println("Error al suscribirse a la revista: " + e.getMessage());
            return false;
        }
    }

    //metodo que se encarga de ver si el usuario ya esta suscrito a la revista
    public boolean verificarEstaSuscrito(String nombreUsuario, String nombreRevista) {
        String consulta = "SELECT * FROM suscripciones WHERE nombre_usuario = ? AND nombre_revista = ? ";
        try (PreparedStatement statement = connection.prepareStatement(consulta)) {
            statement.setString(1, nombreUsuario);
            statement.setString(2, nombreRevista);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); // Retorna true si existe una suscripción activa
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar suscripción: " + e.getMessage());
            return false;
        }
    }

    //Validacion para que al momento de una suscipcion se consulte antes de asignarle la revista al usuario
    private boolean verificarEstadoSuscripcion(String nombreRevista) {
        String consulta = "SELECT estado_suscribirse FROM revistas WHERE nombre_revista = ?";
        try (PreparedStatement statement = connection.prepareStatement(consulta)) {
            statement.setString(1, nombreRevista);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getBoolean("estado_suscribirse"); // Verifica si las suscripciones están permitidas
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar el estado de suscripciones: " + e.getMessage());
        }
        return false; // Retorna false si no encuentra la revista o hay un error
    }

}
