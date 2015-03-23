package se.atrosys.service.info;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan("se.atrosys")
public class InfoApplication {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(InfoApplication.class, args);
	}
}
