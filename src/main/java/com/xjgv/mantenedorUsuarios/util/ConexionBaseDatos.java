package com.xjgv.mantenedorUsuarios.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBaseDatos {

    private static String url = "jdbc:mysql://localhost:3306/mantenedor_usuarios?serverTimezone=America/Santiago";
    private static String username = "root";
    private static String password = "admin";

    private static Connection conexion;

    public static Connection obtenerInstancia() throws SQLException {
        if(conexion == null){
            conexion = DriverManager.getConnection(url, username, password);
        }
        return conexion;
    }
}
