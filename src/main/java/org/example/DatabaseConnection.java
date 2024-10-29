package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public Connection connect() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/kirjakauppa_db";
            String username = ""; // käyttäjätunnus tähän
            String password = ""; // salasana tähän
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Yhteys muodostettu");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return connection;
        }
    }
