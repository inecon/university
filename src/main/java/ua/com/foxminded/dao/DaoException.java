package ua.com.foxminded.dao;

import java.sql.SQLException;

public class DaoException extends RuntimeException {
    public DaoException(RuntimeException e) throws RuntimeException {
        throw new RuntimeException(e);
    }

    public DaoException(SQLException e) throws SQLException {
        throw new SQLException(e);
    }

    /*public DaoException() {

    }*/
}
