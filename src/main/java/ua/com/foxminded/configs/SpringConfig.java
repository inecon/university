package ua.com.foxminded.configs;

import lombok.extern.log4j.Log4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import ua.com.foxminded.domain.*;
import ua.com.foxminded.repository.GroupDaoImpl;
import ua.com.foxminded.repository.JdbcExecutor;
import ua.com.foxminded.repository.LectureDaoImpl;
import ua.com.foxminded.repository.TeacherDaoImpl;

@SpringBootApplication()
@PropertySource(value = "classpath:application.properties")
@Log4j

public class SpringConfig {
    @Bean
    public JdbcExecutor<?> jdbcExecutor() {
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
    public TeacherDaoImpl teacherDao() {
        return new TeacherDaoImpl();
    }

    @Bean
    public Student student() {
        return new Student();
    }

    @Bean
    public Teacher teacher() {
        return new Teacher();
    }

    @Bean
    public Lecture lecture() {
        return new Lecture();
    }

    @Bean
    public Group group() {
        return new Group();
    }

    @Bean
    public Schedule schedule() {
        return new Schedule();
    }
}
