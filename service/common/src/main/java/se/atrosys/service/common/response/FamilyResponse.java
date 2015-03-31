package se.atrosys.service.common.response;

import se.atrosys.birds.common.model.Model;
import se.atrosys.service.common.response.AbstractResponse;
import se.atrosys.service.common.service.ProviderLookupService;

import java.util.Collections;
import java.util.List;

public class FamilyResponse extends AbstractResponse {
	public FamilyResponse(ProviderLookupService lookupService) {
		super(lookupService);
	}

	public List<Model> getFamilies() {
		return Collections.unmodifiableList(models);
	}

	@Override
	protected String getLinkPrefix() {
		return "info";
	}
}
