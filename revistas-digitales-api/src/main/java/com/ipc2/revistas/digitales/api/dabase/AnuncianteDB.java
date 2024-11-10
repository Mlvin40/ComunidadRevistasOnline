/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.revistas.digitales.api.dabase;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author melvin
 */
public class AnuncianteDB {

    private Connection connection;

    public AnuncianteDB() {
        try {
            this.connection = DataSourceDB.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}
