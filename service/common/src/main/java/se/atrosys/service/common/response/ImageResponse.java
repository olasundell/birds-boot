package se.atrosys.service.common.response;

import se.atrosys.birds.common.model.Image;
import se.atrosys.service.common.service.ProviderLookupService;

import java.util.Collections;
import java.util.List;

public class ImageResponse extends AbstractResponse<Image> {
	public ImageResponse(ProviderLookupService lookupService) {
		super(lookupService);
	}

	public List<Image> getImages() {
		return Collections.unmodifiableList(models);
	}
	@Override
	protected String getLinkPrefix() {
		return "image";
	}
}
