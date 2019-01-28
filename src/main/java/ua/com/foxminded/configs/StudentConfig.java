package ua.com.foxminded.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ua.com.foxminded.dao.JdbcExecutor;
import ua.com.foxminded.dao.StudentDaoImpl;
import ua.com.foxminded.domain.Student;

@Configuration
@ComponentScan("ua.com.foxminded")
public class StudentConfig {

    @Bean("student")
    public JdbcExecutor<Student> jdbcExecutor(){
        return new JdbcExecutor<>();
    }

    @Bean
    public StudentDaoImpl studentDao() {
        return new StudentDaoImpl();
    }
}
