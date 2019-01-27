package ua.com.foxminded.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ua.com.foxminded.dao.ConnectionFactory;
import ua.com.foxminded.dao.JdbcExecutor;
import ua.com.foxminded.dao.StudentDaoImpl;
import ua.com.foxminded.domain.Student;

import javax.inject.Inject;

@Configuration
@ComponentScan("ua.com.foxminded")
public class StudentConfig {
    @Inject
    ConnectionFactory connectionFactory;

    @Bean("student")
    public JdbcExecutor<Student> jdbcExecutor(){
        return new JdbcExecutor<>(connectionFactory);
    }

    @Bean
    public StudentDaoImpl studentDao() {
        return new StudentDaoImpl();
    }
}
