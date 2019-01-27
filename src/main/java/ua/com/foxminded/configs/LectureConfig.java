package ua.com.foxminded.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ua.com.foxminded.dao.*;
import ua.com.foxminded.domain.Lecture;

import javax.inject.Inject;

@Configuration
@ComponentScan("ua.com.foxminded")
public class LectureConfig {
    @Inject
    ConnectionFactory connectionFactory;

    @Inject
    GroupDao groupDao;

    @Inject
    TeacherDao teacherDao;

    @Bean("lecture")
    public JdbcExecutor<Lecture> jdbcExecutor(){
        return new JdbcExecutor<>(connectionFactory);
    }

    @Bean
    public LectureDaoImpl lectureDao() {
        return new LectureDaoImpl(groupDao,teacherDao);
    }
}
