package ua.com.foxminded.repository;

import lombok.extern.log4j.Log4j;
import ua.com.foxminded.exceptions.MyException;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
@Log4j
public class JdbcExecutor<T> {
    @Inject
    private ConnectionFactory connectionFactory;

    public void execUpdate(String update, Object... parameters) throws MyException, SQLException {
        try (Connection connection = connectionFactory.getDataSourceConnection();
             PreparedStatement statement = connection.prepareStatement(update);) {
            int count = 1;
            for (Object parameterValue : parameters) {
                statement.setObject(count++, parameterValue);
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error("Exception in execUpdate", e.getCause());
            throw new MyException(e);
        }
    }

    public <T> T execQuery(String query, ResultHandler<T> handler, Object... parameters) throws MyException, SQLException {
        T value = null;
        ResultSet result = null;
        try (Connection connection = connectionFactory.getDataSourceConnection();
             PreparedStatement statement = connection.prepareStatement(query);) {
            int count = 1;
            for (Object parameterValue : parameters) {
                statement.setObject(count++, parameterValue);
            }
            statement.executeQuery();
            result = statement.getResultSet();
            value = handler.handle(result);
        } catch (MyException e) {
            log.error("Exception in execQuery when query = " + query, e.getCause());
            throw e;
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
            } catch (SQLException e) {
                log.error("resultSet not closing correctly = ", e.getCause());
                throw new MyException(e);
            }
        }
        return value;
    }
}