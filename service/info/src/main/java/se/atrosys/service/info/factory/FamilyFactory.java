package se.atrosys.service.info.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.atrosys.service.common.service.ProviderLookupService;
import se.atrosys.service.info.response.FamilyResponse;

import java.util.List;

@Component
public class FamilyFactory {
	@Autowired
	ProviderLookupService providerLookupService;

	public FamilyResponse createResponse(List<String> strings) {
		return new FamilyResponse(providerLookupService);
	}

	public FamilyResponse createFamilyResponse(int limit) {
		return new FamilyResponse(providerLookupService);
	}
}
