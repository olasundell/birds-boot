package se.atrosys.service.common.response;

import se.atrosys.birds.common.model.Model;
import se.atrosys.birds.common.model.Sound;
import se.atrosys.service.common.response.AbstractResponse;
import se.atrosys.service.common.service.ProviderLookupService;

import java.util.Collections;
import java.util.List;

public class SoundResponse extends AbstractResponse<Sound> {
	public SoundResponse(ProviderLookupService lookupService) {
		super(lookupService);
	}

	public List<Sound> getSounds() {
		return Collections.unmodifiableList(models);
	}

	@Override
	protected String getLinkPrefix() {
		return "sound";
	}
}
