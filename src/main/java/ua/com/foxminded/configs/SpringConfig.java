package ua.com.foxminded.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import ua.com.foxminded.dao.ConnectionFactory;
import ua.com.foxminded.dao.JdbcExecutor;
import ua.com.foxminded.dao.StudentDaoImpl;
import ua.com.foxminded.domain.Student;

import javax.sql.DataSource;

@Configuration
@ComponentScan("ua.com.foxminded")
public class SpringConfig {
    @Bean
    public DataSource dataSource() {
        return new JndiDataSourceLookup().getDataSource("java:comp/env/jdbc/postgres");
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        return new ConnectionFactory(dataSource());
    }

    @Bean
    public StudentDaoImpl studentDao() {
        return new StudentDaoImpl();
    }

    @Bean
    public JdbcExecutor<Student> jdbcExecutor(){
        return new JdbcExecutor<>(connectionFactory());
    }

}
