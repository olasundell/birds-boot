package se.atrosys.service.common.response;

import se.atrosys.birds.common.model.Bird;
import se.atrosys.birds.common.model.Model;
import se.atrosys.service.common.response.AbstractResponse;
import se.atrosys.service.common.service.ProviderLookupService;

import java.util.*;

public class BirdResponse extends AbstractResponse<Bird> {
	public BirdResponse(ProviderLookupService lookupService) {
		super(lookupService);
	}

	public List<Bird> getBirds() {
		return Collections.unmodifiableList(models);
	}

	@Override
	protected String getLinkPrefix() {
		return "info";
	}
}
