package org.example.temporary_task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "org.example.temporary_task")
@EntityScan(basePackages = "org.example.temporary_task")
@EnableJpaRepositories(basePackages = "org.example.temporary_task")
@EnableJpaAuditing
public class TemporaryTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(TemporaryTaskApplication.class, args);
	}

}
