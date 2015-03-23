package se.atrosys.service.info.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.atrosys.birds.common.formatter.BirdNameFormatter;
import se.atrosys.birds.common.model.Family;
import se.atrosys.service.info.factory.FamilyFactory;
import se.atrosys.service.info.response.FamilyResponse;

@RestController
public class FamilyResource {
	@Autowired
	FamilyFactory familyFactory;

	@RequestMapping("/family/{names}")
	public FamilyResponse family(@PathVariable("names") String names) {
		return familyFactory.createResponse(new BirdNameFormatter().formatNames(names));
	}

	@RequestMapping("/families")
	public FamilyResponse families() {
		return familyFactory.createFamilyResponse(10);
	}
}
