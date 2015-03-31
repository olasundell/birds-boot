package se.atrosys.service.info;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@EnableEurekaClient
@ComponentScan("se.atrosys")
public class InfoApplication {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(InfoApplication.class, args);
	}
}
