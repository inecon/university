package ua.com.foxminded.dao;

import org.apache.log4j.Logger;

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
    private String driver = "org.postgresql.Driver";
    private static final Logger log = Logger.getLogger(ConnectionFactory.class);

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        log.debug("Connection returns with URL = " + url + ", USER = " + user);
        Class.forName(driver);
        return DriverManager.getConnection(url, user, password);
    }
}

