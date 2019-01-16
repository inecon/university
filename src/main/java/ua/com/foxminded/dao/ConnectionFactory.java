package ua.com.foxminded.dao;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.com.foxminded.configs.SpringConfig;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final Logger log = Logger.getLogger(ConnectionFactory.class);
    ApplicationContext context =
            new AnnotationConfigApplicationContext(SpringConfig.class);

    public DataSource dataSource = (DataSource) context.getBean("dataSource");

    public Connection getDataSourceConnection() {
        try {
            if (context == null) {
                log.error("No context");
                throw new DaoException("Uh oh -- no context!");
            }

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