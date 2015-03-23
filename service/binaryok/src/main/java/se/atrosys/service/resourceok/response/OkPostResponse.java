package se.atrosys.service.resourceok.response;

import se.atrosys.service.common.response.AbstractResponse;
import se.atrosys.service.common.service.ProviderLookupService;

public class OkPostResponse extends AbstractResponse {
	public OkPostResponse(ProviderLookupService lookupService) {
		super(lookupService);
	}

	@Override
	protected String getLinkPrefix() {
		return null;
	}
}
