package ua.com.foxminded.dao;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Executor<T> {
    private ConnectionFactory connectionFactory;
    private static final Logger log = Logger.getLogger(GroupDaoImpl.class);

    public Executor(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void execUpdate(String update, Object... parameters) throws SQLException {
        Connection connection = connectionFactory.getConnection();
        PreparedStatement statement = connection.prepareStatement(update);
        try {
            int count = 1;
            for (Object parameterValue : parameters) {
                statement.setObject(count++, parameterValue);
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public <T> T execQuery(String query, ResultHandler<T> handler, Object... parameters) throws SQLException {
        Connection connection = connectionFactory.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        T value = null;
        ResultSet result = null;
        try {
            int count = 1;
            for (Object parameterValue : parameters) {
                statement.setObject(count++, parameterValue);
            }
            statement.executeQuery();
            result = statement.getResultSet();
            value = handler.handle(result);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return value;
    }
}