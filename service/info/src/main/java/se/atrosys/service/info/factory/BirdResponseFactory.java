package se.atrosys.service.info.factory;

import org.springframework.stereotype.Component;
import se.atrosys.birds.common.model.Bird;
import se.atrosys.service.common.factory.AbstractResponseFactory;
import se.atrosys.service.common.response.BirdResponse;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Component
public class BirdResponseFactory extends AbstractResponseFactory {
	public BirdResponse createResponse(List<String> ids) {
		List<Bird> birds = ids.stream().map(aves::getBird).filter(b -> b != null).collect(Collectors.toList());
		return BirdResponse.builder().withBirds(birds).build();
	}

	public BirdResponse createResponseForRandomBird(int randseed) {
		ThreadLocalRandom random = ThreadLocalRandom.current();

//		random.setSeed(randseed);
		Bird bird = aves.getBirds().get(random.nextInt(aves.getBirds().size()));

		return createResponse(Collections.singletonList(bird.getName()));
//		return bird;
	}
}
