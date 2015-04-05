package se.atrosys.service.common.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import se.atrosys.birds.common.model.Model;
import se.atrosys.birds.common.model.Sound;
import se.atrosys.service.common.response.AbstractResponse;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@JsonDeserialize(builder=SoundResponse.Builder.class)
public class SoundResponse extends AbstractResponse<Sound> {
	private SoundResponse(Builder builder) {
		this.models.addAll(builder.sounds);
	}

	public List<Sound> getSounds() {
		return Collections.unmodifiableList(models);
	}

	@Override
	protected String getLinkPrefix() {
		return "sound";
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		Collection<Sound> sounds;
		private Builder() { }


		public Builder withSounds(Collection<Sound> sounds) {
			this.sounds = sounds;
			return this;
		}

		public Builder withLinks(Map<String, Object> links) {
			return this;
		}

		public SoundResponse build() {
			return new SoundResponse(this);
		}
	}
}
