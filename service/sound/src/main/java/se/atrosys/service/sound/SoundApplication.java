package se.atrosys.service.sound;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan("se.atrosys")
@EnableEurekaClient
public class SoundApplication {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(SoundApplication.class, args);
	}
}
