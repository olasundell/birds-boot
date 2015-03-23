package se.atrosys.birds.common.model;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Family implements Model {
	private final Map<Locale, String> names = new HashMap<>();
	private final List<Bird> birds = new ArrayList<>();
	private Order order;

	public String getName() {
		return names.get(Bird.LATIN);
	}

	public void addName(Locale locale, String name) {
		names.put(locale, name);
	}

	public List<String> getBirdNames() {
		return birds.stream().map(Bird::getName).collect(Collectors.toList());
	}

	public void addBird(Bird bird) {
		birds.add(bird);
	}

	public void setOrder(Order order) {
		this.order = order;
	}
}
