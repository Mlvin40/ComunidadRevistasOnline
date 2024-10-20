/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.revistas.digitales.api.dabase;

import com.ipc2.revistas.digitales.api.modelos.usuarios.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author melvin
 */
public class CarteraDB {

    private Connection connection;

    public CarteraDB() {
        try {
            this.connection = DataSourceDBSingleton.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    // Método para crear una cartera de anunciantes
    public void crearCartera(Usuario usuario) {

        String consulta = "INSERT INTO cartera_anunciantes (nombre_anunciante) VALUES (?)";
        try {
            PreparedStatement statement = connection.prepareStatement(consulta);
            statement.setString(1, usuario.getNombreUsuario());
            statement.executeUpdate();
            System.out.println("Cartera creada con éxito");
        } catch (SQLException e) {
            System.out.println("Error al crear la cartera: " + e.getMessage());
        }
    }

    public boolean recargarCartera(String nombreUsuario, double monto) {
        String consulta = "UPDATE cartera_anunciantes SET saldo = saldo + ? WHERE nombre_anunciante = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(consulta);
            statement.setDouble(1, monto);
            statement.setString(2, nombreUsuario);
            statement.executeUpdate();
            System.out.println("Cartera recargada con éxito");
            return true;
        } catch (SQLException e) {
            System.out.println("Error al recargar la cartera: " + e.getMessage());
            return false;
        }
    }

    public double obtenerSaldoActual(String nombreUsuario) {
        String consulta = "SELECT saldo FROM cartera_anunciantes WHERE nombre_anunciante = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(consulta);
            statement.setString(1, nombreUsuario);

            // Ejecutar la consulta y obtener el ResultSet
            ResultSet rs = statement.executeQuery();

            // Verificar si hay un resultado y mover el cursor a la primera fila
            if (rs.next()) {
                // Obtener el valor del saldo
                return rs.getDouble("saldo");
            }

            // Si no hay resultados, retornar un valor indicativo
            return 0.0;

        } catch (SQLException e) {
            System.out.println("Error al obtener el saldo de la cartera: " + e.getMessage());
            return -1;
        }
    }

}
