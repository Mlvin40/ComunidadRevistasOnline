/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.revistas.digitales.api.dabase.reportes;

import com.ipc2.revistas.digitales.api.dabase.DataSourceDBSingleton;
import com.ipc2.revistas.digitales.api.dabase.SuscripcionDB;
import com.ipc2.revistas.digitales.api.modelos.reporte.RevistaPopular;
import com.ipc2.revistas.digitales.api.modelos.revista.Suscripcion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author melvin
 */
public class GeneradorRevistaPopular {

    private SuscripcionDB suscripcionDB = new SuscripcionDB();
    private Connection connection;

    public GeneradorRevistaPopular() {
        try {
            this.connection = DataSourceDBSingleton.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<RevistaPopular> obtenerTop5() {
        List<RevistaPopular> revistasPopulares = new ArrayList<>();

        String sql = "SELECT r.nombre_revista, COUNT(s.id_suscripcion) AS total_suscripciones "
                + "FROM revistas r "
                + "JOIN suscripciones s ON r.nombre_revista = s.nombre_revista "
                + "WHERE s.estado = TRUE "
                + "GROUP BY r.nombre_revista "
                + "ORDER BY total_suscripciones DESC "
                + "LIMIT 5";

        try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String nombreRevista = rs.getString("nombre_revista");

                // Crear objeto RevistaPopular
                RevistaPopular revistaPopular = new RevistaPopular();
                revistaPopular.setNombre(nombreRevista);

                // Obtener las suscripciones de cada revista popular
                List<Suscripcion> suscripciones = suscripcionDB.obtenerSuscripcionesPorRevista(nombreRevista);
                revistaPopular.setSuscripciones(suscripciones);

                revistasPopulares.add(revistaPopular);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return revistasPopulares;
    }
}
