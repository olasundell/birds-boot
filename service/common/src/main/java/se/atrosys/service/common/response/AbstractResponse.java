package se.atrosys.service.common.response;

import se.atrosys.birds.common.model.Model;
import se.atrosys.service.common.service.ServiceProvider;

import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractResponse<T extends Model> {
	protected final List<T> models = new ArrayList<>();

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
				.withHref(p.name().toLowerCase())
				.withType(p.name().toLowerCase())
				.build();
	}

	public void addModels(Collection<T> models) {
		this.models.addAll(models);
	}
}
