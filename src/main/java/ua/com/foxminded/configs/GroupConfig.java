package ua.com.foxminded.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ua.com.foxminded.dao.GroupDaoImpl;
import ua.com.foxminded.dao.JdbcExecutor;
import ua.com.foxminded.domain.Group;

@Configuration
@ComponentScan("ua.com.foxminded")
public class GroupConfig {

    @Bean("group")
    public JdbcExecutor<Group> jdbcExecutor(){
        return new JdbcExecutor<>();
    }

    @Bean
    public GroupDaoImpl groupDao() {
        return new GroupDaoImpl();
    }
}
