package com.Demeshkan.Kyrsach;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@PropertySource("classpath:/application.properties")
@SpringBootApplication
@EnableJpaAuditing
public class KyrsachApplication {

	public static void main(String[] args) {
		SpringApplication.run(KyrsachApplication.class, args);

	}

}
