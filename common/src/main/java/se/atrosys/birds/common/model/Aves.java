package se.atrosys.birds.common.model;

import java.util.*;
import java.util.stream.Collectors;

public class Aves implements Model {
	private final Map<String, Order> orders = new HashMap<>();
	private final Map<String, Family> families = new HashMap<>();
	private final Map<String, Bird> birds = new HashMap<>();

	public void addOrders(Map<String, Order> orders) {
		this.orders.putAll(orders);
	}

	public void addFamilies(Map<String, Family> families) {
		this.families.putAll(families);
	}

	public void addBirds(Map<String, Bird> birds) {
		this.birds.putAll(birds);
	}

	public Collection<Order> getOrders() {
		return Collections.unmodifiableCollection(orders.values());
	}

	public List<Family> getFamilies() {
		return Collections.unmodifiableList(new ArrayList<>(families.values()));
	}

	public List<Bird> getBirds() {
		return birds.values().stream().collect(Collectors.toList());
	}

	public Family getFamily(String name) {
		return families.get(name);
	}

	public Bird getBird(String latinName) {
		return birds.get(latinName);
	}
}
