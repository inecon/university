package ua.com.foxminded.dao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class ConnectionFactory {
    private static final Logger log = Logger.getLogger(ConnectionFactory.class);
   /* ApplicationContext context =
            new AnnotationConfigApplicationContext(SpringConfig.class);

    public DataSource dataSource = (DataSource) context.getBean("dataSource");*/
   public DataSource dataSource;

   @Autowired
   @Qualifier("dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

   public ConnectionFactory(){}

   public ConnectionFactory(DataSource dataSource){
       setDataSource(dataSource);
   }

    public Connection getDataSourceConnection() {
        try {
            /*if (context == null) {
                log.error("No context");
                throw new DaoException("Context not found!");
            }*/
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