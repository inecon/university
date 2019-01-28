package ua.com.foxminded.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ua.com.foxminded.dao.JdbcExecutor;
import ua.com.foxminded.dao.LectureDaoImpl;
import ua.com.foxminded.domain.Lecture;

@Configuration
@ComponentScan("ua.com.foxminded")
public class LectureConfig {

    @Bean("lecture")
    public JdbcExecutor<Lecture> jdbcExecutor() {
        return new JdbcExecutor<>();
    }

    @Bean
    public LectureDaoImpl lectureDao() {
        return new LectureDaoImpl();
    }
}
