package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {

    public static Connection getConnection() {
        String url = "jdbc:postgresql://localhost:5432/property_purchase_system";
        String username = "mariaxadina";
        String password = "mariaadina#22";

        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
