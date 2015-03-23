package se.atrosys.service.mainpage.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import se.atrosys.birds.common.model.Aves;
import se.atrosys.birds.common.model.Bird;
import se.atrosys.service.mainpage.response.MainPageResponse;

import java.util.List;
import java.util.Random;

@Component
public class MainPageResponseFactory {
	public MainPageResponse createResponse(int randseed) {
//		Random random = new Random(randseed);
//		List<Bird> birds = aves.getBirds();
//		Bird bird = birds.get(random.nextInt(birds.size()));

		RestTemplate template = new RestTemplate();

//		template.getForEntity("http://localhost:9990/randombird/", );

		return new MainPageResponse();
	}
}
