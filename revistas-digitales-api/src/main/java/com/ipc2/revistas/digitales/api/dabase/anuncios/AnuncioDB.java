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

    private ValidacionesAnuncioDB validacionesAnuncioDB = new ValidacionesAnuncioDB();
    private Connection connection;

    public AnuncioDB() {
        try {
            this.connection = DataSourceDBSingleton.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
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

                validacionesAnuncioDB.desactivarAnuncioVencido(anuncio.getIdAnuncio()); // Con esto se desactivan los anuncios vencidos

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
            e.printStackTrace();
        }

        return anuncio;
    }
    
}
