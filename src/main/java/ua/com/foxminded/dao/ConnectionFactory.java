package ua.com.foxminded.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    /****
     * This will be loaded later.....with Spring :)
     * please accept this implementation for now
     */
    private String user = "postgres";
    private String password = "postgres";
    private String url = "jdbc:postgresql://localhost:5433/test";
    private String driver = "com.postgresql.jdbc.Driver";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public void MySqlDaoFactory() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

