package io.swagger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.web.support.SpringBootServletInitializer;



@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = { "io.swagger"})
@EnableJpaRepositories("io.swagger.repositories")

public class Swagger2SpringBoot extends SpringBootServletInitializer {
	
	private static final Class<Swagger2SpringBoot> appClass = Swagger2SpringBoot.class;    

    public static void main(String[] args) throws Exception {
        new SpringApplication(Swagger2SpringBoot.class).run(args);
    }
    
    @Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(appClass);
	}
}
