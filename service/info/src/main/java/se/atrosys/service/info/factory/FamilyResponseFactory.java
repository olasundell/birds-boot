package se.atrosys.service.info.factory;

import org.springframework.stereotype.Component;
import se.atrosys.service.common.factory.AbstractResponseFactory;
import se.atrosys.service.common.response.FamilyResponse;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FamilyResponseFactory extends AbstractResponseFactory {

	public FamilyResponse createResponse(List<String> strings) {

		return FamilyResponse.builder()
				.withFamilies(strings.stream().map(aves::getFamily).collect(Collectors.toList()))
				.build();
	}

	public FamilyResponse createFamilyResponse(int limit) {
		return FamilyResponse.builder().build();
	}
}
