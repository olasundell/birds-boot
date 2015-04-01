package se.atrosys.service.common.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import se.atrosys.birds.common.model.Bird;
import se.atrosys.birds.common.model.Model;
import se.atrosys.service.common.response.AbstractResponse;

import java.io.Serializable;
import java.util.*;

@JsonDeserialize(builder=BirdResponse.Builder.class)
public class BirdResponse extends AbstractResponse<Bird> {
	private BirdResponse(Builder builder) {
		this.models.addAll(builder.birds);
	}

	public List<Bird> getBirds() {
		return Collections.unmodifiableList(models);
	}

	@Override
	protected String getLinkPrefix() {
		return "bird";
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private final List<Bird> birds = new ArrayList<>();

		public BirdResponse build() {
			return new BirdResponse(this);
		}

		public Builder withLinks(Map<String, Object> links) {
			return this;
		}
		public Builder withBirds(List<Bird> birds) {
			this.birds.addAll(birds);

			return this;
		}
	}
}
