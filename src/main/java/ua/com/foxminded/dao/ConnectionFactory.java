package ua.com.foxminded.dao;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
@NoArgsConstructor
@Log4j
public class ConnectionFactory {

    @Setter
    public DataSource dataSource;

    public ConnectionFactory(DataSource dataSource) {
        setDataSource(dataSource);
    }

    public Connection getDataSourceConnection() {
        try {
            if (dataSource == null) {
                log.error("No DataSource");
                throw new DaoException("Data source not found!");
            }
            return dataSource.getConnection();
        } catch (SQLException | DaoException e) {
            log.error("Exception during receiving dataSource", e.getCause());
            throw new DaoException(e);
        }
    }
}