package se.atrosys.service.info.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.atrosys.birds.common.formatter.BirdNameFormatter;
import se.atrosys.birds.common.model.Bird;
import se.atrosys.service.info.factory.BirdResponseFactory;
import se.atrosys.service.info.response.BirdResponse;

@RestController
public class BirdResource {
	@Autowired
	private BirdResponseFactory birdResponseFactory;

	@RequestMapping("/bird/{names}")
	public BirdResponse bird(@PathVariable("names") String names) {
		return birdResponseFactory.createResponse(new BirdNameFormatter().formatNames(names));
	}

	@RequestMapping("/randombird/")
	public Bird randombird(@RequestParam(value = "randseed", required = false, defaultValue = "0") int randseed) {
		return birdResponseFactory.createResponseForRandomBird(randseed);
	}

//	@RequestMapping("/allbirds")
//	public BirdResponse allBirds() {
//
//	}
}
