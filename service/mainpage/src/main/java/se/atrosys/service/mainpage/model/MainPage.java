package se.atrosys.service.mainpage.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import se.atrosys.birds.common.model.*;

import java.util.ArrayList;
import java.util.List;

@JsonDeserialize(builder = MainPage.Builder.class)
public class MainPage implements Model {
	private final Bird bird;
	private final List<Bird> alternatives;
	private final Image image;
	private final Sound sound;

	private MainPage(Builder builder) {
		this.bird = builder.bird;
		this.alternatives = builder.alternatives;
		this.sound = builder.sound;
		this.image = builder.image;
	}

	public Image getImage() {
		return image;
	}

	public Sound getSound() {
		return sound;
	}


	public Bird getBird() {
		return bird;
	}

	public List<Bird> getAlternatives() {
		return alternatives;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private Bird bird;
		private final List<Bird> alternatives = new ArrayList<>();
		private Image image;
		private Sound sound;

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

		public Builder withSound(Sound sound) {
			this.sound = sound;
			return this;
		}

		public Builder withImage(Image image) {
			this.image = image;
			return this;
		}

		public MainPage build() {
			return new MainPage(this);
		}
	}
}
