package com.example.demo;

import org.jboss.weld.environment.servlet.Listener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public ServletContextInitializer servletContextInitializer() {
		return servletContext -> servletContext.addListener(Listener.class);
	}
}
