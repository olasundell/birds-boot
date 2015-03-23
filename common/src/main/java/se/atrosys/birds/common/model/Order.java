package se.atrosys.birds.common.model;

import java.util.ArrayList;
import java.util.List;

public class Order implements Model {
	private final String name;
	private final List<Family> families;

	public Order(String name) {
		this.name = name;
		families = new ArrayList<>();
	}

	public void addFamily(Family family) {
		families.add(family);
	}

	public String getName() {
		return name;
	}
}
