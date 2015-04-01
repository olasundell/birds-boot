package se.atrosys.birds.common.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = Image.Builder.class)
public class Image extends AbstractBinary {
	private String id;
	private String url;
	private String birdId;

	public Image() {
	}

	private Image(Builder builder) {
		this.id = builder.id;
		this.url = builder.url;
		this.birdId = builder.birdId;
	}

	@Override
	public String getBirdId() {
		return birdId;
	}

	@Override
	public String getId() {
		return id;
	}

	public String getUrl() {
		return url;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private String id;
		private String url;
		private String birdId;

		private Builder() {
		}

		public Builder withId(String id) {
			this.id = id;
			return this;
		}

		public Builder withUrl(String url) {
			this.url = url;
			return this;
		}

		public Builder withBirdId(String birdId) {
			this.birdId = birdId;
			return this;
		}

		public Image build() {
			return new Image(this);
		}
	}
}
