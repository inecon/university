package ua.com.foxminded.dao;

import org.apache.log4j.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final Logger log = Logger.getLogger(ConnectionFactory.class);

    public Connection getDataSourceConnection() {
        try {
            InitialContext cxt = new InitialContext();
            if (cxt == null) {
                log.error("No context");
                throw new DaoException("Uh oh -- no context!");
            }
            DataSource ds = (DataSource) cxt.lookup("java:comp/env/jdbc/postgres");
            if (ds == null) {
                log.error("No DataSource");
                throw new DaoException("Data source not found!");
            }
            return ds.getConnection();
        } catch (SQLException | DaoException | NamingException e) {
            log.error("Exception during receiving dataSource", e.getCause());
            throw new DaoException(e);
        }
    }
}

