package ua.com.foxminded.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Executor<T> {
    private ConnectionFactory connectionFactory;

    public Executor(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void execUpdate(String update, Object... parameters) throws SQLException {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(update)) {
            int count = 1;
            for (Object value : parameters) {
                statement.setObject(count++, value);
            }
            statement.executeUpdate();
        }
    }

    public <T> T execQuery(String query, ResultHandler<T> handler, Object... parameters) throws SQLException {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            int count = 1;
            for (Object value : parameters) {
                stmt.setObject(count++, value);
            }
            stmt.executeQuery();
            ResultSet result = stmt.getResultSet();
            T value = handler.handle(result);
            result.close();
            return value;
        }
    }
}