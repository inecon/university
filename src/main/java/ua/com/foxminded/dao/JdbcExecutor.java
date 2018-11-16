package ua.com.foxminded.dao;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcExecutor<T> {
    private ConnectionFactory connectionFactory;
    private static final Logger log = Logger.getLogger(GroupDaoImpl.class);

    public JdbcExecutor(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void execUpdate(String update, Object... parameters) throws Exception {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(update);) {
            int count = 1;
            for (Object parameterValue : parameters) {
                statement.setObject(count++, parameterValue);
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error("Exception in execUpdate", e.getCause());
            throw new DaoException(e);
        }
    }

    public <T> T execQuery(String query, ResultHandler<T> handler, Object... parameters) throws Exception {
        T value = null;
        ResultSet result = null;
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);) {
            int count = 1;
            for (Object parameterValue : parameters) {
                statement.setObject(count++, parameterValue);
            }
            statement.executeQuery();
            result = statement.getResultSet();
            value = handler.handle(result);

        } catch (Exception e) {
            log.error("Exception in execQuery when query = " + query, e.getCause());
            throw new DaoException(e);
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
            } catch (Exception e){
                log.error("resultSet not closing correctly = ", e.getCause());
                throw new DaoException(e);
            }
        }
        return value;
    }
}