package com.example.sproject.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
    private static Connection conn = null;
    private final static String URL = "jdbc:postgresql://localhost:5432/postgres";
    private final static String USERNAME = "postgres";
    private final static String PASSWORD = "postgres";

    public static Connection getConn() {
        if (conn == null) {
            try {
                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (SQLException | ClassNotFoundException e) {
                System.out.println("Проблемс:" + e);
            }
        }
        return conn;
    }
}
