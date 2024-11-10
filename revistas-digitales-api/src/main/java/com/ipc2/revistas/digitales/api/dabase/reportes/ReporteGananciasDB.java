/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.revistas.digitales.api.dabase.reportes;

import com.ipc2.revistas.digitales.api.dabase.DataSourceDBSingleton;
import com.ipc2.revistas.digitales.api.modelos.anuncios.AnuncioComprado;
import com.ipc2.revistas.digitales.api.modelos.reporte.RevistaMantenimiento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author melvin
 */
public class ReporteGananciasDB {

    private static Connection connection;

    public ReporteGananciasDB() {
        try {
            this.connection = DataSourceDBSingleton.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<AnuncioComprado> obtenerAnunciosCompradosPorFecha(LocalDate fechaInicio, LocalDate fechaFin) {
        List<AnuncioComprado> anunciosComprados = new ArrayList<>();
        String query = "SELECT id_pago, nombre_anunciante, fecha_pago, pago, tipo_anuncio FROM pago_anuncios "
                + "WHERE fecha_pago BETWEEN ? AND ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            // Convertir LocalDate a java.sql.Date
            statement.setDate(1, java.sql.Date.valueOf(fechaInicio));
            statement.setDate(2, java.sql.Date.valueOf(fechaFin));

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

    private List<RevistaMantenimiento> obtenerRevistasMantenimientoPorFecha(LocalDate fechaInicio, LocalDate fechaFin) {
        List<RevistaMantenimiento> revistasMantenimiento = new ArrayList<>();
        String query = "SELECT nombre_revista, costo, fecha_creacion FROM revistas "
                + "WHERE fecha_creacion BETWEEN ? AND ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            // Convertir LocalDate a java.sql.Date
            statement.setDate(1, java.sql.Date.valueOf(fechaInicio));
            statement.setDate(2, java.sql.Date.valueOf(fechaFin));

            LocalDate hoy = LocalDate.now();  // Fecha actual

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String nombreRevista = resultSet.getString("nombre_revista");
                    double costoDiario = resultSet.getDouble("costo");
                    LocalDate fechaCreacion = resultSet.getDate("fecha_creacion").toLocalDate();

                    // Calcular los días transcurridos desde la fecha de creación
                    int diasTranscurridos = (int) java.time.temporal.ChronoUnit.DAYS.between(fechaCreacion, hoy);

                    // Calcular el costo total de la revista (costo diario * días transcurridos)
                    double costoTotalRevista = costoDiario * diasTranscurridos;

                    // Crear el objeto RevistaMantenimiento y agregarlo a la lista
                    RevistaMantenimiento revistaMantenimiento = new RevistaMantenimiento(
                            nombreRevista, costoDiario, fechaCreacion, diasTranscurridos, costoTotalRevista
                    );
                    revistasMantenimiento.add(revistaMantenimiento);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return revistasMantenimiento;
    }

    // Este metodo se encarga de constrir el objeto que tiene la logica de ganancias totales a partir de un intervalo de tiempo
    public TablaReporteGanancia obtenertTabla(LocalDate fechaInicio, LocalDate fechaFin) {
        List<RevistaMantenimiento> revistaMantenimiento = obtenerRevistasMantenimientoPorFecha(fechaInicio, fechaFin);
        List<AnuncioComprado> anuncioComprados = obtenerAnunciosCompradosPorFecha(fechaInicio, fechaFin);

        double ingresos = 0;
        double egresos = 0;

        // calcula el ingreso total 
        for (AnuncioComprado anuncio : anuncioComprados) {
            ingresos += anuncio.getPago();
        }

        // calcula el egreso total 
        for (RevistaMantenimiento revista : revistaMantenimiento) {
            egresos += revista.getCostoTotalRevista();
        }
        double ganancias = ingresos - egresos;

        return new TablaReporteGanancia(revistaMantenimiento, anuncioComprados, ingresos, egresos, ganancias);

    }
}
