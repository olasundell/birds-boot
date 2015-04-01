package se.atrosys.service.common.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import se.atrosys.birds.common.model.Family;
import se.atrosys.birds.common.model.Model;
import se.atrosys.service.common.response.AbstractResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@JsonDeserialize(builder=FamilyResponse.Builder.class)
public class FamilyResponse extends AbstractResponse<Family> {
	private FamilyResponse(Builder builder) {
		this.models.addAll(builder.families);
	}

	public List<Family> getFamilies() {
		return Collections.unmodifiableList(models);
	}

	@Override
	protected String getLinkPrefix() {
		return "family";
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private final List<Family> families = new ArrayList<>();

		public FamilyResponse build() {
			return new FamilyResponse(this);
		}

		public Builder withLinks(Map<String, Object> links) {
			return this;
		}
		public Builder withFamilies(List<Family> families) {
			this.families.addAll(families);

			return this;
		}
	}
}
