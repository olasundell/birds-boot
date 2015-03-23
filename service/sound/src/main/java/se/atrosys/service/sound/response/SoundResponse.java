package se.atrosys.service.sound.response;

import se.atrosys.birds.common.model.Model;
import se.atrosys.service.common.response.AbstractResponse;
import se.atrosys.service.common.service.ProviderLookupService;

import java.util.Collections;
import java.util.List;

public class SoundResponse extends AbstractResponse {
	public SoundResponse(ProviderLookupService lookupService) {
		super(lookupService);
	}

	public List<Model> getSounds() {
		return Collections.unmodifiableList(models);
	}

	@Override
	protected String getLinkPrefix() {
		return "sound";
	}
}
