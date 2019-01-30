package ua.com.foxminded.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import ua.com.foxminded.dao.*;

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
    public JdbcExecutor<?> jdbcExecutor(){
        return new JdbcExecutor<>();
    }

    @Bean
    public GroupDaoImpl groupDao() {
        return new GroupDaoImpl();
    }

    @Bean
    public LectureDaoImpl lectureDao() {
        return new LectureDaoImpl();
    }

    @Bean
    public StudentDaoImpl studentDao() {
        return new StudentDaoImpl();
    }

    @Bean
    public TeacherDaoImpl teacherDao() {
        return new TeacherDaoImpl();
    }
}
