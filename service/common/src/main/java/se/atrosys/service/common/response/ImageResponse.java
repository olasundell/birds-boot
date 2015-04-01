package se.atrosys.service.common.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import se.atrosys.birds.common.model.Image;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@JsonDeserialize(builder=ImageResponse.Builder.class)
public class ImageResponse extends AbstractResponse<Image> {
	private ImageResponse(Builder builder) {
		this.models.addAll(builder.images);
	}

	public List<Image> getImages() {
		return Collections.unmodifiableList(models);
	}
	@Override
	protected String getLinkPrefix() {
		return "image";
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private List<Image> images;

		private Builder() {
		}

		public Builder withImages(List<Image> images) {
			this.images = images;
			return this;
		}

		public Builder withLinks(Map<String, Object> links) {
			return this;
		}

		public ImageResponse build() {
			return new ImageResponse(this);
		}
	}
}
