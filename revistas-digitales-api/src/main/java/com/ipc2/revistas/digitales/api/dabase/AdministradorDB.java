package com.ipc2.revistas.digitales.api.dabase;

import com.ipc2.revistas.digitales.api.modelos.anuncios.AnuncioComprado;
import com.ipc2.revistas.digitales.api.modelos.reporte.ReporteEfectividadAnuncio;
import com.ipc2.revistas.digitales.api.modelos.revista.Revista;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AdministradorDB {

    public List<Revista> obtenerTodasLasRevistas() {
        List<Revista> revistas = new ArrayList<>();
        String consulta = "SELECT * FROM revistas";

        try (Connection connection = DataSourceDB.getInstance().getConnection(); 
                PreparedStatement statement = connection.prepareStatement(consulta); 
                ResultSet resultSet = statement.executeQuery()) {

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

        try (Connection connection = DataSourceDB.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(consulta)) {
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
        try (Connection connection = DataSourceDB.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(consulta)) {
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

        try (Connection connection = DataSourceDB.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(consulta)) {
            statement.setDouble(1, precio);
            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;  // Retorna true si se afectó al menos una fila

        } catch (SQLException e) {
            System.err.println("Error al actualizar el precio global de revistas: " + e.getMessage());
            return false;  // Retorna false si ocurre una excepción
        }
    }

    // Método para actualizar el precio de un anuncio
    public boolean actualizarPrecioAnuncios(String tipo, double nuevoPrecio) {
        String sql = "UPDATE precio_anuncio_dia SET costo = ? WHERE tipo_anuncio = ?";

        try (Connection connection = DataSourceDB.getInstance().getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql)) {
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

    public double obtenerPrecioOcultacionAnuncio() {
        String consulta = "SELECT costo FROM precio_ocultacion_anuncio";
        try (Connection connection = DataSourceDB.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(consulta)) {
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

    public boolean actualizarPrecioOcultacionAnuncio(double precio) {
        String consulta = "UPDATE precio_ocultacion_anuncio SET costo = ?";

        try (Connection connection = DataSourceDB.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(consulta)) {
            statement.setDouble(1, precio);
            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;  // Retorna true si se afectó al menos una fila

        } catch (SQLException e) {
            System.err.println("Error al actualizar el precio global de revistas: " + e.getMessage());
            return false;  // Retorna false si ocurre una excepción
        }
    }

    public List<AnuncioComprado> obtenerAnunciosCompradosFiltro(LocalDate fechaInicio, LocalDate fechaFin, String tipoAnuncio) {
        List<AnuncioComprado> anunciosComprados = new ArrayList<>();
        String query = "SELECT id_pago, nombre_anunciante, fecha_pago, pago, tipo_anuncio FROM pago_anuncios "
                + "WHERE fecha_pago BETWEEN ? AND ?";

        if (tipoAnuncio != null && !tipoAnuncio.isEmpty()) {
            query += " AND tipo_anuncio = ?";
        }

        try (Connection connection = DataSourceDB.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDate(1, java.sql.Date.valueOf(fechaInicio));
            statement.setDate(2, java.sql.Date.valueOf(fechaFin));

            if (tipoAnuncio != null && !tipoAnuncio.isEmpty()) {
                statement.setString(3, tipoAnuncio);
            }

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    AnuncioComprado anuncio = new AnuncioComprado(
                            resultSet.getInt("id_pago"),
                            resultSet.getString("nombre_anunciante"),
                            resultSet.getDate("fecha_pago").toLocalDate(),
                            resultSet.getDouble("pago"),
                            resultSet.getString("tipo_anuncio")
                    );
                    anunciosComprados.add(anuncio);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return anunciosComprados;
    }

    public List<ReporteEfectividadAnuncio> generarReporteEfectividad(LocalDate fechaInicio, LocalDate fechaFin) {
        // Consulta SQL para obtener los anuncios mostrados y sus paths
        String sql = "SELECT nombre_anunciante, id_anuncio, tipo_anuncio, "
                + "GROUP_CONCAT(path_mostrado SEPARATOR ', ') AS paths_mostrados, "
                + "COUNT(*) AS cantidad_mostrado "
                + "FROM anuncio_mostrado "
                + "WHERE fecha BETWEEN ? AND ? "
                + "GROUP BY nombre_anunciante, id_anuncio, tipo_anuncio "
                + "ORDER BY nombre_anunciante, cantidad_mostrado DESC";

        try (Connection connection = DataSourceDB.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, Date.valueOf(fechaInicio));  // Establecer fecha de inicio
            statement.setDate(2, Date.valueOf(fechaFin));     // Establecer fecha de fin

            // Ejecutar la consulta
            ResultSet resultSet = statement.executeQuery();

            List<ReporteEfectividadAnuncio> reportes = new ArrayList<>();

            while (resultSet.next()) {
                String nombreAnunciante = resultSet.getString("nombre_anunciante");
                int idAnuncio = resultSet.getInt("id_anuncio");
                String tipoAnuncio = resultSet.getString("tipo_anuncio");
                String pathsMostradosStr = resultSet.getString("paths_mostrados");
                int cantidadMostrado = resultSet.getInt("cantidad_mostrado");

                // Crear un objeto de ReporteEfectividadAnuncio con los resultados
                reportes.add(new ReporteEfectividadAnuncio(nombreAnunciante, idAnuncio, tipoAnuncio, pathsMostradosStr, cantidadMostrado));
            }

            return reportes;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error generando reporte de efectividad de anuncios", e);
        }
    }
}
