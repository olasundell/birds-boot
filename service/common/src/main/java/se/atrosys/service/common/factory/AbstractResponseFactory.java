package se.atrosys.service.common.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.atrosys.birds.common.model.Aves;
import se.atrosys.service.common.service.ProviderLookupService;

@Component
public abstract class AbstractResponseFactory {
	@Autowired
	protected Aves aves;

	@Autowired
	protected ProviderLookupService providerLookupService;
}
