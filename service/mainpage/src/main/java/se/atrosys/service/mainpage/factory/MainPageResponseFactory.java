package se.atrosys.service.mainpage.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import se.atrosys.birds.common.model.Bird;
import se.atrosys.birds.common.model.Family;
import se.atrosys.birds.common.model.Image;
import se.atrosys.service.common.response.BirdResponse;
import se.atrosys.service.common.response.FamilyResponse;
import se.atrosys.service.common.response.ImageResponse;
import se.atrosys.service.mainpage.model.MainPage;
import se.atrosys.service.mainpage.response.MainPageResponse;

import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class MainPageResponseFactory {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RestTemplate restTemplate;

	public MainPageResponse createResponse(int randseed) {
//		Random random = new Random(randseed);
//		List<Bird> birds = aves.getBirds();
//		Bird bird = birds.get(random.nextInt(birds.size()));


		logger.info("Getting random bird");
		BirdResponse birdResponse = restTemplate.getForObject("http://info/randombird/", BirdResponse.class);

		// TODO null and range checking goes here.
		Bird bird = birdResponse.getBirds().get(0);
		logger.info("Got {}", bird.getName());

		logger.info("Getting image");
		ImageResponse imageResponse = restTemplate.getForObject("http://image/image/{id}", ImageResponse.class, bird.getName());

		// TODO null and range checking goes here.
		final Image image;
		if (imageResponse.getImages().isEmpty()) {
			image = null;
		} else {
			image = imageResponse.getImages().get(0);
			logger.info("Got image {}", image.getUrl());
		}

		FamilyResponse familyResponse = restTemplate.getForObject("http://info/family/{id}",
				FamilyResponse.class,
				bird.getFamily());

		logger.info("Got alternatives for family {}", familyResponse.getFamilies().stream().map(Family::getName).collect(Collectors.joining(",")));

		logger.info("Number of birds in family {}: {}",
				familyResponse.getFamilies().get(0).getName(),
				familyResponse.getFamilies().get(0).getBirdNames().size());

		logger.info("Bird names {}",familyResponse.getFamilies().get(0).getBirdNames().stream().collect(Collectors.joining(",")));

		final String birdIds = familyResponse.getFamilies().stream()
				.flatMap(f -> f.getBirdNames().stream())
				.limit(5)
				.collect(Collectors.joining(","));

		logger.info("Getting info for alternatives {}", birdIds);

		BirdResponse alternativeBirds = restTemplate.getForObject("http://info/bird/{names}", BirdResponse.class, birdIds);

		final MainPage mainPage = MainPage.builder()
				.withBird(bird)
				.withAlternatives(alternativeBirds.getBirds())
				.withBinary(image)
				.build();

		return MainPageResponse.builder()
				.withMainPages(Collections.singletonList(mainPage))
				.build();
	}
}
