package com.ng.chavespix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan({"com.ng.chavespix"})
@EnableJpaRepositories
@SpringBootApplication
public class ChavesPixApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChavesPixApplication.class, args);
	}

}
