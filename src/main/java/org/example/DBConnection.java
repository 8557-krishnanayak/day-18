package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    final static String URL = "jdbc:postgresql://localhost:5432/Ecommerce";
    final static String USERNAME = "postgres";
    final static String PASSWORD = "root";
    static Connection instance = null;

    public static synchronized Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

// using singleton pattern : This does not work as instance ge close.

//        if (instance == null) {
//            instance = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//        }
//        return instance;
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }


}
