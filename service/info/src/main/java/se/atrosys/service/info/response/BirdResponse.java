package se.atrosys.service.info.response;

import se.atrosys.birds.common.model.Bird;
import se.atrosys.birds.common.model.Model;
import se.atrosys.service.common.response.AbstractResponse;
import se.atrosys.service.common.service.ProviderLookupService;

import java.util.*;

public class BirdResponse extends AbstractResponse {
	public BirdResponse(ProviderLookupService lookupService) {
		super(lookupService);
	}

	public List<Model> getBirds() {
		return Collections.unmodifiableList(models);
	}

	@Override
	protected String getLinkPrefix() {
		return "info";
	}
}
