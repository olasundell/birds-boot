package se.atrosys.birds.common.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.*;
import java.util.stream.Collectors;

@JsonDeserialize(builder=Family.Builder.class)
public class Family implements Model {
	private final List<String> birdNames = new ArrayList<>();
	private final String name;
	private final Order order;

	private Family(Builder builder) {
		this.birdNames.addAll(builder.birdNames);
		this.order = builder.order;
		this.name = builder.name;
	}

	public static Builder builder() {
		return new Builder();
	}

	public String getName() {
		return name;
	}

	public List<String> getBirdNames() {
		return Collections.unmodifiableList(birdNames);
	}

	public void addBird(Bird bird) {
		birdNames.add(bird.getName());
	}

	public static final class Builder {
		private Order order;
		private final List<String> birdNames;
		private String name;

		private Builder() {
			birdNames = new ArrayList<>();
		}

		public Builder withOrder(Order order) {
			this.order = order;
			return this;
		}

		public Builder withName(String name) {
			this.name = name;
			return this;
		}

/*
		public Builder withBirds(List<Bird> birds) {
			this.birdNames.addAll(birds.stream().map(Bird::getName).collect(Collectors.toList()));
			return this;
		}
*/

		public Builder withBirdNames(List<String> birdNames) {
			this.birdNames.addAll(birdNames);

			return this;
		}

		public Family build() {
			return new Family(this);
		}
	}
}
