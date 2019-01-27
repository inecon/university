package ua.com.foxminded.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ua.com.foxminded.dao.ConnectionFactory;
import ua.com.foxminded.dao.GroupDaoImpl;
import ua.com.foxminded.dao.JdbcExecutor;
import ua.com.foxminded.domain.Group;

import javax.inject.Inject;

@Configuration
@ComponentScan("ua.com.foxminded")
public class GroupConfig {
    @Inject
    ConnectionFactory connectionFactory;

    @Bean("group")
    public JdbcExecutor<Group> jdbcExecutor(){
        return new JdbcExecutor<>(connectionFactory);
    }

    @Bean
    public GroupDaoImpl groupDao() {
        return new GroupDaoImpl();
    }
}
