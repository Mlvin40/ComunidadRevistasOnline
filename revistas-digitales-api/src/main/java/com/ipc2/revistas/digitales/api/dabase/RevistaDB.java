package com.ipc2.revistas.digitales.api.dabase;

import com.ipc2.revistas.digitales.api.modelos.revista.Revista;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RevistaDB {

    private Connection connection;

    public RevistaDB() {
        try {
            this.connection = DataSourceDBSingleton.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean crearRevista(Revista revista) {
        if (existeRevista(revista.getNombre())) {
            return false; // Significa que la revista ya existe en la base de datos
        }

        String consulta = "INSERT INTO revistas (nombre_revista, descripcion, categoria, fecha_creacion, autor, costo) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(consulta)) {
            statement.setString(1, revista.getNombre());
            statement.setString(2, revista.getDescripcion());
            statement.setString(3, revista.getCategoria());

            // Convertir LocalDate a java.sql.Date
            java.sql.Date sqlDate = java.sql.Date.valueOf(revista.getFechaCreacion());
            statement.setDate(4, sqlDate);

            statement.setString(5, revista.getAutor());
            statement.setDouble(6, revista.getCosto());

            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0; // Si se actualizan filas, significa que la inserción fue exitosa
        } catch (SQLException e) {
            System.err.println("Error al crear revista: " + e.getMessage());
            e.printStackTrace(); // Imprime el rastro de la excepción para depuración
            return false;
        }
    }

    public boolean existeRevista(String nombreRevista) {
        //eliminar espacios en blanco 
        nombreRevista = nombreRevista.trim();
        String consulta = "SELECT COUNT(*) FROM revistas WHERE nombre_revista = ?";
        try (PreparedStatement statement = connection.prepareStatement(consulta)) {
            statement.setString(1, nombreRevista);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0; // Devuelve true si ya existe la revista
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar revista: " + e.getMessage());
            return false;
        }
        return false;
    }

    //Para obtener una revista de la base de datos, si devuelve null es porque no existe la revista
    public Revista obtenerRevista(String nombreRevista) {
        String consulta = "SELECT * FROM revistas WHERE nombre_revista = ?";
        try (PreparedStatement statement = connection.prepareStatement(consulta)) {
            statement.setString(1, nombreRevista);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {

                    String nombre = resultSet.getString("nombre_revista");
                    String descripcion = resultSet.getString("descripcion");
                    //String contenido = resultSet.getString("contenido");  /// ver si sirve o no 
                    String categoria = resultSet.getString("categoria");

                    //Castear la fecha de creacion a LocalDate
                    String fechaString = resultSet.getString("fecha_creacion");
                    LocalDate fechaCreacion = fechaString != null ? LocalDate.parse(fechaString) : null; // Manejar posibles valores nulos

                    String autor = resultSet.getString("autor");
                    double costo = resultSet.getDouble("costo");
                    boolean estadoComentar = resultSet.getBoolean("estado_comentar");
                    boolean estadoMeGusta = resultSet.getBoolean("estado_megusta");
                    boolean estadoSucribirse = resultSet.getBoolean("estado_suscribirse");

                    return new Revista(nombre, descripcion, categoria, fechaCreacion, autor, costo, estadoComentar, estadoMeGusta, estadoSucribirse);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar revista: " + e.getMessage());
        }
        return null;
    }

    //Método para obtener todas las revistas que estan disponibles Para realizar una suscripcion para los usuarios 
    public List<Revista> obtenerRevistasDisponibles(List<String> revistasSuscritas) {
        List<Revista> revistasDisponibles = new ArrayList<>();

        // Construir la consulta SQL con el número adecuado de parámetros
        StringBuilder consultaBuilder = new StringBuilder("SELECT * FROM revistas");

        if (revistasSuscritas != null && !revistasSuscritas.isEmpty()) {
            consultaBuilder.append(" WHERE nombre_revista NOT IN (");
            consultaBuilder.append(String.join(",", Collections.nCopies(revistasSuscritas.size(), "?")));
            consultaBuilder.append(")");
        }

        String consulta = consultaBuilder.toString();

        try (PreparedStatement statement = connection.prepareStatement(consulta)) {
            // Establecer los parámetros en el PreparedStatement
            int index = 1;
            if (revistasSuscritas != null && !revistasSuscritas.isEmpty()) {
                for (String revista : revistasSuscritas) {
                    statement.setString(index++, revista);
                }
            }
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {

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
                    revistasDisponibles.add(revista);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener todas las revistas: " + e.getMessage());
        }

        return revistasDisponibles;
    }

    //Metodo para obtener todas las revistas de una categoria especifica y luego ir comparando con las revistas suscritas
    public List<String> obtenerRevistasPorCategoria(String categoria) {
        String sql = "SELECT nombre_revista FROM revistas WHERE categoria = ?";

        List<String> revistas = new ArrayList<>();
        //Utilizar try-with-resources para cerrar los recursos

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, categoria);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    revistas.add(resultSet.getString("nombre_revista"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener revistas por categoria: " + e.getMessage());
        }
        return revistas;
    }
}
