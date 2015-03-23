package se.atrosys.service.common.response;

import se.atrosys.birds.common.model.Model;
import se.atrosys.service.common.service.ProviderLookupService;
import se.atrosys.service.common.service.ServiceProvider;

import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractResponse {
	protected final List<Model> models = new ArrayList<>();
	protected final ProviderLookupService providerLookupService;

	public AbstractResponse(ProviderLookupService lookupService) {
		this.providerLookupService = lookupService;
	}

	public Map<String, Link> getLinks() {
		Set<Class> classes = models.stream().map(Model::getClass).collect(Collectors.toSet());

		List<Link> list = classes.stream()
				.map(this::generateLinkFromClass)
				.collect(Collectors.toList());

		HashMap<String, Link> map = new HashMap<>();
		for (Link l: list) {
			map.put(getLinkPrefix() + "." + l.getType(), l);
		}
		return map;
	}

	protected abstract String getLinkPrefix();

	private String linkNameFromClass(Class c) {
		return "";
	}

	private Link generateLinkFromClass(Class c) {
		ServiceProvider p = ServiceProvider.getProvider(c);
		return Link.builder()
				.withHref(providerLookupService.getHref(p))
				.withType(p.name().toLowerCase())
				.build();
	}

	public void addModels(Collection<? extends Model> models) {
		this.models.addAll(models);
	}
}