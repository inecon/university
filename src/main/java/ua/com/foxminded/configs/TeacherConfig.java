package ua.com.foxminded.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ua.com.foxminded.dao.JdbcExecutor;
import ua.com.foxminded.dao.TeacherDaoImpl;
import ua.com.foxminded.domain.Teacher;

@Configuration
@ComponentScan("ua.com.foxminded")
public class TeacherConfig {
    @Bean("teacher")
    public JdbcExecutor<Teacher> jdbcExecutor(){
        return new JdbcExecutor<>();
    }

    @Bean
    public TeacherDaoImpl teacherDao() {
        return new TeacherDaoImpl();
    }
}
