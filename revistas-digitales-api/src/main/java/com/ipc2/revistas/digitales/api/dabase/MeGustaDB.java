package com.ipc2.revistas.digitales.api.dabase;

import com.ipc2.revistas.digitales.api.modelos.revista.MeGusta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
        // Verificar si se puede dar like a la revista
        if (!sePuedeDarLike(nombreRevista)) {
            System.out.println("No se puede dar like a esta revista.");
            return;
        }

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

    private boolean sePuedeDarLike(String nombreRevista) throws SQLException {
        String consulta = "SELECT estado_megusta FROM revistas WHERE nombre_revista = ?";

        try (PreparedStatement statement = connection.prepareStatement(consulta)) {
            statement.setString(1, nombreRevista);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {

                    // Si el estado_megusta es true, significa que se puede dar like
                    return rs.getBoolean("estado_megusta");
                }
            }
        }
        return false;  // Si no se encuentra la revista o estado_megusta es false, no se puede dar like
    }

    // Método para recuperar todos los "Me Gusta" de la base de datos
    public List<MeGusta> obtenerTodosMeGusta() {
        List<MeGusta> listaMeGusta = new ArrayList<>();

        String consulta = "SELECT id_me_gusta, nombre_revista, nombre_usuario, fecha_me_gusta FROM me_gusta";

        try (PreparedStatement stmt = connection.prepareStatement(consulta); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id_me_gusta");
                String nombreRevista = rs.getString("nombre_revista");
                String nombreUsuario = rs.getString("nombre_usuario");
                LocalDate fechaMeGusta = rs.getDate("fecha_me_gusta").toLocalDate();

                MeGusta meGusta = new MeGusta(id, nombreRevista, nombreUsuario, fechaMeGusta);
                listaMeGusta.add(meGusta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaMeGusta;
    }

    // Método para recuperar todos los "Me Gusta" de una revista específica
    public List<MeGusta> obtenerMeGustaPorRevista(String nombreRevista) {
        List<MeGusta> listaMeGusta = new ArrayList<>();

        String consulta = "SELECT id_me_gusta, nombre_revista, nombre_usuario, fecha_me_gusta "
                + "FROM me_gusta WHERE nombre_revista = ?";

        try (PreparedStatement stmt = connection.prepareStatement(consulta)) {
            stmt.setString(1, nombreRevista);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id_me_gusta");
                    String nombreUsuario = rs.getString("nombre_usuario");
                    LocalDate fechaMeGusta = rs.getDate("fecha_me_gusta").toLocalDate();

                    MeGusta meGusta = new MeGusta(id, nombreRevista, nombreUsuario, fechaMeGusta);
                    listaMeGusta.add(meGusta);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return listaMeGusta;
    }

}
