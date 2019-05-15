package ua.com.foxminded.configs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import ua.com.foxminded.domain.*;

@SpringBootApplication()
@PropertySource(value = "classpath:application.properties")
@Slf4j
public class SpringConfig {
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