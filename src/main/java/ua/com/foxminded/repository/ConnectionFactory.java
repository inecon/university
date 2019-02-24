package ua.com.foxminded.repository;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Component;
import ua.com.foxminded.exceptions.MyException;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
@NoArgsConstructor
@Log4j
public class ConnectionFactory {

    @Inject
    public DataSource dataSource;

    public Connection getDataSourceConnection() {
        try {
            if (dataSource == null) {
                log.error("No DataSource");
                throw new MyException("Data source not found!");
            }
            return dataSource.getConnection();
        } catch (SQLException | MyException e) {
            log.error("Exception during receiving dataSource", e.getCause());
            throw new MyException(e);
        }
    }
}