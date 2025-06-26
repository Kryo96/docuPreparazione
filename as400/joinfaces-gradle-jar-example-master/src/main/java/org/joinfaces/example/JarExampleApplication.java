package org.joinfaces.example;

import org.jboss.weld.environment.servlet.Listener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;

/**
 * @author Lars Grefer
 */
@SpringBootApplication
@ServletComponentScan
public class JarExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(JarExampleApplication.class, args);
    }

    @Bean
    public ServletContextInitializer servletContextInitializer() {
        return servletContext -> {
            // Initialize Weld for CDI
            servletContext.addListener(Listener.class);
        };
    }

}
