package se.atrosys.service.mainpage.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import se.atrosys.birds.common.model.Aves;
import se.atrosys.birds.common.model.Bird;
import se.atrosys.birds.common.model.Image;
import se.atrosys.service.common.response.AbstractResponse;
import se.atrosys.service.common.response.BirdResponse;
import se.atrosys.service.mainpage.response.MainPageResponse;

import java.util.List;
import java.util.Random;

@Component
public class MainPageResponseFactory {
	@Autowired
	private LoadBalancerClient loadBalancer;
	@Autowired
	private RestTemplate restTemplate;

	public MainPageResponse createResponse(int randseed) {
//		Random random = new Random(randseed);
//		List<Bird> birds = aves.getBirds();
//		Bird bird = birds.get(random.nextInt(birds.size()));

		BirdResponse birdResponse = restTemplate.getForObject("http://info/randombird/", BirdResponse.class);
		Bird bird = birdResponse.getBirds().get(0);
		Image image = restTemplate.getForObject("http://IMAGE/{id}", Image.class, bird.getName());

//		template.getForEntity("http://localhost:9990/randombird/", );

		return new MainPageResponse.Builder()
				.bird(bird)
				.binary(image)
				.build();
	}
}
