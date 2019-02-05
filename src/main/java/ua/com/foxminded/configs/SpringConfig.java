package ua.com.foxminded.configs;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import ua.com.foxminded.dao.*;
import ua.com.foxminded.domain.Student;

import javax.sql.DataSource;

@Configuration
@ComponentScan("ua.com.foxminded")
@Import(HibernateConfig.class)
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

    @Bean
    public Student student(){
        return new Student();
    }

    @Bean
    public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
        PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
        ppc.setLocation(new ClassPathResource("app.properties"));
        ppc.setIgnoreUnresolvablePlaceholders(true);
        return ppc;
    }

}
