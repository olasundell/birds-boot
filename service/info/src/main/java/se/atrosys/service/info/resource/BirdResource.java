package se.atrosys.service.info.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.atrosys.birds.common.formatter.BirdNameFormatter;
import se.atrosys.service.info.factory.BirdResponseFactory;
import se.atrosys.service.common.response.BirdResponse;

import java.util.concurrent.Callable;

@RestController
public class BirdResource {
	@Autowired
	private BirdResponseFactory birdResponseFactory;

	@RequestMapping("/bird/{names}")
	public Callable<BirdResponse> bird(@PathVariable("names") String names) {
		return () -> birdResponseFactory.createResponse(new BirdNameFormatter().formatNames(names)).toBlocking().single();
	}

	@RequestMapping("/randombird/")
	public Callable<BirdResponse> randombird(@RequestParam(value = "randseed", required = false, defaultValue = "0") int randseed) {
		return () -> birdResponseFactory.createResponseForRandomBird(randseed).toBlocking().single();
	}

//	@RequestMapping("/allbirds")
//	public BirdResponse allBirds() {
//
//	}
}
