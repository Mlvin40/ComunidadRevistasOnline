/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.revistas.digitales.api.dabase.anuncios;

import com.ipc2.revistas.digitales.api.dabase.DataSourceDBSingleton;
import com.ipc2.revistas.digitales.api.dabase.DataSourceDBSingleton;
import com.ipc2.revistas.digitales.api.modelos.anuncios.Anuncio;

import java.io.InputStream;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author melvin
 */
public class AnuncioDB {

    private Connection connection;

    public AnuncioDB() {
        try {
            this.connection = DataSourceDBSingleton.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Método para insertar un anuncio de texto
    public boolean crearAnuncioTexto(String contenidoTexto, String nombreAnunciante, LocalDate fechaInicio, int duracionDias) {
        String sql = "INSERT INTO anuncios (tipo_anuncio, contenido_texto, nombre_anunciante, fecha_inicio, duracion_dias, fecha_expiracion) "
                + "VALUES (?, ?, ?, ?, ?, DATE_ADD(?, INTERVAL ? DAY))";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, "TEXTO");
            pstmt.setString(2, contenidoTexto);
            pstmt.setString(3, nombreAnunciante);
            pstmt.setDate(4, java.sql.Date.valueOf(fechaInicio));  // Convertir LocalDate a java.sql.Date
            pstmt.setInt(5, duracionDias);
            pstmt.setDate(6, java.sql.Date.valueOf(fechaInicio));
            pstmt.setInt(7, duracionDias); // Usar directamente el número de días
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para insertar un anuncio de texto e imagen
    public boolean crearAnuncioTextoImagen(String contenidoTexto, InputStream imagen, String nombreAnunciante, LocalDate fechaInicio, int duracionDias) {
        String sql = "INSERT INTO anuncios (tipo_anuncio, contenido_texto, imagen, nombre_anunciante, fecha_inicio, duracion_dias, fecha_expiracion) "
                + "VALUES (?, ?, ?, ?, ?, ?, DATE_ADD(?, INTERVAL ? DAY))";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, "TEXTO_IMAGEN");
            pstmt.setString(2, contenidoTexto);
            pstmt.setBlob(3, imagen);
            pstmt.setString(4, nombreAnunciante);
            pstmt.setDate(5, java.sql.Date.valueOf(fechaInicio));  // Convertir LocalDate a java.sql.Date
            pstmt.setInt(6, duracionDias);
            pstmt.setDate(7, java.sql.Date.valueOf(fechaInicio));
            pstmt.setInt(8, duracionDias); // Usar directamente el número de días
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para insertar un anuncio de video
    public boolean crearAnuncioVideo(String urlVideo, String nombreAnunciante, LocalDate fechaInicio, int duracionDias) {
        String sql = "INSERT INTO anuncios (tipo_anuncio, url_video, nombre_anunciante, fecha_inicio, duracion_dias, fecha_expiracion)"
                + "VALUES (?, ?, ?, ?, ?, DATE_ADD(?, INTERVAL ? DAY))";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, "VIDEO");
            pstmt.setString(2, urlVideo);
            pstmt.setString(3, nombreAnunciante);
            pstmt.setDate(4, java.sql.Date.valueOf(fechaInicio));  // Convertir LocalDate a java.sql.Date
            pstmt.setInt(5, duracionDias);
            pstmt.setDate(6, java.sql.Date.valueOf(fechaInicio));
            pstmt.setInt(7, duracionDias); // Usar directamente el número de días
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para obtener el costo de un anuncio por día
    public double obtenerCostoAnuncioDia(String tipoAnuncio) {
        String sql = "SELECT costo FROM precio_anuncio_dia WHERE tipo_anuncio = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, tipoAnuncio);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("costo");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    // Con este metodo debemos de obtener todos los anuncios de la base de datos y retornarlos
    public List<Anuncio> obtenerTodosLosAnuncios() {
        String sql = "SELECT id_anuncio, tipo_anuncio, contenido_texto, imagen, url_video, nombre_anunciante, "
                + "fecha_inicio, duracion_dias, fecha_expiracion, vencido, activo FROM anuncios";
        List<Anuncio> anuncios = new ArrayList<>();

        try (PreparedStatement pstmt = connection.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Anuncio anuncio = new Anuncio();
                anuncio.setIdAnuncio(rs.getInt("id_anuncio"));
                anuncio.setTipoAnuncio(rs.getString("tipo_anuncio"));
                anuncio.setContenidoTexto(rs.getString("contenido_texto"));
                anuncio.setImagen(rs.getBytes("imagen"));
                anuncio.setUrlVideo(rs.getString("url_video"));
                anuncio.setNombreAnunciante(rs.getString("nombre_anunciante"));
                anuncio.setFechaInicio(rs.getDate("fecha_inicio").toLocalDate());
                anuncio.setDuracion(rs.getInt("duracion_dias"));
                anuncio.setFechaExpiracion(rs.getDate("fecha_expiracion") != null
                        ? rs.getDate("fecha_expiracion").toLocalDate()
                        : null);
                anuncio.setVencido(rs.getBoolean("vencido"));
                anuncio.setActivo(rs.getBoolean("activo"));
                anuncios.add(anuncio);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return anuncios;
    }

    public List<Anuncio> obtenerAnunciosPorAnunciante(String nombreAnunciante) {
        String sql = "SELECT id_anuncio, tipo_anuncio, contenido_texto, imagen, url_video, nombre_anunciante, "
                + "fecha_inicio, duracion_dias, fecha_expiracion, vencido, activo FROM anuncios "
                + "WHERE nombre_anunciante = ?";
        List<Anuncio> anuncios = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, nombreAnunciante); // Establecer el nombre del anunciante en la consulta
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Anuncio anuncio = new Anuncio();
                    anuncio.setIdAnuncio(rs.getInt("id_anuncio"));
                    anuncio.setTipoAnuncio(rs.getString("tipo_anuncio"));
                    anuncio.setContenidoTexto(rs.getString("contenido_texto"));
                    anuncio.setImagen(rs.getBytes("imagen"));
                    anuncio.setUrlVideo(rs.getString("url_video"));
                    anuncio.setNombreAnunciante(rs.getString("nombre_anunciante"));
                    anuncio.setFechaInicio(rs.getDate("fecha_inicio").toLocalDate());
                    anuncio.setDuracion(rs.getInt("duracion_dias"));
                    anuncio.setFechaExpiracion(rs.getDate("fecha_expiracion") != null
                            ? rs.getDate("fecha_expiracion").toLocalDate()
                            : null);
                    anuncio.setVencido(rs.getBoolean("vencido"));
                    anuncio.setActivo(rs.getBoolean("activo"));
                    anuncios.add(anuncio);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return anuncios;
    }

    public Anuncio obtenerAnuncioPorId(int id) {
        String sql = "SELECT id_anuncio, tipo_anuncio, contenido_texto, imagen, url_video, nombre_anunciante, "
                + "fecha_inicio, duracion_dias, fecha_expiracion, vencido, activo FROM anuncios WHERE id_anuncio = ?";

        Anuncio anuncio = null; // Inicializa la variable anuncio

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id); // Establece el ID del anuncio en el PreparedStatement
            ResultSet rs = pstmt.executeQuery(); // Ejecuta la consulta

            // Verifica si se encontró un anuncio
            if (rs.next()) {
                anuncio = new Anuncio(); // Crea un nuevo objeto Anuncio
                anuncio.setIdAnuncio(rs.getInt("id_anuncio"));
                anuncio.setTipoAnuncio(rs.getString("tipo_anuncio"));
                anuncio.setContenidoTexto(rs.getString("contenido_texto"));
                anuncio.setImagen(rs.getBytes("imagen"));
                anuncio.setUrlVideo(rs.getString("url_video"));
                anuncio.setNombreAnunciante(rs.getString("nombre_anunciante"));
                anuncio.setFechaInicio(rs.getDate("fecha_inicio").toLocalDate());
                anuncio.setDuracion(rs.getInt("duracion_dias"));
                anuncio.setFechaExpiracion(rs.getDate("fecha_expiracion") != null
                        ? rs.getDate("fecha_expiracion").toLocalDate()
                        : null);
                anuncio.setVencido(rs.getBoolean("vencido"));
                anuncio.setActivo(rs.getBoolean("activo"));
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de excepciones
        }

        return anuncio; // Retorna el anuncio encontrado o null si no existe
    }

    public boolean actualizarAnuncioTexto(int idAnuncio, String contenidoTexto, boolean activo) {
        String sql = "UPDATE anuncios SET contenido_texto = ?, activo = ? WHERE id_anuncio = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, contenidoTexto); // Set contenidoTexto
            pstmt.setBoolean(2, activo); // Set activo
            pstmt.setInt(3, idAnuncio); // Set idAnuncio

            int filasActualizadas = pstmt.executeUpdate(); // Ejecutar la actualización

            return filasActualizadas > 0; // Retorna verdadero si se actualizó al menos una fila
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Retorna falso en caso de error o si no se actualizó nada
    }


    public boolean actualizarAnuncioTextoImagen(int idAnuncio, String contenidoTexto, InputStream imagenInputStream, boolean activo) {
        // Crea una consulta SQL que solo actualizará los campos que se deben modificar
        StringBuilder sql = new StringBuilder("UPDATE anuncios SET contenido_texto = ?, activo = ?");

        // Agrega el campo de imagen a la consulta solo si no es nulo
        if (imagenInputStream != null) {
            sql.append(", imagen = ?");
        }
        
        sql.append(" WHERE id_anuncio = ?");

        try (PreparedStatement pstmt = connection.prepareStatement(sql.toString())) {
            pstmt.setString(1, contenidoTexto); // Establece el contenidoTexto
            pstmt.setBoolean(2, activo); // Establece el estado activo

            int parameterIndex = 3; // Índice del siguiente parámetro
            // Solo establece el parámetro de la imagen si no es nula
            if (imagenInputStream != null) {
                pstmt.setBlob(parameterIndex++, imagenInputStream); // Establece la imagen como un InputStream
            }
            pstmt.setInt(parameterIndex, idAnuncio); // Establece el idAnuncio

            int filasActualizadas = pstmt.executeUpdate(); // Ejecuta la actualización

            return filasActualizadas > 0; // Retorna verdadero si se actualizó al menos una fila
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Retorna falso en caso de error o si no se actualizó nada
    }
    
    public boolean actualizarAnuncioVideo(int idAnuncio, String urlVideo, boolean activo) {
        String sql = "UPDATE anuncios SET url_video = ?, activo = ? WHERE id_anuncio = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, urlVideo); // Establece la URL del video
            pstmt.setBoolean(2, activo); // Establece el estado activo
            pstmt.setInt(3, idAnuncio); // Establece el idAnuncio

            int filasActualizadas = pstmt.executeUpdate(); // Ejecuta la actualización

            return filasActualizadas > 0; // Retorna verdadero si se actualizó al menos una fila
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Retorna falso en caso de error o si no se actualizó nada
    }
}
