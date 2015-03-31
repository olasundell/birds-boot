package se.atrosys.service.image;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan("se.atrosys")
@EnableEurekaClient
public class BirdImageApplication {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(BirdImageApplication.class, args);
	}
}
