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


// Método para crear una cartera para un usuario
    public void crearCartera(Usuario usuario) {
        String consulta = "INSERT INTO carteras (nombre_usuario, tipo_usuario) VALUES (?, ?)";

        try (Connection connection = DataSourceDB.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(consulta)) {
            statement.setString(1, usuario.getNombreUsuario());
            statement.setString(2, usuario.getRol().toString());
            statement.executeUpdate();
            System.out.println("Cartera creada con éxito");

        } catch (SQLException e) {
            System.err.println("Error al crear la cartera: " + e.getMessage());
        }
    }

    // Método para recargar la cartera
    public boolean recargarCartera(String nombreUsuario, double monto) {
        String consulta = "UPDATE carteras SET saldo = saldo + ? WHERE nombre_usuario = ?";

        try (Connection connection = DataSourceDB.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(consulta)) {
            statement.setDouble(1, monto);
            statement.setString(2, nombreUsuario);
            int filasActualizadas = statement.executeUpdate();

            if (filasActualizadas > 0) {
                System.out.println("Cartera recargada con éxito");
                return true;
            } else {
                System.out.println("No se encontró la cartera para el usuario: " + nombreUsuario);
                return false;
            }

        } catch (SQLException e) {
            System.err.println("Error al recargar la cartera: " + e.getMessage());
            return false;
        }
    }

// Método para obtener el saldo actual de la cartera
    public double obtenerSaldoActual(String nombreUsuario) {
        String consulta = "SELECT saldo FROM carteras WHERE nombre_usuario = ?";

        try (Connection connection = DataSourceDB.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(consulta)) {
            // Establecer el parámetro primero
            statement.setString(1, nombreUsuario);

            // Ahora ejecutamos la consulta
            try (ResultSet rs = statement.executeQuery()) {
                // Verificar si hay un resultado y devolver el saldo
                if (rs.next()) {
                    return rs.getDouble("saldo");
                }

                // Si no hay resultados, retornar un valor indicativo
                return 0.0;
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener el saldo de la cartera: " + e.getMessage());
            return -1;
        }
    }

    // Método para actualizar el saldo de la cartera cuando se realiza una compra de un anuncio
    public boolean actualizarSaldo(String nombreUsuario, double nuevoSaldo) {
        String consulta = "UPDATE carteras SET saldo = ? WHERE nombre_usuario = ?";

        try (Connection connection = DataSourceDB.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(consulta)) {
            statement.setDouble(1, nuevoSaldo);
            statement.setString(2, nombreUsuario);

            int filasActualizadas = statement.executeUpdate();
            return filasActualizadas > 0; // Retorna true si se actualizó alguna fila
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
