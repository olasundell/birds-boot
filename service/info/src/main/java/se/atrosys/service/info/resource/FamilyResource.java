package se.atrosys.service.info.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.atrosys.birds.common.formatter.BirdNameFormatter;
import se.atrosys.service.info.factory.FamilyResponseFactory;
import se.atrosys.service.common.response.FamilyResponse;

import java.util.concurrent.Callable;

@RestController
public class FamilyResource {
	@Autowired
	FamilyResponseFactory familyResponseFactory;

	@RequestMapping("/family/{names}")
	public Callable<FamilyResponse> family(@PathVariable("names") String names) {
		return () -> familyResponseFactory.createResponse(new BirdNameFormatter().formatNames(names)).toBlocking().single();
	}
}
