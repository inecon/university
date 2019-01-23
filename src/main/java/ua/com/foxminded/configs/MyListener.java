package ua.com.foxminded.configs;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent context) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
        context.getServletContext().setAttribute("applicationContext",ctx);
    }

    @Override

    public void contextDestroyed(ServletContextEvent arg0) {

        System.out.println("ServletContextListener destroyed");

    }
}
