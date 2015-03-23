package se.atrosys.service.common.service;

import se.atrosys.birds.common.model.*;

import java.util.*;

public enum ServiceProvider {
	IMAGE(Image.class),
	INFO(Aves.class, Order.class, Family.class, Bird.class),
	SOUND(Sound.class),
	MAINPAGE,
	BINARY_OK,
	NONE;

	Set<Class<? extends Model>> classes = new HashSet<>();

	@SafeVarargs
	ServiceProvider(Class<? extends Model>... classes) {
		this.classes.addAll(Arrays.asList(classes));
	}

	public static ServiceProvider getProvider(Class c) {
		for (ServiceProvider p: ServiceProvider.values()) {
			if (p.getClasses().contains(c)) {
				return p;
			}
		}

		return NONE;
	}

	protected Set<Class<? extends Model>> getClasses() {
		return classes;
	}
}
