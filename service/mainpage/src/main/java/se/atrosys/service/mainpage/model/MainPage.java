package se.atrosys.service.mainpage.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import se.atrosys.birds.common.model.AbstractBinary;
import se.atrosys.birds.common.model.Bird;
import se.atrosys.birds.common.model.Model;

import java.util.ArrayList;
import java.util.List;

@JsonDeserialize(builder = MainPage.Builder.class)
public class MainPage implements Model {
	private final Bird bird;
	private final List<Bird> alternatives;
	private final AbstractBinary binary;

	private MainPage(Builder builder) {
		this.bird = builder.bird;
		this.alternatives = builder.alternatives;
		this.binary = builder.binary;
	}

	public Bird getBird() {
		return bird;
	}

	public List<Bird> getAlternatives() {
		return alternatives;
	}

	public AbstractBinary getBinary() {
		return binary;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private Bird bird;
		private final List<Bird> alternatives = new ArrayList<>();
		private AbstractBinary binary;

		private Builder() {}

		public Builder withBird(Bird bird) {
			this.bird = bird;
			return this;
		}

		public Builder withAlternatives(List<Bird> alternatives) {
			this.alternatives.clear();
			this.alternatives.addAll(alternatives);
			return this;
		}

		public Builder withBinary(AbstractBinary binary) {
			this.binary = binary;
			return this;
		}

		public MainPage build() {
			return new MainPage(this);
		}
	}
}
