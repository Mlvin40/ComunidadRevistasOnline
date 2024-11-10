/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.revistas.digitales.api.dabase;

/**
 *
 * @author melvin
 */
import com.ipc2.revistas.digitales.api.modelos.usuarios.Rol;
import com.ipc2.revistas.digitales.api.modelos.usuarios.Seguridad;
import com.ipc2.revistas.digitales.api.modelos.usuarios.Usuario;
import java.io.InputStream;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDB {

    public boolean registrarUsuario(Usuario usuario) {
        if (existeUsuario(usuario.getNombreUsuario())) {
            return false; // El usuario ya existe en la base de datos
        }

        String consulta = "INSERT INTO usuarios (nombre_usuario, contraseña, perfil, rol) VALUES (?, ?, ?, ?)";

        try (Connection connection = DataSourceDB.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(consulta)) {
            // Establecer los parámetros en el PreparedStatement
            statement.setString(1, usuario.getNombreUsuario());
            statement.setString(2, usuario.getContrasena());
            statement.setString(3, usuario.getTexto()); // Se usa String aquí
            statement.setString(4, usuario.getRol().toString()); // Asumimos que Rol es un enum

            // Ejecutar la consulta y obtener el número de filas afectadas
            int filasAfectadas = statement.executeUpdate();

            // Si se afectó al menos una fila, la inserción fue exitosa
            return filasAfectadas > 0;

        } catch (SQLException e) {
            // Imprimir el error en la salida estándar de errores
            System.err.println("Error al registrar usuario: " + e.getMessage());
            e.printStackTrace();  // Esto ayuda a rastrear el error en detalle
            return false;
        }
    }

    private boolean existeUsuario(String nombreUsuario) {
        String consulta = "SELECT COUNT(*) FROM usuarios WHERE nombre_usuario = ?";
        try (Connection connection = DataSourceDB.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(consulta)) {
            statement.setString(1, nombreUsuario);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1); // Obtiene el conteo de usuarios
                    return count > 0; // Devuelve true si hay uno o más usuarios
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
        // Devuelve false si no hay usuarios o se produjo un error
    }

    public Usuario iniciarSesion(String nombreUsuario, String contrasena) {
        Seguridad seguridad = new Seguridad();
        Usuario usuarioObtenido = obtenerUsuario(nombreUsuario);

        if (usuarioObtenido == null) {
            return null;
        }
        // Si la contraseña coincide con el hash almacenado, devuelve un objeto Usuario
        if (seguridad.verificarContrasena(contrasena, usuarioObtenido.getContrasena())) {
            System.out.println("Contraseña correcta");
            return usuarioObtenido;
        }

        // Si no se encontró un usuario con las credenciales dadas, devuelve null
        return null;
    }

    public Usuario obtenerUsuario(String nombreUsuario) {
        String consulta = "SELECT * FROM usuarios WHERE nombre_usuario = ?";
        try (Connection connection = DataSourceDB.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(consulta)) {
            statement.setString(1, nombreUsuario);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    //Para castear el rol al enum correspondiente
                    String rol = resultSet.getString("rol");
                    return new Usuario(
                            resultSet.getString("nombre_usuario"),
                            resultSet.getString("contraseña"),
                            resultSet.getString("perfil"),
                            resultSet.getBytes("foto_perfil"),
                            Rol.valueOf(rol),
                            resultSet.getDate("fecha_creacion").toLocalDate()
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void actualizarUsuario(String nombreUsuario, String texto, InputStream fotoPerfil) {
        // Construimos la consulta SQL que solo actualizará los campos necesarios
        StringBuilder consulta = new StringBuilder("UPDATE usuarios SET perfil = ?");

        // Agregar la columna de foto_perfil solo si no es nulo
        if (fotoPerfil != null) {
            consulta.append(", foto_perfil = ?");
        }

        consulta.append(" WHERE nombre_usuario = ?");

        try (Connection connection = DataSourceDB.getInstance().getConnection();
                PreparedStatement stmt = connection.prepareStatement(consulta.toString())) {
            stmt.setString(1, texto); // Establece el texto del perfil

            int parameterIndex = 2; // Índice para los parámetros
            // Solo se agrega el parámetro de la foto_perfil si no es nulo
            if (fotoPerfil != null) {
                stmt.setBlob(parameterIndex++, fotoPerfil); // Establece la foto de perfil
            }
            stmt.setString(parameterIndex, nombreUsuario); // Establece el nombre de usuario

            stmt.executeUpdate(); // Ejecuta la actualización
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
