package ua.com.foxminded.configs;

import lombok.extern.log4j.Log4j;
import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import ua.com.foxminded.dao.*;
import ua.com.foxminded.domain.Group;
import ua.com.foxminded.domain.Lecture;
import ua.com.foxminded.domain.Student;
import ua.com.foxminded.domain.Teacher;

import javax.sql.DataSource;

@Configuration
@ComponentScan("ua.com.foxminded")
@Import(HibernateConfig.class)
@PropertySource(value = "classpath:app.properties")
@Log4j
public class SpringConfig {

    @Value("${driverClassName}")
    private String driverClassName;
    @Value("${url}")
    private String url;
    @Value("${usernameValue}")
    private String username;
    @Value("${password}")
    private String password;

    @Bean
    public DataSource dataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(driverClassName);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        return ds;
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        return new ConnectionFactory(dataSource());
    }

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
    public StudentDaoImpl studentDao() {
        return new StudentDaoImpl();
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
}
