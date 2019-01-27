package ua.com.foxminded.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ua.com.foxminded.dao.ConnectionFactory;
import ua.com.foxminded.dao.JdbcExecutor;
import ua.com.foxminded.dao.TeacherDaoImpl;
import ua.com.foxminded.domain.Teacher;

import javax.inject.Inject;

@Configuration
@ComponentScan("ua.com.foxminded")
public class TeacherConfig {
    @Inject
    ConnectionFactory connectionFactory;

    @Bean("teacher")
    public JdbcExecutor<Teacher> jdbcExecutor(){
        return new JdbcExecutor<>(connectionFactory);
    }

    @Bean
    public TeacherDaoImpl teacherDao() {
        return new TeacherDaoImpl();
    }
}
